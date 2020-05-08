package ua.training.model;

import ua.training.model.entity.User;
import ua.training.model.constants.UserConst;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper {

    static User userMap(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getLong(UserConst.ID))
                .setPassword(resultSet.getString(UserConst.PASSWORD))
                .setUsername(resultSet.getString(UserConst.USERNAME))
                .setRole(resultSet.getString(UserConst.ROLE))
                .build();
    }
}
