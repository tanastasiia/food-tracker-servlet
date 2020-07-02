package ua.training.utils.validation;

import ua.training.model.constants.FoodConst;
import ua.training.model.constants.MealConst;
import ua.training.model.constants.UserConst;
import ua.training.model.dto.FoodDto;
import ua.training.model.dto.MealDto;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public void validate(FoodDto foodDto) throws ValidationException {

        List<ValidationError> errors = new ArrayList<>();

        if (foodDto.getCarbs().compareTo(BigDecimal.ZERO) < 0) {
            errors.add(new ValidationError(FoodConst.CARBS.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }
        if (foodDto.getProtein().compareTo(BigDecimal.ZERO) < 0) {
            errors.add(new ValidationError(FoodConst.PROTEIN.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }
        if (foodDto.getFat().compareTo(BigDecimal.ZERO) < 0) {
            errors.add(new ValidationError(FoodConst.FAT.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }
        if (foodDto.getCalories() < 0) {
            errors.add(new ValidationError(FoodConst.CALORIES.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }

        if (!foodDto.getName().isEmpty() && foodDto.getName().length() > 45) {
            errors.add(new ValidationError(FoodConst.NAME.getField(), ValidationErrorMessages.LENGTH_MUST_BE_LESS_THAN_45));
        }
        if (!foodDto.getNameUa().isEmpty() && foodDto.getNameUa().length() > 45) {
            errors.add(new ValidationError(FoodConst.NAME_UA.getField(), ValidationErrorMessages.LENGTH_MUST_BE_LESS_THAN_45));
        }
        if(foodDto.getName().isEmpty() && foodDto.getNameUa().isEmpty()){
            errors.add(new ValidationError("names_empty", ValidationErrorMessages.FOOD_NAMES_EMPTY));
        }

        if (!errors.isEmpty()) {
            errors.forEach(e -> System.out.println(e.getField() + "  " + e.getMessage()));
            throw new ValidationException(errors);
        }
    }

    public void validate(User user) throws ValidationException {

        List<ValidationError> errors = new ArrayList<>();

        if (user.getFirstName().isEmpty()) {
            errors.add(new ValidationError(UserConst.FIRST_NAME.getField(), ValidationErrorMessages.EMPTY_FIELD));
        }
        if (user.getFirstName().length() > 45) {
            errors.add(new ValidationError(UserConst.FIRST_NAME.getField(), ValidationErrorMessages.LENGTH_MUST_BE_LESS_THAN_45));
        }
        if (user.getLastName().isEmpty()) {
            errors.add(new ValidationError(UserConst.LAST_NAME.getField(), ValidationErrorMessages.EMPTY_FIELD));
        }
        if (user.getLastName().length() > 45) {
            errors.add(new ValidationError(UserConst.LAST_NAME.getField(), ValidationErrorMessages.LENGTH_MUST_BE_LESS_THAN_45));
        }
        if (user.getHeight() < 0) {
            errors.add(new ValidationError(UserConst.HEIGHT.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }
        if (user.getWeight() < 0) {
            errors.add(new ValidationError(UserConst.WEIGHT.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }
        if (user.getWeight() < 0) {
            errors.add(new ValidationError(UserConst.WEIGHT.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void validate(MealDto mealDto) throws ValidationException {
        List<ValidationError> errors = new ArrayList<>();

        if (mealDto.getFoodName().isEmpty()) {
            errors.add(new ValidationError(MealConst.FOOD.getField(), ValidationErrorMessages.EMPTY_FIELD));
        }
        if (mealDto.getFoodName().length() > 45) {
            errors.add(new ValidationError(MealConst.FOOD.getField(), ValidationErrorMessages.LENGTH_MUST_BE_LESS_THAN_45));
        }
        if (mealDto.getAmount() <= 0) {
            errors.add(new ValidationError(MealConst.AMOUNT.getField(), ValidationErrorMessages.WRONG_NUMBER));
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
