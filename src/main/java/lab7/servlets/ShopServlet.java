package lab7.servlets;

import lab7.dto.ShopDTO;
import lab7.model.Catalog;
import lab7.model.Shop;
import lab7.utils.GlobalConfig;
import lab7.exception.ServiceException;
import lab7.service.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@WebServlet(urlPatterns = "/shop")
public class ShopServlet extends HttpServlet {

    // DTO

    // services
    // tomcat standart authorisation

    // в 7 лабі показати все
    // форма добавлення смартфона
    // показати магазини
    // показати каталоги

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawId = req.getParameter("id");

        if (rawId == null) {
            resp.sendRedirect("./");
            return;
        }

        // Get Shop Id
        long id;
        try {
            id = Long.parseLong(rawId);
        } catch (Exception ex) {
            resp.sendRedirect("./");
            return;
        }

        // Create ShopService
        ShopService shopService;
        try {
            shopService = new ShopService();
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        // Get Shop
        Optional<ShopDTO> optionalShopDto;
        try {
            optionalShopDto = shopService.findById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        if (optionalShopDto.isEmpty()) {
            resp.sendRedirect("./");
            return;
        }

        ShopDTO shopDto = optionalShopDto.get();

        // Get Catalogs
        Set<Catalog> catalogs;
        try {
            catalogs = shopService.getCatalogs(shopDto);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        // Set image root
        ServletContext application = getServletConfig().getServletContext();
        String imagesRoot = (String) application.getAttribute("shop.images.root");

        shopDto.setImageUrl(imagesRoot + shopDto.getImageUrl());

        // Set Attributes for JSP
        req.setAttribute("shop", shopDto);
        req.setAttribute("catalogs", catalogs);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/shop.jsp");
        requestDispatcher.forward(req, resp);

    }
}
