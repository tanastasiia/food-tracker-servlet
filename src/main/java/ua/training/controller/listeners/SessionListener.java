package ua.training.controller.listeners;

import ua.training.model.entity.Role;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("lang", Locale.ENGLISH);
        se.getSession().setAttribute("role", Role.ROLE_GUEST.name());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
