package ua.training.controller.command;

import ua.training.controller.utils.ControllerUtil;
import ua.training.controller.utils.PagesToForward;
import ua.training.controller.utils.Routes;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserRoleCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long userId = Long.parseLong(request.getParameter("id"));
        String role = request.getParameter("role");

        if (userId != controllerUtil.getUserId(request)) {
            UserService.getInstance().changeRole(userId, role);
        }

        response.sendRedirect(Routes.ADMIN.getPath());
        return PagesToForward.NONE;

    }
}
