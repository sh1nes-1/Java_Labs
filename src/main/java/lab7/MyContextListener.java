package lab7;

import lab5.utils.GlobalConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();

        try {
            GlobalConfig config = new GlobalConfig();
            config.loadGlobalConfig();
            application.setAttribute("image.extensions", config.getProperty("image.extensions").split(","));
            application.setAttribute("shop.images.root", config.getProperty("shop.images.root"));
            application.setAttribute("shop.images.absolute_root", config.getProperty("shop.images.absolute_root"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
