package ua.training.model;

import ua.training.model.constants.FoodConst;
import ua.training.model.constants.FoodInfoConst;
import ua.training.model.constants.MealConst;
import ua.training.model.dto.UserMealStatDto;
import ua.training.model.entity.*;
import ua.training.model.constants.UserConst;
import ua.training.model.jdbc.JDBCFoodDao;
import ua.training.model.jdbc.JDBCUserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public interface Mapper {

    static User userMap(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getLong(UserConst.ID.getColumn()))
                .setUsername(resultSet.getString(UserConst.USERNAME.getColumn()))
                .setFirstName(resultSet.getString(UserConst.FIRST_NAME.getColumn()))
                .setLastName(resultSet.getString(UserConst.LAST_NAME.getColumn()))
                .setRole(resultSet.getString(UserConst.ROLE.getColumn()))
                .setPassword(resultSet.getString(UserConst.PASSWORD.getColumn()))
                .setHeight(resultSet.getInt(UserConst.HEIGHT.getColumn()))
                .setWeight(resultSet.getInt(UserConst.WEIGHT.getColumn()))
                .setGender(resultSet.getString(UserConst.GENDER.getColumn()))
                .setActivityLevel(resultSet.getString(UserConst.ACTIVITY_LEVEL.getColumn()))
                .setAge(resultSet.getInt(UserConst.AGE.getColumn()))
                .build();
    }
    static Food foodMap(ResultSet resultSet) throws SQLException {
        return new Food.Builder()
                .setId(resultSet.getLong(FoodInfoConst.FOOD.getColumn()))
                .setName(resultSet.getString(FoodConst.NAME.getColumn()))
                .setNameUa(resultSet.getString(FoodConst.NAME_UA.getColumn()))
                .setCarbs(resultSet.getInt(FoodConst.CARBS.getColumn()))
                .setFat(resultSet.getInt(FoodConst.FAT.getColumn()))
                .setProtein(resultSet.getInt(FoodConst.PROTEIN.getColumn()))
                .setCalories(resultSet.getInt(FoodConst.CALORIES.getColumn()))
                .build();
    }
    static FoodInfo foodInfoMap(ResultSet resultSet) throws SQLException {
        return new FoodInfo.Builder()
                .setId(resultSet.getLong(FoodInfoConst.ID.getColumn()))
                .setFood(foodMap(resultSet))
                .setUser(userMap(resultSet))
                .setIsGlobal(resultSet.getBoolean(FoodInfoConst.IS_GLOBAL.getColumn()))
                .build();
    }

    /*static MealFoodDto mealFoodMap(ResultSet resultSet) throws SQLException {
        LocalDateTime dateTime = resultSet.getTimestamp(MealConst.DATE_TIME).toLocalDateTime();
        return new MealFoodDto(foodMap(resultSet), resultSet.getInt(MealConst.AMOUNT),
                dateTime.toLocalDate(), dateTime.toLocalTime());
    }*/

    static Meal mealFoodMap(ResultSet resultSet) throws SQLException {
        LocalDateTime dateTime = resultSet.getTimestamp(MealConst.DATE_TIME.getColumn()).toLocalDateTime();
        return new Meal.Builder()
                .setFood(foodMap(resultSet))
                .setAmount(resultSet.getInt(MealConst.AMOUNT.getColumn()))
                .setDateTime(resultSet.getTimestamp(MealConst.DATE_TIME.getColumn()).toLocalDateTime())
                .build();
    }

}
