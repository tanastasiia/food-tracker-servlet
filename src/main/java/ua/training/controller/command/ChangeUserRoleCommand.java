package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Routes;
import ua.training.service.UserService;
import ua.training.utils.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserRoleCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private Logger logger = LogManager.getLogger(ChangePasswordCommand.class.getName());

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long userId = Long.parseLong(request.getParameter("id"));
        String role = request.getParameter("role");

        if (userId != controllerUtil.getUserId(request)) {
            UserService.getInstance().changeRole(userId, role);
            logger.info("For user with id=" + userId + "role changed");
        }

        response.sendRedirect(Routes.ADMIN.getPath());
        return PagesToForward.NONE;

    }
}
