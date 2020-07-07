package ua.training.controller.command;

import ua.training.controller.Paths;
import ua.training.controller.RoutesToRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCommand implements Command {
    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return RoutesToRedirect.HOME;

    }
}
