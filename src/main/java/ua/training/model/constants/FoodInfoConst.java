package ua.training.model.constants;

import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;

import java.util.function.Function;

public enum FoodInfoConst implements EntityConst {
    ID("id", "id"),
    FOOD("food", "food_id"),
    USER("user", "adder_user_id"),
    IS_GLOBAL("isGlobal", "is_global");


    FoodInfoConst(String field, String column) {
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
