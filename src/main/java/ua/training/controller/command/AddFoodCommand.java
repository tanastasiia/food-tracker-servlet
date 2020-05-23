package ua.training.controller.command;

import ua.training.controller.utils.Routes;
import ua.training.controller.utils.PagesToForward;
import ua.training.model.constants.FoodConst;
import ua.training.model.dto.FoodDto;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.service.FoodInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Optional;

public class AddFoodCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FoodDto foodDto = parseFoodDto(request);

        System.out.println("foodDto " + foodDto.toEntity().toString());
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        Optional<FoodInfo> savedFoodInfo = FoodInfoService.getInstance()
                .saveFood(foodDto, user.toEntity());

        request.getSession().setAttribute("formSuccess", savedFoodInfo.isPresent() ? "foodAdded" : "foodNotAdded");

        request.getSession().removeAttribute("lastAdd");

        response.sendRedirect(Routes.HOME.getPath());
        return PagesToForward.NONE;
    }

    private FoodDto parseFoodDto(HttpServletRequest request){
        return new FoodDto.Builder()
                .setProtein(new BigDecimal(request.getParameter(FoodConst.PROTEIN.getField())))
                .setFat(new BigDecimal(request.getParameter(FoodConst.FAT.getField())))
                .setCarbs(new BigDecimal(request.getParameter(FoodConst.CARBS.getField())))
                .setCalories(Integer.parseInt(request.getParameter(FoodConst.CALORIES.getField())))
                .setName(request.getParameter(FoodConst.NAME.getField()))
                .setLocale((Locale) request.getSession().getAttribute("lang"))
                .build();

    }
}
/*    private Food parseFood(HttpServletRequest request){
        return new Food.Builder()
                .setProtein(Integer.parseInt(request.getParameter(FoodConst.PROTEIN.getField())))
                .setFat(Integer.parseInt(request.getParameter(FoodConst.FAT.getField())))
                .setCarbs(Integer.parseInt(request.getParameter(FoodConst.CARBS.getField())))
                .setCalories(Integer.parseInt(request.getParameter(FoodConst.CALORIES.getField())))
                .setName(request.getParameter(FoodConst.NAME.getField()))
                .setNameUa(request.getParameter(FoodConst.NAME_UA.getField()))
                .build();

    }*/