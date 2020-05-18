package ua.training.controller.command;

import ua.training.controller.utils.PagesToForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class HomeCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) {

        String lastAdd = (String) request.getSession().getAttribute("lastAdd");
        if (lastAdd != null) {
            request.getSession().removeAttribute("formSuccess");
            request.getSession().removeAttribute("lastAdd");
        } else {
            request.getSession().setAttribute("lastAdd", request.getSession().getAttribute("formSuccess"));
        }

        return PagesToForward.HOME;
    }
}