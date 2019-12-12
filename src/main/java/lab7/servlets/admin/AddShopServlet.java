package lab7.servlets.admin;

import lab7.dto.ShopDTO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/add-shop")
@MultipartConfig
public class AddShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set Attributes
        req.setAttribute("add", true);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/admin/shop-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");

            Part filePart = req.getPart("logo");
            InputStream fileContent = filePart.getInputStream();

            String[] fileNameParts = filePart.getName().split("\\.");
            String extension = fileNameParts[fileNameParts.length - 1];

            ServletContext application = getServletConfig().getServletContext();

            List<String> allowedExtensions = Arrays.asList((String[]) application.getAttribute("image.extensions"));
            if (!allowedExtensions.contains(extension))
                throw new Exception("This extension is now allowed");

            String imagesAbsoluteRoot = (String) application.getAttribute("shop.images.absolute_root");

            String fileName = name + "." + extension;
            Files.copy(fileContent, Path.of(imagesAbsoluteRoot + fileName), StandardCopyOption.REPLACE_EXISTING);

            ShopDTO shopDTO = new ShopDTO();
            shopDTO.setName(name);
            shopDTO.setImageUrl(fileName);

            ShopService shopService = new ShopService();
            shopService.insert(shopDTO);

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(400, ex.toString());
            return;
        }
        resp.sendRedirect("./shops");
    }

}
