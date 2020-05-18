package ua.training.controller.command;

import ua.training.controller.utils.PagesToForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
