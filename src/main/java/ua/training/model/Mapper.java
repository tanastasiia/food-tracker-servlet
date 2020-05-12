package ua.training.model;

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
}
