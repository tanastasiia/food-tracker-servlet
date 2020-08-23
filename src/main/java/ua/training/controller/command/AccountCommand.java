package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.Endpoints;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Command for account info page
 *
 * @see Endpoints#ACCOUNT
 */
public class AccountCommand implements Command {

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) {

        return PagesToForward.ACCOUNT;

    }
}
