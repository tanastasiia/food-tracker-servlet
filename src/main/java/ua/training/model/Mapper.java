package ua.training.model;

import ua.training.model.constants.FoodConst;
import ua.training.model.constants.FoodInfoConst;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.constants.UserConst;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper {

    static User userMap(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getLong(UserConst.ID))
                .setUsername(resultSet.getString(UserConst.USERNAME))
                .setFirstName(resultSet.getString(UserConst.FIRST_NAME))
                .setLastName(resultSet.getString(UserConst.LAST_NAME))
                .setRole(Role.USER.name())
                .setPassword(resultSet.getString(UserConst.PASSWORD))
                .setHeight(resultSet.getInt(UserConst.HEIGHT))
                .setWeight(resultSet.getInt(UserConst.WEIGHT))
                .setGender(resultSet.getString(UserConst.GENDER))
                .setActivityLevel(resultSet.getString(UserConst.ACTIVITY_LEVEL))
                .setAge(resultSet.getInt(UserConst.AGE))
                .build();
    }
    static Food foodMap(ResultSet resultSet) throws SQLException {
        return new Food.Builder()
                .setId(resultSet.getLong(FoodConst.ID))
                .setName(resultSet.getString(FoodConst.NAME))
                .setNameUa(resultSet.getString(FoodConst.NAME_UA))
                .setCarbs(resultSet.getInt(FoodConst.CARBS))
                .setFat(resultSet.getInt(FoodConst.FAT))
                .setProtein(resultSet.getInt(FoodConst.PROTEIN))
                .setCalories(resultSet.getInt(FoodConst.CALORIES))
                .build();
    }
    static FoodInfo foodInfoMap(ResultSet resultSet) throws SQLException {
        return new FoodInfo.Builder()
                .setId(resultSet.getLong(FoodInfoConst.ID))
                .setFood(foodMap(resultSet))
                .setUser(userMap(resultSet))
                .setIsGlobal(resultSet.getBoolean(FoodInfoConst.IS_GLOBAL))
                .build();
    }
}
