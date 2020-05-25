package ua.training.model.constants;

import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;

import java.util.function.Function;

public enum FoodConst implements EntityConst {

    ID("id", "id"),
    NAME("name", "name"),
    NAME_UA("nameUa", "name_ua"),
    CARBS("carbs", "carbs_mg"),
    PROTEIN("protein", "protein_mg"),
    FAT("fat", "fat_mg"),
    CALORIES("calories", "calories");

    FoodConst(String field, String column) {
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
