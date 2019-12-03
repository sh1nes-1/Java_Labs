package lab7.servlets;

import lab5.connection.ConnectionBuilder;
import lab5.connection.ConnectionFactory;
import lab5.dao.CatalogDao;
import lab5.dao.SmartPhoneDao;
import lab5.dao.jdbc.CatalogDaoJdbc;
import lab5.dao.jdbc.SmartPhoneDaoJdbc;
import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.model.Catalog;
import lab5.model.CatalogItem;
import lab5.model.Shop;
import lab5.model.SmartPhone;
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

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rawCatalogId = req.getParameter("cat_id");
        String rawSmartPhoneId = req.getParameter("sm_id");
        if (rawCatalogId != null && rawSmartPhoneId != null) {
            Long catalogId = null, smartPhoneId = null;

            try {
                catalogId = Long.parseLong(rawCatalogId);
                smartPhoneId = Long.parseLong(rawSmartPhoneId);
            } catch (Exception ignored) {
            }

            if (catalogId != null && smartPhoneId != null) {
                try {
                    ConnectionBuilder connectionBuilder = ConnectionFactory.getConnectionBuilder();
                    Connection connection = connectionBuilder.getConnection();

                    CatalogDao catalogDao = new CatalogDaoJdbc(connection);
                    SmartPhoneDao smartPhoneDao = new SmartPhoneDaoJdbc(connection);

                    Optional<Catalog> optionalCatalog = catalogDao.findByIdEager(catalogId);
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

                        Optional<SmartPhone> optionalSmartPhone = smartPhoneDao.findById(smartPhoneId);
                        if (optionalSmartPhone.isPresent()) {
                            Optional<CatalogItem> optionalCatalogItem = catalog.getSmartPhoneInfo(optionalSmartPhone.get());
                            if (optionalCatalogItem.isPresent()) {
                                req.setAttribute("item", optionalCatalogItem.get());

                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/item.jsp");
                                requestDispatcher.forward(req, resp);
                            }
                        }
                    }
                } catch (DaoException | DatabaseConnectionException e) {
                    e.printStackTrace();
                }
            }
        }
        resp.sendRedirect("./");
    }
}
