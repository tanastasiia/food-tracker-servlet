package ua.training.controller.command;

import ua.training.controller.utils.PagesToForward;
import ua.training.controller.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect(Routes.HOME.getPath());
        return PagesToForward.NONE;

    }
}
