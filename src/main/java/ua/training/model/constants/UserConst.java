package ua.training.model.constants;

import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.util.function.Function;

public enum UserConst implements EntityConst<User> {
    ID("id","id", User::getId),
    USERNAME("username", "username", User::getUsername),
    FIRST_NAME("firstName", "first_name", User::getFirstName),
    LAST_NAME("lastName", "last_name", User::getLastName),
    ROLE("role", "role", User::getRole),
    PASSWORD("password", "password", User::getPassword),
    HEIGHT("height", "height", User::getHeight),
    WEIGHT("weight", "weight", User::getWeight),
    GENDER("gender", "gender", User::getGender),
    ACTIVITY_LEVEL("activityLevel", "activity_level", User::getActivityLevel),
    AGE("age", "age", User::getAge);


    UserConst(String field, String column, Function<User, ?> fieldGetter){
        this.field = field;
        this.column = column;
        this.fieldGetter = fieldGetter;
    }
    private String column;
    private String field;
    private Function<User, ?> fieldGetter;

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public Function<User, ?> fieldGetter() {
        return fieldGetter;
    }
}
