package ua.training.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.MealDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.MealDto;
import ua.training.model.entity.Food;
import ua.training.model.entity.Meal;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.util.Optional;

public class MealService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        private static final MealService INSTANCE = new MealService();
    }

    public static MealService getInstance() {
        return MealService.Holder.INSTANCE;
    }

    public void saveMeal(Meal meal) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            dao.create(meal);
        }
    }

}
