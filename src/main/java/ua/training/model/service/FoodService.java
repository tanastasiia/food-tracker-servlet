package ua.training.model.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodDao;
import ua.training.model.entity.Food;

import java.rmi.ServerException;
import java.util.Optional;

public class FoodService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        private static final FoodService INSTANCE = new FoodService();
    }

    private FoodService() {

    }

    public static FoodService getInstance() {
        return FoodService.Holder.INSTANCE;
    }

    public Food addFood(Food food) throws ServerException {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.create(food);
        }
    }

    public Optional<Food> findById(long foodId) throws ServerException {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findById(foodId);

        }
    }

}
