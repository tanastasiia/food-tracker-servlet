package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    Paths execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
