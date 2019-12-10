package lab7.servlets.admin;

import lab7.dto.ShopDTO;
import lab7.dto.SmartPhoneDTO;
import lab7.exception.ServiceException;
import lab7.service.ShopService;
import lab7.service.SmartPhoneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/shops")
public class ShopsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ShopDTO> shops;
        try {
            ShopService smartPhoneService = new ShopService();
            shops = smartPhoneService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        // Set Attributes
        req.setAttribute("shops", shops);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/admin/shops.jsp");
        requestDispatcher.forward(req, resp);
    }
}
