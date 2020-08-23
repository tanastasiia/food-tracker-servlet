package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.Paths;
import ua.training.controller.Endpoints;
import ua.training.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command for logout
 *
 * @see Endpoints#LOGOUT
 */
public class LogoutCommand implements Command {

    private Logger logger = LogManager.getLogger(LogoutCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        session.setAttribute("role", Role.ROLE_GUEST.name());
        session.removeAttribute("user");
        session.removeAttribute("userId");
        session.removeAttribute("isAdmin");

        logger.info("User logged out");
        return Endpoints.LOGIN_LOGOUT;
    }
}
