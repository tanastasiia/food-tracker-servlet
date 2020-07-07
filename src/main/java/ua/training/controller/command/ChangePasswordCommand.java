package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.model.constants.UserConst;
import ua.training.service.UserService;
import ua.training.utils.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ControllerUtil.getInst().isMethodGet(request)){
            request.removeAttribute("success");
            return PagesToForward.CHANGE_PASSWORD;
        }
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        request.setAttribute("success", UserService.getInstance().changePassword(oldPassword, newPassword, ControllerUtil.getInst().getUser(request)));

        return PagesToForward.CHANGE_PASSWORD;

    }
}
