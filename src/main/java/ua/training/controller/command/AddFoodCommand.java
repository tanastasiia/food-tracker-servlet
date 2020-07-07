package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.RoutesToRedirect;
import ua.training.model.entity.FoodInfo;
import ua.training.service.FoodInfoService;
import ua.training.utils.ControllerUtil;
import ua.training.utils.ServiceUtil;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddFoodCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private FoodInfoService foodInfoService = FoodInfoService.getInstance();
    private Logger logger = LogManager.getLogger(AddFoodCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            Optional<FoodInfo> savedFoodInfo = foodInfoService.saveFood(
                    controllerUtil.parseFoodDto(request),
                    controllerUtil.getUser(request)
            );

            controllerUtil.setAddSuccessOrFailAttributesHomePage(request, savedFoodInfo.isPresent(), "food");

            return RoutesToRedirect.HOME;

        } catch (ValidationException e) {
            logger.info("Validation errors: " + e.getErrors());
            controllerUtil.setErrorAttributes(request, e.getErrors());
        } catch (Exception e) {
            logger.info("Exception: " + e);
            request.setAttribute("food_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }

        request.setAttribute("caloriesNorm", ServiceUtil.getInstance().countCaloriesNorm(controllerUtil.getUser(request)));
        return PagesToForward.HOME;

    }
}