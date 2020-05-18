package ua.training.model.jdbc;

import ua.training.model.constants.FoodConst;
import ua.training.model.constants.MealConst;
import ua.training.model.dao.GenericDao;
import ua.training.model.dao.MealDao;
import ua.training.model.entity.Meal;

import java.rmi.ServerException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCMealDao implements MealDao {
    private Connection connection;

    public JDBCMealDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Meal create(Meal meal) throws ServerException {

        try (PreparedStatement query = connection.prepareStatement(MealConst.CREATE, Statement.RETURN_GENERATED_KEYS)) {

            query.setLong(1, meal.getUser().getId());
            query.setLong(2, meal.getFood().getId());
            query.setInt(3, meal.getAmount());
            query.setObject(4, meal.getDateTime());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                meal.setId(keys.getLong(1));
            }
            return meal;
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public Optional<Meal> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Meal> findAll() {
        return null;
    }

    @Override
    public void update(Meal entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }

}
