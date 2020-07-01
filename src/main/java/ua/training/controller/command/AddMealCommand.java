package ua.training.controller.command;

import ua.training.model.dto.MealDto;
import ua.training.model.dto.UserDto;
import ua.training.utils.ControllerUtil;
import ua.training.controller.Routes;
import ua.training.controller.PagesToForward;
import ua.training.model.entity.FoodInfo;
import ua.training.service.FoodInfoService;
import ua.training.service.MealService;
import ua.training.utils.ServiceUtil;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddMealCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            MealDto mealDto = controllerUtil.parseMealDto(request);

            UserDto user = controllerUtil.getUser(request);
            Optional<FoodInfo> foodInfo = FoodInfoService.getInstance().findFoodByFoodNameAndUser(mealDto.getFoodName(), user.getId());

            if (foodInfo.isPresent()) {
                MealService.getInstance().saveMeal(foodInfo.get(), mealDto.getAmount(), user);
            }

            controllerUtil.setAddSuccessOrFailAttributesHomePage(request, foodInfo.isPresent(), "meal");

            response.sendRedirect(Routes.HOME.getPath());
            return PagesToForward.NONE;


        } catch (ValidationException e) {
            e.getErrors().forEach(error -> request.setAttribute("error_" + error.getField(), error.getMessage()));
        } catch (Exception e) {
            request.setAttribute("meal_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }

        request.setAttribute("caloriesNorm", ServiceUtil.getInstance().countCaloriesNorm(controllerUtil.getUser(request).toEntity()));
        return PagesToForward.HOME;
    }
}
