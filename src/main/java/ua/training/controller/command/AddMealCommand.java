package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.RoutesToRedirect;
import ua.training.model.dto.MealDto;
import ua.training.model.entity.FoodInfo;
import ua.training.service.FoodInfoService;
import ua.training.service.MealService;
import ua.training.utils.ControllerUtil;
import ua.training.utils.ServiceUtil;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddMealCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private Logger logger = LogManager.getLogger(AddFoodCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            MealDto mealDto = controllerUtil.parseMealDto(request);

            Optional<FoodInfo> foodInfo = FoodInfoService.getInstance().findFoodByFoodNameAndUser(mealDto.getFoodName(), controllerUtil.getUserId(request));

            if (foodInfo.isPresent()) {
                MealService.getInstance().saveMeal(foodInfo.get(), mealDto.getAmount(), controllerUtil.getUser(request));
            }

            controllerUtil.setAddSuccessOrFailAttributesHomePage(request, foodInfo.isPresent(), "meal");

            return RoutesToRedirect.HOME;


        } catch (ValidationException e) {
            logger.info("Validation errors: " + e.getErrors());
            controllerUtil.setErrorAttributes(request, e.getErrors());
        } catch (Exception e) {
            request.setAttribute("meal_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }

        request.setAttribute("caloriesNorm", ServiceUtil.getInstance().countCaloriesNorm(controllerUtil.getUser(request)));
        return PagesToForward.HOME;
    }
}
