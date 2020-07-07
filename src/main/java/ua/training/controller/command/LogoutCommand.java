package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Routes;
import ua.training.utils.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {

    private Logger logger = LogManager.getLogger(LogoutCommand.class.getName());

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("role");
        session.removeAttribute("user");
        session.removeAttribute("userId");
        session.removeAttribute("isAdmin");

        logger.info("User logged out");
        response.sendRedirect(Routes.LOGIN.getPath() + "?logout=true");

        return PagesToForward.NONE;
    }
}
