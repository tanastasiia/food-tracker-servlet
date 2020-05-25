package ua.training.model.jdbc;

import ua.training.model.Mapper;
import ua.training.model.dao.MealDao;
import ua.training.model.entity.Meal;

import java.rmi.ServerException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCMealDao implements MealDao {
    private Connection connection;

    public JDBCMealDao(Connection connection) {
        this.connection = connection;
    }

    private static String AMOUNT_COLUMN = "amount";
    private static String DATE_TIME_COLUMN = "date_time";


    private static String CREATE =
            "INSERT INTO meals (user_id, food_id, amount_g, date_time) " +
                    "VALUES(?, ?, ?, ?)";

    private static String FIND_ALL_BY_USER_ID =
            "SELECT meals.id, user_id, food_id, amount_g, date_time, food.name, " +
                    "food.name_ua, food.carbs_mg, food.fat_mg, food.protein_mg, food.calories " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? " +
                    "ORDER BY meals.date_time";

    private static String FIND_ALL_BY_USER_ID_AND_DATE_TIME_BETWEEN =
            "SELECT * " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? AND meals.date_time BETWEEN ? AND ? " +
                    "ORDER BY meals.date_time";

    private static String FIND_ALL_BY_USER_ID_PAGINATION =
            "SELECT meals.id, user_id, food_id, amount_g, date_time, food.name, " +
                    "food.name_ua, food.carbs_mg, food.fat_mg, food.protein_mg, food.calories " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? " +
                    "ORDER BY meals.date_time " +
                    "LIMIT ? OFFSET ?";

    private static String FIND_ALL_BY_USER_ID_AND_DATE_TIME_BETWEEN_PAGINATION =
            "SELECT * " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? AND meals.date_time BETWEEN ? AND ? " +
                    "ORDER BY meals.date_time " +
                    "LIMIT ? OFFSET ?";


    private static String FIND_ALL_STATISTICS_BY_USER_ID_AND_DATE_TIME_BETWEEN =
            "SELECT meals.amount, food.fat, food.carbs, food.protein, food.calories " +
                    "FROM meals " +
                    "INNER JOIN food ON meals.food_id=food.id " +
                    "WHERE  meals.user_id=? " +
                    "ORDER BY meals.date_time";

    private static String COUNT_BY_USER_ID =  "SELECT COUNT(*) AS count FROM meals WHERE user_id=?";
    private static String COUNT_ALL =  "SELECT COUNT(*) AS count FROM meals";
    private static String COUNT_BY_USER_ID_AND_DATE_TIME_BETWEEN =
            "SELECT COUNT(*) AS count " +
                    "FROM meals " +
                    "WHERE user_id=? AND date_time BETWEEN ? AND ?";

    private String FIND_ALL_PAGINATION = "SELECT * " +
            "FROM meals " +
            "INNER JOIN food ON meals.food_id=food.id " +
            "INNER JOIN users ON meals.user_id=users.id " +
            "ORDER BY meals.date_time " +
            "LIMIT ? OFFSET ?";

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
            throw new ServerException(e.getMessage());
        }
        return meals;
    }
    @Override
    public int countAllMeals() throws ServerException {
        int count = 0;
        try (PreparedStatement query = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt( "count");
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        return count;
    }

    @Override
    public void update(Meal entity) {

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
    public List<Meal> findByUserId(Long userId) throws ServerException {
        List<Meal> meals = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_BY_USER_ID)) {
            query.setLong(1, userId);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                meals.add(Mapper.mealFoodWithoutUserMap(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        return meals;
    }
    @Override
    public List<Meal> findByUserId(Long userId, int limit, int offset) throws ServerException {
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
                count = resultSet.getInt( "count");
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        return count;
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
            throw new ServerException(e.getMessage());
        }
        return meals;
    }

    @Override
    public List<Meal> findAllStatisticsByUserIdAndDateTimeBetween(Long userId, LocalDateTime dateTime1, LocalDateTime dateTime2) throws ServerException {
        List<Meal> meals = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_STATISTICS_BY_USER_ID_AND_DATE_TIME_BETWEEN)) {
            query.setLong(1, userId);
            query.setObject(2, dateTime1);
            query.setObject(3, dateTime2);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                meals.add(Mapper.mealFoodWithoutUserMap(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        return meals;
    }

}
