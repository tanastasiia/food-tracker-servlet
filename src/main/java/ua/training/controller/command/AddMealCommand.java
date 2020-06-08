package ua.training.controller.command;

import ua.training.model.dto.UserDto;
import ua.training.utils.ControllerUtil;
import ua.training.controller.Routes;
import ua.training.controller.PagesToForward;
import ua.training.model.dto.MealDto;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.Meal;
import ua.training.model.entity.User;
import ua.training.service.FoodInfoService;
import ua.training.service.MealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class AddMealCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        MealDto mealDto = parseMeal(request);
        UserDto user = controllerUtil.getUser(request);
        Optional<FoodInfo> foodInfo = FoodInfoService.getInstance().findFoodByFoodNameAndUser(mealDto.getFoodName(), user.getId());

        if (foodInfo.isPresent()) {
            MealService.getInstance().saveMeal(mealDto, foodInfo.get(), user);
            request.getSession().setAttribute("formSuccess", "mealAdded");
        } else {
            request.getSession().setAttribute("formSuccess", "mealNotAdded");
        }

        request.getSession().removeAttribute("lastAdd");

        response.sendRedirect(Routes.HOME.getPath());
        return PagesToForward.NONE;
    }

    private MealDto parseMeal(HttpServletRequest request) {

        return new MealDto(request.getParameter("foodName"),
                Integer.parseInt(request.getParameter("amount")),
                LocalDate.now(), LocalTime.now());

    }
}
