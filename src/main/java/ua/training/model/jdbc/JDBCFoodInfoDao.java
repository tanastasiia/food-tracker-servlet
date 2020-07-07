package ua.training.model.jdbc;

import ua.training.model.Mapper;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class JDBCFoodInfoDao implements FoodInfoDao {

    private Connection connection;

    private String FIND_ALL_BY_FOOD_NAME_OR_FOOD_NAME_UA_AND_USER_ID_OR_GLOBAL =
            "SELECT * FROM food_info " +
                    "INNER JOIN food ON food_info.food_id=food.id " +
                    "LEFT OUTER JOIN users ON food_info.adder_user_id=users.id " +
                    "WHERE  (food.name=? OR food.name_ua=?)AND (food_info.adder_user_id=? OR food_info.is_global=TRUE)";

    private static String COUNT_ALL = "SELECT COUNT(*) AS count FROM food_info";

    private String FIND_ALL_PAGINATION = "SELECT * " +
            "FROM food_info " +
            "LEFT OUTER JOIN food ON food_info.food_id=food.id " +
            "LEFT OUTER JOIN users ON food_info.adder_user_id=users.id " +
            "LIMIT ? OFFSET ?";

    private static String CREATE = "INSERT INTO food_info (food_id, adder_user_id,  is_global) VALUES (?, ?, ?)";

    private static String CREATE_FOOD = "INSERT INTO food (name, name_ua, carbs_mg, fat_mg, protein_mg, calories) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private static String FIND_ALL_FOOD_NAMES_FOR_USER = "SELECT * " +
            "FROM food_info " +
            "LEFT OUTER JOIN food ON food_info.food_id=food.id " +
            "WHERE food_info.adder_user_id = ? OR food_info.is_global=TRUE";

    private String UPDATE_FOOD_INFO =
            "UPDATE food_info " +
                    "LEFT OUTER JOIN food ON food_info.food_id=food.id " +
                    "SET food.name=?, food.name_ua=?, food.carbs_mg=?, food.fat_mg=?, food.protein_mg=?, food.calories=?, " +
                    "food_info.is_global=? " +
                    "WHERE food_info.food_id=? ";

    private String FIND_BY_FOOD_ID = "SELECT * FROM food_info " +
            "LEFT OUTER JOIN food ON food_info.food_id=food.id " +
            "WHERE food_info.food_id=? ";

    public JDBCFoodInfoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws ServerException {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(JDBCFoodInfoDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public FoodInfo create(FoodInfo foodInfo) throws ServerException {

        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            query.setLong(1, foodInfo.getFood().getId());
            query.setLong(2, foodInfo.getUser().getId());
            query.setBoolean(3, foodInfo.getIsGlobal());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                foodInfo.setId(keys.getLong(1));
            }
            return foodInfo;

        } catch (SQLException e) {
            Logger.getLogger(JDBCFoodInfoDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public FoodInfo saveFood(FoodInfo foodInfo) throws SQLException, ServerException {

        Food food = foodInfo.getFood();

        try (PreparedStatement createFoodInfoSt = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement createFoodSt = connection.prepareStatement(CREATE_FOOD, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);

            if (food.getName().isEmpty()) {
                createFoodSt.setNull(1, Types.NULL);
            } else {
                createFoodSt.setString(1, food.getName());
            }
            if (food.getNameUa().isEmpty()) {
                createFoodSt.setNull(2, Types.NULL);
            } else {
                createFoodSt.setString(2, food.getNameUa());
            }
            createFoodSt.setInt(3, food.getCarbs());
            createFoodSt.setInt(4, food.getFat());
            createFoodSt.setInt(5, food.getProtein());
            createFoodSt.setInt(6, food.getCalories());
            createFoodSt.executeUpdate();

            ResultSet foodKeys = createFoodSt.getGeneratedKeys();
            if (foodKeys.next()) {
                food.setId(foodKeys.getLong(1));
            }

            createFoodInfoSt.setLong(1, food.getId());
            createFoodInfoSt.setLong(2, foodInfo.getUser().getId());
            createFoodInfoSt.setBoolean(3, foodInfo.getIsGlobal());
            createFoodInfoSt.executeUpdate();

            ResultSet foodInfoKeys = createFoodInfoSt.getGeneratedKeys();
            if (foodInfoKeys.next()) {
                foodInfo.setId(foodInfoKeys.getLong(1));
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            Logger.getLogger(JDBCFoodInfoDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
        return foodInfo;
    }

    @Override
    public List<FoodInfo> findAll(int limit, int offset) throws ServerException {
        List<FoodInfo> foodInfos = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_PAGINATION)) {
            query.setInt(1, limit);
            query.setInt(2, offset);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                foodInfos.add(Mapper.foodInfoMap(resultSet));
            }
        } catch (SQLException e) {
            Logger.getLogger(JDBCFoodInfoDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
        return foodInfos;
    }

    @Override
    public List<FoodInfo> findAllUserFood(long userId) throws ServerException {
        List<FoodInfo> foodInfos = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_FOOD_NAMES_FOR_USER)) {
            query.setLong(1, userId);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                foodInfos.add(Mapper.foodInfoMapWithoutUser(resultSet));
            }
        } catch (SQLException e) {
            Logger.getLogger(JDBCFoodInfoDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
        return foodInfos;
    }

    /**
     * food by food name(ua or eng) if it is available for user
     *
     * @param foodName food name in ukrainian or english.
     * @param userId   user id of user querying food.
     * @return foodInfo with food with queried food name available for user with queried user id
     */
    public Optional<FoodInfo> findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(String foodName, Long userId) throws ServerException {
        Optional<FoodInfo> foodInfo = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(
                FIND_ALL_BY_FOOD_NAME_OR_FOOD_NAME_UA_AND_USER_ID_OR_GLOBAL)) {
            query.setString(1, foodName);
            query.setString(2, foodName);
            query.setLong(3, userId);

            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                foodInfo = Optional.of(Mapper.foodInfoMap(resultSet));
            }
        } catch (Exception e) {
            Logger.getLogger(JDBCFoodInfoDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
        return foodInfo;
    }

    @Override
    public int countAll() throws ServerException {
        int count = 0;
        try (PreparedStatement query = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            Logger.getLogger(JDBCFoodInfoDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
        return count;
    }

    @Override
    public void updateFoodInfo(FoodInfo foodInfo) throws ServerException {
        try (PreparedStatement query = connection.prepareStatement(UPDATE_FOOD_INFO)) {
            query.setString(1, foodInfo.getFood().getName());
            query.setString(2, foodInfo.getFood().getNameUa());
            query.setInt(3, foodInfo.getFood().getCarbs());
            query.setInt(4, foodInfo.getFood().getFat());
            query.setInt(5,foodInfo.getFood().getProtein());
            query.setInt(6, foodInfo.getFood().getCalories());
            query.setBoolean(7, foodInfo.getIsGlobal());
            query.setLong(8, foodInfo.getFood().getId());
            query.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(JDBCUserDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public Optional<FoodInfo> findByFoodId(Long foodId) throws ServerException {
        Optional<FoodInfo> foodInfo = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_FOOD_ID)) {
            query.setLong(1, foodId);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                foodInfo = Optional.of(Mapper.foodInfoMapWithoutUser(resultSet));
            }
        } catch (SQLException e) {
            Logger.getLogger(JDBCUserDao.class.getName()).severe(e.getMessage());
            throw new ServerException(e.getMessage());
        }
        return foodInfo;
    }
}

