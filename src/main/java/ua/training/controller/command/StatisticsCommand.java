package ua.training.controller.command;

import ua.training.controller.utils.PagesToForward;
import ua.training.model.dto.UserDto;
import ua.training.service.MealService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class StatisticsCommand implements Command {

    private int pageSize = 2;
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        request.setAttribute("caloriesNorm", UserService.getInstance().countCaloriesNorm(userDto.toEntity()));
        request.setAttribute("userStat", MealService.getInstance().todaysUserStatistics(userDto.getId()));




        request.setAttribute("todaysMeals",
                    MealService.getInstance().todaysUserMeals(userDto.getId(), (Locale)request.getSession().getAttribute("lang")));



        int page=1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        int offset = (page-1)*pageSize;
        int numOfMeals = MealService.getInstance().countAllUsersMeals(userDto.getId());

        request.setAttribute("allMeals", MealService.getInstance().findAllUserMeals(userDto.getId(),
                (Locale) request.getSession().getAttribute("lang"), pageSize, offset));
        request.setAttribute("numOfMealsPages" , (int)Math.ceil((double)numOfMeals / pageSize));


        return PagesToForward.STATISTICS;
    }
}
