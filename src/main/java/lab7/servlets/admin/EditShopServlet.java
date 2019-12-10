package lab7.servlets.admin;

import lab1.model.Shop;
import lab1.model.SmartPhone;
import lab7.dto.ShopDTO;
import lab7.dto.SmartPhoneDTO;
import lab7.exception.ServiceException;
import lab7.service.ShopService;
import lab7.service.SmartPhoneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet(urlPatterns = "/edit-shop")
@MultipartConfig
public class EditShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawId = req.getParameter("id");

        if (rawId == null) {
            resp.sendRedirect("./");
            return;
        }

        // Get SmartPhone Id
        long id;
        try {
            id = Long.parseLong(rawId);
        } catch (Exception ex) {
            resp.sendRedirect("./");
            return;
        }

        Optional<ShopDTO> optionalShopDto;
        try {
            ShopService shopService = new ShopService();
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

        // Set Attributes
        req.setAttribute("shop", shopDto);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/admin/shop-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = null;
        boolean isSet = false;
        try {
            filePart = req.getPart("logo");
            isSet = true;
        } catch(Exception ignored) {
            isSet = false;
        }

        try {
            String rawId = req.getParameter("id");
            String name = req.getParameter("name");

            Long id = Long.parseLong(rawId);

            ShopService shopService = new ShopService();
            Optional<ShopDTO> optionalShopDTO = shopService.findById(id);

            if (optionalShopDTO.isEmpty())
                throw new Exception("This shop not found!");

            ShopDTO shopDto = optionalShopDTO.get();
            shopDto.setName(name);

            if (isSet && filePart != null && filePart.getSize() > 0) {
                shopDto.setImageUrl(name + ".jpg");

                InputStream fileContent = filePart.getInputStream();

                ServletContext application = getServletConfig().getServletContext();
                String imagesAbsoluteRoot = (String) application.getAttribute("shop.images.absolute_root");

                Files.copy(fileContent, Path.of(imagesAbsoluteRoot + shopDto.getImageUrl()), StandardCopyOption.REPLACE_EXISTING);
            }

            shopService.update(shopDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(400, ex.getMessage());
            return;
        }
        resp.sendRedirect("./shops");
    }
}
