package ua.training.controller.command;

import ua.training.controller.utils.PagesToForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response){
        System.out.println("HOME command");
        return PagesToForward.HOME;
    }
}