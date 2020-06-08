package ua.training.controller.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Logger.getRootLogger().warn(e.getMessage());
        }

        if(req.getParameter("lang")!=null){
            req.getSession().setAttribute("lang", new Locale(req.getParameter("lang")));
        }

    }
}
