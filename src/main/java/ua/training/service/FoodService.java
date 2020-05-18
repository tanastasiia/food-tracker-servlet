package ua.training.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodDao;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;

import java.rmi.ServerException;
import java.util.Optional;

public class FoodService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        private static final FoodService INSTANCE  = new FoodService();
    }

    public static FoodService getInstance() {
        return FoodService.Holder.INSTANCE;
    }

    public Food addFood(Food food) throws ServerException {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.create(food);
        }
    }

    public Optional<Food> findById(Long id) throws ServerException {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findById(id);
        }
    }
    public Optional<Food> findByName(String name) throws ServerException {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findByName(name);
        }
    }
}
