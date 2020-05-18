package ua.training.model;

import ua.training.model.dao.FoodDao;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.dao.MealDao;
import ua.training.model.dao.UserDao;
import ua.training.model.jdbc.JDBCFoodDao;
import ua.training.model.jdbc.JDBCFoodInfoDao;
import ua.training.model.jdbc.JDBCMealDao;
import ua.training.model.jdbc.JDBCUserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public FoodDao createFoodDao() {
        return new JDBCFoodDao(getConnection());
    }

    @Override
    public FoodInfoDao createFoodInfoDao() {
        return new JDBCFoodInfoDao(getConnection());
    }

    @Override
    public MealDao createMealDao() {
        return new JDBCMealDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}