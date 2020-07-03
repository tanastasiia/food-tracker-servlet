package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.model.entity.User;
import ua.training.service.MealService;
import ua.training.utils.Constants;
import ua.training.utils.ControllerUtil;
import ua.training.utils.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class StatisticsCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private MealService mealService = MealService.getInstance();

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Locale locale = controllerUtil.getLocale(request);

        request.setAttribute("caloriesNorm", ServiceUtil.getInstance().countCaloriesNorm(controllerUtil.getUser(request)));
        request.setAttribute("userStat", mealService.todaysUserStatistics(controllerUtil.getUserId(request)));

        if (request.getParameter("tab") == null || request.getParameter("tab").equals("1")) {
            request.setAttribute("todaysMeals", mealService.todaysUserMeals(controllerUtil.getUserId(request), locale));
        } else {
            int page = controllerUtil.getPage(request);
            int offset = controllerUtil.getOffset(page, Constants.PAGE_SIZE);

            request.setAttribute("allMeals",
                    mealService.findAllUserMeals(controllerUtil.getUserId(request), locale, Constants.PAGE_SIZE, offset));
            request.setAttribute("numOfMealsPages",
                    controllerUtil.countNumOfPages(mealService.countAllUsersMeals(controllerUtil.getUserId(request)), Constants.PAGE_SIZE));
        }

        return PagesToForward.STATISTICS;
    }


}
