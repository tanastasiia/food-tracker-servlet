package ua.training.controller.command;

import ua.training.utils.*;
import ua.training.controller.Routes;
import ua.training.controller.PagesToForward;
import ua.training.model.constants.FoodInfoConst;
import ua.training.model.dto.FoodDto;
import ua.training.model.entity.FoodInfo;
import ua.training.service.FoodInfoService;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AddFoodCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private FoodInfoService foodInfoService = FoodInfoService.getInstance();

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            FoodDto foodDto = controllerUtil.parseFoodDto(request);

            Optional<FoodInfo> savedFoodInfo = foodInfoService.saveFood(foodDto, controllerUtil.getUser(request),
                            request.getParameter(FoodInfoConst.IS_GLOBAL.getField()) != null);

            controllerUtil.setAddSuccessOrFailAttributesHomePage(request, savedFoodInfo.isPresent(), "food");

            response.sendRedirect(Routes.HOME.getPath());
            return PagesToForward.NONE;

        } catch (ValidationException e) {
            controllerUtil.setErrorAttributes(request, e.getErrors());
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("food_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }
        request.setAttribute("caloriesNorm", ServiceUtil.getInstance().countCaloriesNorm(controllerUtil.getUser(request)));
        return PagesToForward.HOME;

    }
}