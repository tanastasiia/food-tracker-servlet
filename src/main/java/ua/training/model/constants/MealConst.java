package ua.training.model.constants;

import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.Meal;

import java.util.function.Function;

public enum MealConst implements EntityConst {
    ID("id", "id"),
    USER("user", "user_id"),
    FOOD("food", "food_id"),
    AMOUNT("amount", "amount_g"),
    DATE_TIME("dateTime", "date_time");


    MealConst(String field, String column) {
        this.field = field;
        this.column = column;
    }

    private String column;
    private String field;

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public String getField() {
        return field;
    }

}
