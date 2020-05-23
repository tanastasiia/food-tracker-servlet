package ua.training.listeners;

import lombok.SneakyThrows;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @SneakyThrows
    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();

        System.out.println("request lesteener");
        req.setCharacterEncoding("UTF-8");

        if(req.getParameter("lang")!=null){
            req.getSession().setAttribute("lang", new Locale(req.getParameter("lang")));
        }

    }
}
