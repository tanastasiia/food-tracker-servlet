package ua.training.controller.command;

import ua.training.controller.PagesToForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
