package ua.training.controller.command;

import ua.training.controller.Endpoints;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.model.service.MealService;
import ua.training.controller.ControllerUtil;
import ua.training.model.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.ServerException;

/**
 * Command for home page
 *
 * @see Endpoints#HOME
 */
public class HomeCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws ServerException {

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