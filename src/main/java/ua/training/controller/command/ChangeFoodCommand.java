package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.Endpoints;
import ua.training.model.dto.FoodDto;
import ua.training.model.service.FoodInfoService;
import ua.training.controller.ControllerUtil;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for changing food info
 *
 * @see Endpoints#CHANGE_FOOD
 */
public class ChangeFoodCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private FoodInfoService foodInfoService = FoodInfoService.getInstance();
    private Logger logger = LogManager.getLogger(ChangeFoodCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (controllerUtil.isMethodGet(request)) {
            foodInfoService.findByFoodId(Long.parseLong(request.getParameter("foodId")))
                    .ifPresent(foodInfo -> request.setAttribute("foodInfo", foodInfo));
            return PagesToForward.CHANGE_FOOD;
        }
        try {
            FoodDto foodDto = controllerUtil.parseFoodDto(request);
            foodInfoService.updateFood(foodDto, Long.parseLong(request.getParameter("foodId")));
            return Endpoints.ADMIN;
        } catch (ValidationException e) {
            logger.info("Validation errors: " + e.getErrors());
            controllerUtil.setErrorAttributes(request, e.getErrors());
            foodInfoService.findByFoodId(Long.parseLong(request.getParameter("foodId")))
                    .ifPresent(foodInfo -> request.setAttribute("foodInfo", foodInfo));
            return PagesToForward.CHANGE_FOOD;
        }

    }
}
