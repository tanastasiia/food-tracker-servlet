package ua.training.model.constants;

import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.util.function.Function;

public enum UserConst implements EntityConst {
    ID("id", "id"),
    USERNAME("username", "username"),
    FIRST_NAME("firstName", "first_name"),
    LAST_NAME("lastName", "last_name"),
    ROLE("role", "role"),
    PASSWORD("password", "password"),
    HEIGHT("height", "height"),
    WEIGHT("weight", "weight"),
    GENDER("gender", "gender"),
    ACTIVITY_LEVEL("activityLevel", "activity_level"),
    DATE_OF_BIRTH("dateOfBirth", "date_of_birth");


    UserConst(String field, String column) {
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
