package ua.training.controller.command;

import ua.training.controller.utils.ControllerUtil;
import ua.training.controller.utils.Constants;
import ua.training.controller.utils.PagesToForward;
import ua.training.model.dto.UserDto;
import ua.training.service.MealService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class StatisticsCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private MealService mealService = MealService.getInstance();


    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserDto userDto = controllerUtil.getUser(request);
        Locale locale = controllerUtil.getLocale(request);

        request.setAttribute("caloriesNorm", UserService.getInstance().countCaloriesNorm(userDto.toEntity()));
        request.setAttribute("userStat", mealService.todaysUserStatistics(userDto.getId()));

        if (request.getParameter("tab")==null ||request.getParameter("tab").equals("1")) {

            request.setAttribute("todaysMeals", mealService.todaysUserMeals(userDto.getId(), locale));

        } else {
            int page = controllerUtil.getPage(request);
            int offset = controllerUtil.getOffset(page, Constants.PAGE_SIZE);

            request.setAttribute("allMeals",
                    mealService.findAllUserMeals(userDto.getId(), locale, Constants.PAGE_SIZE, offset));
            request.setAttribute("numOfMealsPages",
                    controllerUtil.countNumOfPages(mealService.countAllUsersMeals(userDto.getId()), Constants.PAGE_SIZE));
        }

        return PagesToForward.STATISTICS;
    }


}
