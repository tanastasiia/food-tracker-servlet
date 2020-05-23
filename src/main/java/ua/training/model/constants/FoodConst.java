package ua.training.model.constants;

import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;

import java.util.function.Function;

public enum FoodConst implements EntityConst<Food> {

    ID("id","id", Food::getId),
    NAME("name", "name", Food::getName),
    NAME_UA("nameUa", "name_ua",Food::getNameUa),
    CARBS("carbs", "carbs_mg", Food::getCarbs),
    PROTEIN("protein", "protein_mg", Food::getProtein),
    FAT("fat", "fat_mg", Food::getFat),
    CALORIES("calories", "calories", Food::getCalories);

    FoodConst(String field, String column, Function<Food, ?> fieldGetter){
        this.field = field;
        this.column = column;
        this.fieldGetter = fieldGetter;
    }
    private String column;
    private String field;
    private Function<Food, ?> fieldGetter;

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public Function<Food, ?> fieldGetter() {
        return fieldGetter;
    }
}
