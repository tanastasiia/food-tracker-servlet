package ua.training.model.jdbc;

import org.apache.logging.log4j.core.util.JsonUtils;
import ua.training.model.Mapper;
import ua.training.model.constants.FoodInfoConst;
import ua.training.model.constants.UserConst;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCFoodInfoDao implements FoodInfoDao {

    private Connection connection;

    public JDBCFoodInfoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FoodInfo create(FoodInfo foodInfo) throws ServerException {

        try (PreparedStatement query = connection.prepareStatement(FoodInfoConst.CREATE, Statement.RETURN_GENERATED_KEYS)) {

            query.setLong(1, foodInfo.getFood().getId());
            query.setLong(2, foodInfo.getUser().getId());
            query.setBoolean(3, foodInfo.isGlobal());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                foodInfo.setId(keys.getLong(1));
            }
            return foodInfo;

        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public FoodInfo findById(int id) {
        return null;
    }

    @Override
    public List<FoodInfo> findAll() {
        return null;
    }

    @Override
    public void update(FoodInfo entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<FoodInfo> findAllByFoodNameFilterByUserIdOrGlobal(String foodName, Long userId) throws ServerException {
        Optional<FoodInfo> foodInfo = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(
                FoodInfoConst.FIND_ALL_BY_FOOD_NAME_FILTER_BY_USER_ID_OR_GLOBAL)) {
            query.setString(1, foodName);
            query.setLong(2, userId);
            System.out.println("Query: " + query.toString());

            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                foodInfo = Optional.of(Mapper.foodInfoMap(resultSet));
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return foodInfo;
    }

    @Override
    public Optional<FoodInfo> findByFoodName(Long foodName) throws ServerException {
        return Optional.empty();
    }

    @Override
    public Optional<FoodInfo> findByFoodNameUa(Long foodNameUa) throws ServerException {
        return Optional.empty();
    }
}
