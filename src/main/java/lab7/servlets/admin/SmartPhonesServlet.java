package lab7.servlets.admin;

import lab1.model.SmartPhone;
import lab7.dto.SmartPhoneDTO;
import lab7.exception.ServiceException;
import lab7.service.SmartPhoneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebServlet(urlPatterns = "/admin/smartphones")
@WebServlet(urlPatterns = "/smartphones")
public class SmartPhonesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<SmartPhoneDTO> smartPhones;
        try {
            SmartPhoneService smartPhoneService = new SmartPhoneService();
            smartPhones = smartPhoneService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        // Set Attributes
        req.setAttribute("smartPhones", smartPhones);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/admin/smartphones.jsp");
        requestDispatcher.forward(req, resp);
    }
}
