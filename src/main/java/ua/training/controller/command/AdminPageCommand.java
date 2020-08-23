package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.Endpoints;
import ua.training.model.service.FoodInfoService;
import ua.training.model.service.MealService;
import ua.training.model.service.UserService;
import ua.training.utils.Constants;
import ua.training.controller.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.ServerException;


/**
 * Command for admin page
 *
 * @see Endpoints#ADMIN
 */
public class AdminPageCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private MealService mealService = MealService.getInstance();
    private UserService userService = UserService.getInstance();
    private FoodInfoService foodInfoService = FoodInfoService.getInstance();

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws ServerException {

        int page = controllerUtil.getPage(request);
        int offset = controllerUtil.getOffset(page, Constants.PAGE_SIZE);

        if (request.getParameter("tab") == null || request.getParameter("tab").equals("1")) {

            request.setAttribute("users", userService.findAllUsers(Constants.PAGE_SIZE, offset));
            request.setAttribute("numOfPages",
                    controllerUtil.countNumOfPages(userService.countAllUsers(), Constants.PAGE_SIZE));

        } else if (request.getParameter("tab").equals("2")) {

            request.setAttribute("foods", foodInfoService.findAllFood(Constants.PAGE_SIZE, offset));
            request.setAttribute("numOfPages",
                    controllerUtil.countNumOfPages(foodInfoService.countAllFood(), Constants.PAGE_SIZE));

        } else {
            request.setAttribute("meals", mealService.findAllMeals(Constants.PAGE_SIZE, offset));
            request.setAttribute("numOfPages",
                    controllerUtil.countNumOfPages(mealService.countAllMeals(), Constants.PAGE_SIZE));
        }
        return PagesToForward.ADMIN;
    }
}
