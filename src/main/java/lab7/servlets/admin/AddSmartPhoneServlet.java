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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

@WebServlet(urlPatterns = "/add-smartphone")
public class AddSmartPhoneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set Attributes
        req.setAttribute("add", true);
        req.setAttribute("colors", SmartPhone.Color.values());

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/admin/smartphone-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String rawPrice = req.getParameter("price");
            String rawReleaseDate = req.getParameter("releaseDate");
            String rawRam = req.getParameter("ram");
            String rawDiagonal = req.getParameter("diagonal").replace(",", ".");
            String color = req.getParameter("color");

            LocalDate releaseDate = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(rawReleaseDate).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            SmartPhoneDTO smartPhoneDto = new SmartPhoneDTO();

            smartPhoneDto.setName(name);
            smartPhoneDto.setPrice(Integer.parseInt(rawPrice));
            smartPhoneDto.setReleaseDate(releaseDate);
            smartPhoneDto.setRam(Integer.parseInt(rawRam));
            smartPhoneDto.setDiagonal(Double.parseDouble(rawDiagonal));
            smartPhoneDto.setColor(color);

            SmartPhoneService smartPhoneService = new SmartPhoneService();
            smartPhoneService.insert(smartPhoneDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(400, ex.toString());
            return;
        }
        resp.sendRedirect("./smartphones");
    }
}
