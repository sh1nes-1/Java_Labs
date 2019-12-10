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
import java.time.LocalDate;
import java.util.Optional;

@WebServlet(urlPatterns = "/edit-smartphone")
public class EditSmartPhoneServlet extends HttpServlet {

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

        Optional<SmartPhoneDTO> optionalSmartPhoneDto;
        try {
            SmartPhoneService smartPhoneService = new SmartPhoneService();
            optionalSmartPhoneDto = smartPhoneService.findById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        if (optionalSmartPhoneDto.isEmpty()) {
            resp.sendRedirect("./");
            return;
        }

        SmartPhoneDTO smartPhoneDto = optionalSmartPhoneDto.get();

        // Set Attributes
        req.setAttribute("colors", SmartPhone.Color.values());
        req.setAttribute("smartPhone", smartPhoneDto);

        // Show page
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/admin/smartphone-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String rawId = req.getParameter("id");
            String name = req.getParameter("name");
            String rawPrice = req.getParameter("price");
            String rawReleaseDate = req.getParameter("releaseDate");
            String rawRam = req.getParameter("ram");
            String rawDiagonal = req.getParameter("diagonal");
            String color = req.getParameter("color");

            SmartPhoneDTO smartPhoneDto = new SmartPhoneDTO();

            smartPhoneDto.setId(Long.parseLong(rawId));
            smartPhoneDto.setName(name);
            smartPhoneDto.setPrice(Integer.parseInt(rawPrice));
            smartPhoneDto.setReleaseDate(LocalDate.parse(rawReleaseDate));
            smartPhoneDto.setRam(Integer.parseInt(rawRam));
            smartPhoneDto.setDiagonal(Double.parseDouble(rawDiagonal));
            smartPhoneDto.setColor(color);

            SmartPhoneService smartPhoneService = new SmartPhoneService();
            smartPhoneService.update(smartPhoneDto);
        } catch (Exception ex) {
            resp.sendError(400, ex.getMessage());
            return;
        }
        resp.sendRedirect("./smartphones");
    }
}
