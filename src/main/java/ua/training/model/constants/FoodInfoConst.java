package ua.training.model.constants;

import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;

import java.util.function.Function;

public enum FoodInfoConst implements EntityConst<FoodInfo>{
    ID("id","id", FoodInfo::getId),
    FOOD("food", "food_id", FoodInfo::getFood),
    USER("user", "adder_user_id", FoodInfo::getUser),
    IS_GLOBAL("user", "adder_user_id", FoodInfo::isGlobal);


    FoodInfoConst(String field, String column, Function<FoodInfo, ?> fieldGetter){
        this.field = field;
        this.column = column;
        this.fieldGetter = fieldGetter;
    }
    private String column;
    private String field;
    private Function<FoodInfo, ?> fieldGetter;

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public  Function<FoodInfo, ?> fieldGetter() {
        return fieldGetter;
    }
}
