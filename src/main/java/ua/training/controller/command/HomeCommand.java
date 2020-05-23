package ua.training.controller.command;

import ua.training.controller.utils.PagesToForward;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;
import ua.training.service.MealService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.ServerException;
import java.util.Optional;

public class HomeCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws ServerException {

        UserDto userDto = (UserDto)request.getSession().getAttribute("user");
        request.setAttribute("caloriesNorm", UserService.getInstance().countCaloriesNorm(userDto.toEntity()));
        request.getSession().setAttribute("todaysCalories", MealService.getInstance().todaysUserCalories(userDto.getId()));

        if (request.getSession().getAttribute("lastAdd") != null) {
            request.getSession().removeAttribute("formSuccess");
            request.getSession().removeAttribute("lastAdd");
        } else {
            request.getSession().setAttribute("lastAdd", request.getSession().getAttribute("formSuccess"));
        }

        return PagesToForward.HOME;
    }
}