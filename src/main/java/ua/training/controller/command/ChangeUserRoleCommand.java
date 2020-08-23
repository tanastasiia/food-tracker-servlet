package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.Paths;
import ua.training.controller.Endpoints;
import ua.training.model.service.UserService;
import ua.training.controller.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for changing user role
 *
 * @see Endpoints#CHANGE_USER_ROLE
 */
public class ChangeUserRoleCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private Logger logger = LogManager.getLogger(ChangePasswordCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long userId = Long.parseLong(request.getParameter("id"));
        String role = request.getParameter("role");

        if (userId != controllerUtil.getUserId(request)) {
            UserService.getInstance().changeRole(userId, role);
            logger.info("For user with id=" + userId + "role changed");
        }

        return Endpoints.ADMIN;

    }
}
