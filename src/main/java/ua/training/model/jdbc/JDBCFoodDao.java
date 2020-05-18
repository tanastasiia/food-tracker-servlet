package ua.training.model.jdbc;

import ua.training.model.Mapper;
import ua.training.model.constants.FoodConst;
import ua.training.model.constants.FoodInfoConst;
import ua.training.model.constants.UserConst;
import ua.training.model.dao.FoodDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCFoodDao implements FoodDao {

    private Connection connection;

    public JDBCFoodDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Food create(Food food) throws ServerException {
        try (PreparedStatement query = connection.prepareStatement(FoodConst.CREATE, Statement.RETURN_GENERATED_KEYS)) {

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
    public void close() {

    }

    @Override
    public Optional<Food> findByName(String name) throws ServerException {
        Optional<Food> food = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FoodConst.FIND_BY_NAME)) {
            query.setString(1, name);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                food = Optional.of(Mapper.foodMap(resultSet));
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return food;
    }

    @Override
    public Optional<Food> findByNameUa(String nameUa) throws ServerException {
        return Optional.empty();
    }
}
