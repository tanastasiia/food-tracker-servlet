package ua.training.controller.command;

import ua.training.utils.ControllerUtil;
import ua.training.controller.Routes;
import ua.training.controller.PagesToForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LogoutCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("role");
        session.removeAttribute("user");
        session.removeAttribute("userId");
        session.removeAttribute("isAdmin");

        Logger.getLogger(LogoutCommand.class.getName()).info("User logged out");
        response.sendRedirect(Routes.LOGIN.getPath() + "?logout=true");

        return PagesToForward.NONE;
    }
}
