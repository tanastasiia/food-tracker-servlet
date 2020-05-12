package ua.training.controller.command;

import ua.training.controller.utils.Constants;
import ua.training.controller.utils.Endpoints;
import ua.training.controller.utils.PagesToForward;
import ua.training.controller.utils.Params;
import ua.training.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("role");
        session.removeAttribute("user");
        session.removeAttribute("isAdmin");

        //session.setAttribute("logout", true);

        response.sendRedirect(Endpoints.LOGIN.getPath() + "?" + Params.LOGOUT.getParam()+"=true");

        return PagesToForward.NONE;
    }
}
