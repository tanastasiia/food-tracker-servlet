package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.service.FoodInfoService;
import ua.training.service.MealService;
import ua.training.utils.ControllerUtil;
import ua.training.utils.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class HomeCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws ServerException {

        request.setAttribute("caloriesNorm", ServiceUtil.getInstance().countCaloriesNorm(controllerUtil.getUser(request)));
        request.getSession().setAttribute("todaysCalories", MealService.getInstance().todaysUserCalories(controllerUtil.getUserId(request)));

        if (request.getSession().getAttribute("lastAdd") != null) {
            request.getSession().removeAttribute("formSuccess");
            request.getSession().removeAttribute("lastAdd");
        } else {
            request.getSession().setAttribute("lastAdd", request.getSession().getAttribute("formSuccess"));
        }

        return PagesToForward.HOME;
    }
}