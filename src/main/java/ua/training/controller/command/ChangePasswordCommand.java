package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.Endpoints;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.model.service.UserService;
import ua.training.controller.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for changing password
 *
 * @see Endpoints#CHANGE_USER_PASSWORD
 */
public class ChangePasswordCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private Logger logger = LogManager.getLogger(ChangePasswordCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ControllerUtil.getInst().isMethodGet(request)){
            request.removeAttribute("success");
            return PagesToForward.CHANGE_PASSWORD;
        }
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        boolean isPasswordChanged = UserService.getInstance().changePassword(oldPassword, newPassword, ControllerUtil.getInst().getUser(request));

        request.setAttribute("success", isPasswordChanged);
        logger.info("For user with id=" + controllerUtil.getUserId(request) + " password changed: " + isPasswordChanged);

        return PagesToForward.CHANGE_PASSWORD;

    }
}
