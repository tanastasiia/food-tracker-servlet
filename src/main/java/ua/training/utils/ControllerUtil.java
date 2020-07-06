package ua.training.utils;

import ua.training.model.constants.FoodConst;
import ua.training.model.constants.UserConst;
import ua.training.model.dto.FoodDto;
import ua.training.model.dto.MealDto;
import ua.training.model.dto.PasswordDto;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.utils.validation.ValidationError;
import ua.training.utils.validation.ValidationException;
import ua.training.utils.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class ControllerUtil {
    private Validator validator = new Validator();

    private static class Holder {
        private static final ControllerUtil INSTANCE = new ControllerUtil();
    }

    public static ControllerUtil getInst() {
        return Holder.INSTANCE;
    }

    public Locale getLocale(HttpServletRequest request) {
        return (Locale) request.getSession().getAttribute("lang");
    }

    public User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    public long getUserId(HttpServletRequest request) {
        return (long) request.getSession().getAttribute("userId");
    }

    public String getRole(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("role");
    }

    public boolean isMethodGet(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET");
    }

    public boolean isAdmin(HttpServletRequest request) {
        return (boolean) request.getSession().getAttribute("isAdmin");
    }

    public int getPage(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        return page;
    }

    public int getOffset(int page, int pageSize) {
        return (page - 1) * pageSize;
    }

    public int countNumOfPages(int numOfRecords, int pageSize) {
        return (int) Math.ceil((double) numOfRecords / pageSize);
    }

    public void setAddSuccessOrFailAttributesHomePage(HttpServletRequest request, Boolean isPresent, String type) {
        request.getSession().setAttribute("formSuccess", isPresent ? type + "Added" : type + "NotAdded");
        request.getSession().removeAttribute("lastAdd");
    }

    public void setErrorAttributes(HttpServletRequest request, List<ValidationError> errors){
        errors.forEach(error -> request.setAttribute("error_" + error.getField(), error.getMessage()));
    }

    public FoodDto parseFoodDto(HttpServletRequest request) throws ValidationException {
        FoodDto foodDto = new FoodDto.Builder()
                .setProtein(new BigDecimal(request.getParameter(FoodConst.PROTEIN.getField())))
                .setFat(new BigDecimal(request.getParameter(FoodConst.FAT.getField())))
                .setCarbs(new BigDecimal(request.getParameter(FoodConst.CARBS.getField())))
                .setCalories(Integer.parseInt(request.getParameter(FoodConst.CALORIES.getField())))
                .setName(request.getParameter(FoodConst.NAME.getField()))
                .setNameUa(request.getParameter(FoodConst.NAME_UA.getField()))
                .setLocale((Locale) request.getSession().getAttribute("lang"))
                .build();
        validator.validate(foodDto);
        return foodDto;
    }

    public MealDto parseMealDto(HttpServletRequest request) throws ValidationException {
        MealDto mealDto = new MealDto.Builder()
                .setFoodName(request.getParameter("foodName"))
                .setAmount(Integer.parseInt(request.getParameter("amount"))).build();

        validator.validate(mealDto);
        return mealDto;
    }

    public User parseUserUpdate(HttpServletRequest request) throws ValidationException {
        User user = new User.Builder()
                .setFirstName(request.getParameter(UserConst.FIRST_NAME.getField()))
                .setLastName(request.getParameter(UserConst.LAST_NAME.getField()))
                .setHeight(Integer.parseInt(request.getParameter(UserConst.HEIGHT.getField())))
                .setWeight(Integer.parseInt(request.getParameter(UserConst.WEIGHT.getField())))
                .setGender(request.getParameter(UserConst.GENDER.getField()))
                .setActivityLevel(request.getParameter(UserConst.ACTIVITY_LEVEL.getField()))
                .setDateOfBirth(LocalDate.parse(request.getParameter(UserConst.DATE_OF_BIRTH.getField())))
                .build();
        validator.validate(user);
        return user;
    }

    public User parseRegUser(HttpServletRequest request) throws ValidationException {
        User user = new User.Builder()
                .setUsername(request.getParameter(UserConst.USERNAME.getField()))
                .setFirstName(request.getParameter(UserConst.FIRST_NAME.getField()))
                .setLastName(request.getParameter(UserConst.LAST_NAME.getField()))
                .setRole(Role.ROLE_USER.name())
                .setPassword(request.getParameter(UserConst.PASSWORD.getField()))
                .setHeight(Integer.parseInt(request.getParameter(UserConst.HEIGHT.getField())))
                .setWeight(Integer.parseInt(request.getParameter(UserConst.WEIGHT.getField())))
                .setGender(request.getParameter(UserConst.GENDER.getField()))
                .setActivityLevel(request.getParameter(UserConst.ACTIVITY_LEVEL.getField()))
                .setDateOfBirth(LocalDate.parse(request.getParameter(UserConst.DATE_OF_BIRTH.getField())))
                .build();
        validator.validate(user);
        return user;
    }
}
