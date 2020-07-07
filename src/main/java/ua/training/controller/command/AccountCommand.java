package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AccountCommand implements Command {

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) {

        return PagesToForward.ACCOUNT;

    }
}
