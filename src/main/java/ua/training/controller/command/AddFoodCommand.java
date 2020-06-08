package ua.training.controller.command;

import ua.training.utils.ControllerUtil;
import ua.training.controller.Routes;
import ua.training.controller.PagesToForward;
import ua.training.model.constants.FoodConst;
import ua.training.model.constants.FoodInfoConst;
import ua.training.model.dto.FoodDto;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.FoodInfo;
import ua.training.service.FoodInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

public class AddFoodCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FoodDto foodDto = parseFoodDto(request);

        UserDto user = controllerUtil.getUser(request);
        Boolean isGlobal = request.getParameter(FoodInfoConst.IS_GLOBAL.getField()) != null;

        Optional<FoodInfo> savedFoodInfo = FoodInfoService.getInstance().saveFood(foodDto, user.toEntity(), isGlobal);

        request.getSession().setAttribute("formSuccess", savedFoodInfo.isPresent() ? "foodAdded" : "foodNotAdded");
        request.getSession().removeAttribute("lastAdd");

        response.sendRedirect(Routes.HOME.getPath());
        return PagesToForward.NONE;
    }

    private FoodDto parseFoodDto(HttpServletRequest request) {
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