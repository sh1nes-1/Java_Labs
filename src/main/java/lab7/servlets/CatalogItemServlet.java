package lab7.servlets;

import lab7.dto.CatalogDTO;
import lab7.dto.SmartPhoneDTO;
import lab7.mapper.CatalogMapper;
import lab7.mapper.SmartPhoneMapper;
import lab7.model.Catalog;
import lab7.model.CatalogItem;
import lab7.model.Shop;
import lab7.model.SmartPhone;
import lab7.utils.GlobalConfig;
import lab7.exception.ServiceException;
import lab7.service.CatalogService;
import lab7.service.SmartPhoneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/catalogitem")
public class CatalogItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawCatalogId = req.getParameter("cat_id");
        String rawSmartPhoneId = req.getParameter("sm_id");

        if (rawCatalogId == null || rawSmartPhoneId == null) {
            resp.sendRedirect("./");
            return;
        }

        long catalogId, smartPhoneId;
        try {
            catalogId = Long.parseLong(rawCatalogId);
            smartPhoneId = Long.parseLong(rawSmartPhoneId);
        } catch (Exception ex) {
            resp.sendRedirect("./");
            return;
        }

        // Get Catalog
        CatalogService catalogService;
        Optional<CatalogDTO> optionalCatalogDto;
        try {
            catalogService = new CatalogService();
            optionalCatalogDto = catalogService.findByIdEager(catalogId);
        } catch (ServiceException e) {
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
        } catch (ServiceException e) {
            resp.sendRedirect("./");
            return;
        }

        if (optionalShop.isEmpty()) {
            resp.sendRedirect("./");
            return;
        }

        Shop shop = optionalShop.get();

        // Get SmartPhone
        Optional<SmartPhoneDTO> optionalSmartPhoneDto;
        try {
            SmartPhoneService smartPhoneService = new SmartPhoneService();
            optionalSmartPhoneDto = smartPhoneService.findById(smartPhoneId);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        if (optionalSmartPhoneDto.isEmpty()) {
            resp.sendRedirect("./");
            return;
        }

        // Get CatalogItem
        SmartPhone smartPhone = SmartPhoneMapper.INSTANCE.getSmartPhone(optionalSmartPhoneDto.get());
        Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
        Optional<CatalogItem> optionalCatalogItem = catalog.getSmartPhoneInfo(smartPhone);
        if (optionalCatalogItem.isEmpty()) {
            resp.sendRedirect("./");
            return;
        }

        // Set Attributes for JSP
        req.setAttribute("catalog", catalogDto);
        req.setAttribute("shop", shop);
        req.setAttribute("item", optionalCatalogItem.get());

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/catalogitem.jsp");
        requestDispatcher.forward(req, resp);
    }
}
