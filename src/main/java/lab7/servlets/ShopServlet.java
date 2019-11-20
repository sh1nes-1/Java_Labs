package lab7.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet()
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
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("message", "смартфонів");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/shop.jsp");
        requestDispatcher.forward(req, resp);
    }

}
