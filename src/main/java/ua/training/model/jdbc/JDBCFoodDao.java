package ua.training.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.LoginCommand;
import ua.training.model.Mapper;
import ua.training.model.dao.FoodDao;
import ua.training.model.entity.Food;

import java.rmi.ServerException;
import java.sql.*;
import java.util.Optional;

public class JDBCFoodDao implements FoodDao {

    private Connection connection;
    private Logger logger = LogManager.getLogger(JDBCFoodDao.class.getName());

    private String FIND_BY_NAME = "select * from food WHERE name=?";

    private String CREATE = "INSERT INTO food (name, name_ua, carbs_mg, fat_mg, protein_mg, calories) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private String FIND_BY_ID = "SELECT * FROM food WHERE id=?";

    public JDBCFoodDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Food> findById(Long id) throws ServerException {
        Optional<Food> food = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                food = Optional.of(Mapper.foodMap(resultSet));
            }
        } catch (Exception e) {
            logger.error("Error finding by id=" + id + ": " + e);
            throw new ServerException(e.getMessage());
        }
        return food;
    }


    @Override
    public void close() throws ServerException {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Error closing: " + e);
            throw new ServerException(e.getMessage());
        }
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
            logger.error("Error creating food=" + food + ": " + e);
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
            logger.error("Error finding by name=" + name + ": " + e);
            throw new ServerException(e.getMessage());
        }
        return food;
    }


}
