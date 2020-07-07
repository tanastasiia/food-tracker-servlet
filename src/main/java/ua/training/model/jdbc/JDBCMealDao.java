package ua.training.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.Mapper;
import ua.training.model.dao.MealDao;
import ua.training.model.entity.Meal;

import java.rmi.ServerException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCMealDao implements MealDao {

    private Connection connection;
    private Logger logger = LogManager.getLogger(JDBCMealDao.class.getName());

    public JDBCMealDao(Connection connection) {
        this.connection = connection;
    }

    private static String CREATE =
            "INSERT INTO meals (user_id, food_id, amount_g, date_time) " +
                    "VALUES(?, ?, ?, ?)";

    private static String FIND_ALL_BY_USER_ID_AND_DATE_TIME_BETWEEN =
            "SELECT * " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? AND meals.date_time BETWEEN ? AND ? " +
                    "ORDER BY meals.date_time DESC";

    private static String FIND_ALL_BY_USER_ID_PAGINATION =
            "SELECT meals.id, user_id, food_id, amount_g, date_time, food.name, " +
                    "food.name_ua, food.carbs_mg, food.fat_mg, food.protein_mg, food.calories " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? " +
                    "ORDER BY meals.date_time DESC " +
                    "LIMIT ? OFFSET ?";

    private String FIND_ALL_PAGINATION = "SELECT * " +
            "FROM meals " +
            "INNER JOIN food ON meals.food_id=food.id " +
            "INNER JOIN users ON meals.user_id=users.id " +
            "ORDER BY meals.date_time DESC " +
            "LIMIT ? OFFSET ?";

    private static String FIND_ALL_BY_USER_ID_AND_DATE_TIME_BETWEEN_PAGINATION =
            "SELECT * " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? AND meals.date_time BETWEEN ? AND ? " +
                    "ORDER BY meals.date_time DESC " +
                    "LIMIT ? OFFSET ?";


    private static String COUNT_BY_USER_ID = "SELECT COUNT(*) AS count FROM meals WHERE user_id=?";

    private static String COUNT_ALL = "SELECT COUNT(*) AS count FROM meals";

    private static String COUNT_BY_USER_ID_AND_DATE_TIME_BETWEEN =
            "SELECT COUNT(*) AS count " +
                    "FROM meals " +
                    "WHERE user_id=? AND date_time BETWEEN ? AND ?";



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
    public Meal create(Meal meal) throws ServerException {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

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
            logger.error("Error creating meal=" + meal + ": " + e);
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public List<Meal> findAll(int limit, int offset) throws ServerException {
        List<Meal> meals = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_PAGINATION)) {
            query.setInt(1, limit);
            query.setInt(2, offset);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                meals.add(Mapper.mealFoodMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error findAll: " + e);
            throw new ServerException(e.getMessage());
        }
        return meals;
    }

    @Override
    public List<Meal> findAllByUserId(Long userId, int limit, int offset) throws ServerException {
        List<Meal> meals = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_BY_USER_ID_PAGINATION)) {
            query.setLong(1, userId);
            query.setInt(2, limit);
            query.setInt(3, offset);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                meals.add(Mapper.mealFoodWithoutUserMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding by userId=" + userId + ": " + e);
            throw new ServerException(e.getMessage());
        }
        return meals;
    }

    @Override
    public List<Meal> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime dateTime1, LocalDateTime dateTime2) throws ServerException {
        List<Meal> meals = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_BY_USER_ID_AND_DATE_TIME_BETWEEN)) {
            query.setLong(1, userId);
            query.setObject(2, dateTime1);
            query.setObject(3, dateTime2);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                meals.add(Mapper.mealFoodWithoutUserMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding by userId=" + userId + ": " + e);
            throw new ServerException(e.getMessage());
        }
        return meals;
    }

    @Override
    public List<Meal> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime dateTime1, LocalDateTime dateTime2,
                                                     int limit, int offset) throws ServerException {
        List<Meal> meals = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_BY_USER_ID_AND_DATE_TIME_BETWEEN_PAGINATION)) {
            query.setLong(1, userId);
            query.setObject(2, dateTime1);
            query.setObject(3, dateTime2);
            query.setInt(4, limit);
            query.setInt(5, offset);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                meals.add(Mapper.mealFoodWithoutUserMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding by userId=" + userId + ": " + e);
            throw new ServerException(e.getMessage());
        }
        return meals;
    }

    @Override
    public int countAllUserMeals(Long userId) throws ServerException {
        int count = 0;
        try (PreparedStatement query = connection.prepareStatement(COUNT_BY_USER_ID)) {
            query.setLong(1, userId);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            logger.error("Error counting by userId=" + userId + ": " + e);
            throw new ServerException(e.getMessage());
        }
        return count;
    }

    @Override
    public int countAllMeals() throws ServerException {
        int count = 0;
        try (PreparedStatement query = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            logger.error("Error counting: " + e);
            throw new ServerException(e.getMessage());
        }
        return count;
    }

}
