package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.Endpoints;
import ua.training.model.dto.MealDto;
import ua.training.model.entity.FoodInfo;
import ua.training.model.service.FoodInfoService;
import ua.training.model.service.MealService;
import ua.training.controller.ControllerUtil;
import ua.training.model.ServiceUtil;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.ServerException;
import java.util.Optional;

/**
 * Command for adding meal
 *
 * @see Endpoints#ADD_MEAL
 */
public class AddMealCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private Logger logger = LogManager.getLogger(AddFoodCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws ServerException {

        try {
            MealDto mealDto = controllerUtil.parseMealDto(request);

            Optional<FoodInfo> foodInfo = FoodInfoService.getInstance().findFoodByFoodNameAndUser(mealDto.getFoodName(), controllerUtil.getUserId(request));

            if (foodInfo.isPresent()) {
                MealService.getInstance().saveMeal(foodInfo.get(), mealDto.getAmount(), controllerUtil.getUser(request));
            }

            controllerUtil.setAddSuccessOrFailAttributesHomePage(request, foodInfo.isPresent(), "meal");

            return Endpoints.HOME;


        } catch (ValidationException e) {
            logger.info("Validation errors: " + e.getErrors());
            controllerUtil.setErrorAttributes(request, e.getErrors());
        } catch (NumberFormatException e) {
            request.setAttribute("meal_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }

        request.setAttribute("caloriesNorm", ServiceUtil.getInstance().countCaloriesNorm(controllerUtil.getUser(request)));
        return PagesToForward.HOME;
    }
}
