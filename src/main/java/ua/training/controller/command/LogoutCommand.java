package ua.training.controller.command;

import ua.training.controller.utils.PagesToForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesToForward.EMPTY;
    }
}
