package lab7.servlets;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/image")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("image/jpeg");

        ServletContext application = getServletConfig().getServletContext();
        String imagesAbsoluteRoot = (String) application.getAttribute("shop.images.absolute_root");

        File f = new File(imagesAbsoluteRoot + req.getParameter("name"));
        BufferedImage bi = ImageIO.read(f);
        OutputStream out = res.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();
    }
}
