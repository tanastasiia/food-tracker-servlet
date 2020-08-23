package ua.training.controller.command;

import com.google.gson.Gson;
import ua.training.controller.Endpoints;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.model.service.FoodInfoService;
import ua.training.controller.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command forgetting food names list for the user
 *
 * @see Endpoints#GET_FOOD_NAMES
 */
public class GetFoodNamesCommand implements Command {
    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> str = FoodInfoService.getInstance()
                .findAllFoodNamesForUser(
                        ControllerUtil.getInst().getUserId(request),
                        ControllerUtil.getInst().getLocale(request));

        response.getWriter().println((new Gson()).toJson(str));
        response.setContentType("application/json");

        return PagesToForward.NONE;
    }
}
