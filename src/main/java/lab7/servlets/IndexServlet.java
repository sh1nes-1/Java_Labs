package lab7.servlets;

import lab7.dto.ShopDTO;
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
import java.util.List;

@WebServlet(urlPatterns = "")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShopService shopService;
        try {
            shopService = new ShopService();
        } catch (ServiceException ex) {
            ex.printStackTrace();
            return;
        }

        // Get Shops
        List<ShopDTO> shops;
        try {
            shops = shopService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }

        // Set image root
        ServletContext application = getServletConfig().getServletContext();
        String imagesRoot = (String) application.getAttribute("shop.images.root");

        for (ShopDTO shop : shops) {
            shop.setImageUrl(imagesRoot + shop.getImageUrl());
        }

        // Set Attribute for JSP
        req.setAttribute("shops", shops);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }

}
