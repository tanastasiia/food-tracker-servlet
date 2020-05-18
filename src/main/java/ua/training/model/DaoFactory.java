package ua.training.model;

import ua.training.model.dao.FoodDao;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.dao.MealDao;
import ua.training.model.dao.UserDao;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract FoodDao createFoodDao();
    public abstract FoodInfoDao createFoodInfoDao();
    public abstract MealDao createMealDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}