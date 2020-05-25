package ua.training.model.jdbc;

import ua.training.controller.command.LogoutCommand;
import ua.training.model.Mapper;
import ua.training.model.dao.FoodDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class JDBCFoodDao implements FoodDao {

    private Connection connection;
    private static String ID_COLUMN = "id";
    private static String NAME_COLUMN = "name";
    private static String NAME_UA_COLUMN = "name_ua";
    private static String CARBS_COLUMN = "carbs_mg";
    private static String FAT_COLUMN = "fat_mg";
    private static String PROTEIN_COLUMN = "protein_mg";
    private static String CALORIES_COLUMN = "calories";

    String FIND_ALL = "select * from food";
    String FIND_BY_NAME = "select * from food WHERE name=?";

    String FIND_BY_NAME_UA = "select * from food WHERE name_ua=?";

    String CREATE = "INSERT INTO food (name, name_ua, carbs_mg, fat_mg, protein_mg, calories) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    public JDBCFoodDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Food create(Food food) throws ServerException {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, food.getName());
            query.setString(2, food.getNameUa());
            query.setInt(3, food.getCarbs());
            query.setInt(4, food.getFat());
            query.setInt(5, food.getProtein());
            query.setInt(6, food.getCalories());
            query.executeUpdate();


            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                food.setId(keys.getLong(1));
            }
            return food;
        } catch (SQLException e) {
            Logger.getLogger(JDBCFoodDao.class.getName()).severe("Food creation error: " + e.getMessage());
            throw new ServerException(e.getMessage());
        }

    }

    @Override
    public Optional<Food> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Food> findAll() {
        return null;
    }

    @Override
    public void update(Food entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() throws ServerException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public Optional<Food> findByName(String name) throws ServerException {
        Optional<Food> food = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_NAME)) {
            query.setString(1, name);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                food = Optional.of(Mapper.foodMap(resultSet));
            }
        } catch (Exception e) {
            Logger.getLogger(JDBCFoodDao.class.getName()).severe("findByName eror: " + e.getMessage());
            throw new ServerException(e.getMessage());
        }
        return food;
    }

}
