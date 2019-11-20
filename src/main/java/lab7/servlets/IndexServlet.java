package lab7.servlets;

import lab5.connection.ConnectionBuilder;
import lab5.connection.ConnectionFactory;
import lab5.dao.ShopDao;
import lab5.dao.jdbc.ShopDaoJdbc;
import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = "")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ConnectionBuilder connectionBuilder = ConnectionFactory.getConnectionBuilder();
            Connection connection = connectionBuilder.getConnection();
            ShopDao shopDao = new ShopDaoJdbc(connection);
            req.setAttribute("userName", shopDao.findAll());
        } catch (DatabaseConnectionException | DaoException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }

}
