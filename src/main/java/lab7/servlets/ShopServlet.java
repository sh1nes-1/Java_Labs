package lab7.servlets;

import lab5.connection.ConnectionBuilder;
import lab5.connection.ConnectionFactory;
import lab5.dao.ShopDao;
import lab5.dao.jdbc.ShopDaoJdbc;
import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.model.Shop;
import lab5.utils.GlobalConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

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
        req.setCharacterEncoding("UTF-8");
        String rawId = req.getParameter("id");
        if (rawId != null) {
            Long id = null;

            try {
                id = Long.parseLong(rawId);
            } catch (Exception ignored) {
            }

            if (id != null) {
                try {
                    ConnectionBuilder connectionBuilder = ConnectionFactory.getConnectionBuilder();
                    Connection connection = connectionBuilder.getConnection();
                    ShopDao shopDao = new ShopDaoJdbc(connection);
                    Optional<Shop> optionalShop = shopDao.findById(id);
                    if (optionalShop.isPresent()) {
                        Shop shop = optionalShop.get();

                        GlobalConfig config = new GlobalConfig();
                        config.loadGlobalConfig();
                        // в DTO шлях буде а в Entity ні
                        shop.setImageUrl(config.getProperty("shop.images.root") + shop.getImageUrl());
                        req.setAttribute("shop", shop);
                        req.setAttribute("catalogs", shopDao.getCatalogs(shop));

                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/shop.jsp");
                        requestDispatcher.forward(req, resp);
                    }
                } catch (DaoException | DatabaseConnectionException e) {
                    e.printStackTrace();
                }
            }
        }
        resp.sendRedirect("./");
    }

}
