package ua.training.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.util.Optional;

public class FoodInfoService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        private static final FoodInfoService INSTANCE  = new FoodInfoService();
    }

    public static FoodInfoService getInstance() {
        return FoodInfoService.Holder.INSTANCE;
    }

    public void addUserFood(Food food, User user) throws ServerException {
        //TODO create food first
        FoodInfo foodInfo = new FoodInfo.Builder().setFood(food).setUser(user).setIsGlobal(false).build();
        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            dao.create(foodInfo);
        }
    }

    public Optional<FoodInfo> findFoodByFoodNameAndUser(String foodName, Long userId) throws ServerException {
        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return dao.findAllByFoodNameFilterByUserIdOrGlobal(foodName, userId);
        }
    }
}
