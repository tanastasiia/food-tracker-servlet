package ua.training.model.constants;

import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.Meal;

import java.util.function.Function;

public enum MealConst implements EntityConst<Meal>{
    ID("id","id", Meal::getId),
    USER("user", "user_id", Meal::getUser),
    FOOD("food","food_id", Meal::getFood),
    AMOUNT("amount", "amount_g", Meal::getAmount),
    DATE_TIME("dateTime", "date_time", Meal::getDateTime);


    MealConst(String field, String column, Function<Meal, ?> fieldGetter){
        this.field = field;
        this.column = column;
        this.fieldGetter = fieldGetter;

    }
    private String column;
    private String field;
    private Function<Meal, ?> fieldGetter;

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public Function<Meal, ?> fieldGetter() {
        return fieldGetter;
    }
}
