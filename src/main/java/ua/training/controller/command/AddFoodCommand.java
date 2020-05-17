package ua.training.controller.command;

import ua.training.controller.utils.Endpoints;
import ua.training.controller.utils.PagesToForward;
import ua.training.controller.utils.Params;
import ua.training.model.constants.FoodConst;
import ua.training.model.constants.UserConst;
import ua.training.model.entity.Food;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.service.FoodInfoService;
import ua.training.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFoodCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Food food = parseFood(request);
        User user = (User) request.getSession().getAttribute("user");

        if(!FoodInfoService.getInstance().findFoodByFoodNameAndUser(food.getName(), user.getId()).isPresent()){
            food = FoodService.getInstance().addFood(food);
            FoodInfoService.getInstance().addUserFood(food, user);
            request.getSession().setAttribute("formSuccess", "foodAdded");
        } else{
            request.getSession().setAttribute("formSuccess", "foodNotAdded");
        }

        request.getSession().removeAttribute("lastAdd");
        response.sendRedirect(Endpoints.HOME.getPath());
        return PagesToForward.NONE;
    }

    private Food parseFood(HttpServletRequest request){
        return new Food.Builder()
                .setProtein(Integer.parseInt(request.getParameter("protein"/*FoodConst.PROTEIN*/)))
                .setFat(Integer.parseInt(request.getParameter("fat"/*FoodConst.FAT*/)))
                .setCarbs(Integer.parseInt(request.getParameter("carbs"/*FoodConst.CARBS*/)))
                .setCalories(Integer.parseInt(request.getParameter(FoodConst.CALORIES)))
                .setName(request.getParameter(FoodConst.NAME))
                .setNameUa(request.getParameter(FoodConst.NAME_UA))
                .build();

    }
}
