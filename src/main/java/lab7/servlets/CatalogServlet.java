package lab7.servlets;

import lab7.dto.CatalogDTO;
import lab7.exception.DatabaseConnectionException;
import lab7.exception.ServiceException;
import lab7.model.Catalog;
import lab7.model.Shop;
import lab7.service.CatalogService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet(urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    // TODO: MapStruct
    // в DTO шлях буде а в Entity ні
    // TODO: зробити сервіси, маппери для DTO -> Entity, Entity -> DTO

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String rawId = req.getParameter("id");

        if (rawId == null) {
            resp.sendRedirect("./");
            return;
        }

        // Get Catalog Id
        long id;
        try {
            id = Long.parseLong(rawId);
        } catch (Exception ex) {
            resp.sendRedirect("./");
            return;
        }

        // Get Catalog
        CatalogService catalogService;
        Optional<CatalogDTO> optionalCatalogDto;
        try {
            catalogService = new CatalogService();
            optionalCatalogDto = catalogService.findByIdEager(id);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        if (optionalCatalogDto.isEmpty()) {
            resp.sendRedirect("./");
            return;
        }

        CatalogDTO catalogDto = optionalCatalogDto.get();

        // Get Shop
        Optional<Shop> optionalShop;
        try {
            optionalShop = catalogService.getShop(catalogDto);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        if (optionalShop.isEmpty()) {
            resp.sendRedirect("./");
            return;
        }

        Shop shop = optionalShop.get();

        // Set Attributes for JSP
        req.setAttribute("catalog", catalogDto);
        req.setAttribute("shop", shop);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/catalog.jsp");
        requestDispatcher.forward(req, resp);

    }
}
