package lab7.servlets;

import lab5.connection.ConnectionBuilder;
import lab5.connection.ConnectionFactory;
import lab5.dao.CatalogDao;
import lab5.dao.ShopDao;
import lab5.dao.jdbc.CatalogDaoJdbc;
import lab5.dao.jdbc.ShopDaoJdbc;
import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.model.Catalog;
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

@WebServlet(urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    //TODO: в ініт конфіг
    //TODO: або в скоуп або фільтр
    //TODO: зробити сервіси, маппери для DTO -> Entity, Entity -> DTO

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
                    CatalogDao catalogDao = new CatalogDaoJdbc(connection);
                    Optional<Catalog> optionalCatalog = catalogDao.findByIdEager(id);
                    if (optionalCatalog.isPresent()) {
                        Catalog catalog = optionalCatalog.get();
                        req.setAttribute("catalog", catalog);

                        Optional<Shop> optionalShop = catalogDao.getShop(catalog);
                        if (optionalShop.isPresent()) {
                            GlobalConfig config = new GlobalConfig();
                            config.loadGlobalConfig();

                            Shop shop = optionalShop.get();
                            req.setAttribute("shop", shop);
                            shop.setImageUrl(config.getProperty("shop.images.root") + shop.getImageUrl());
                        }

                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/catalog.jsp");
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
