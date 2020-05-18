package ua.training.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.dto.FoodDto;
import ua.training.model.dto.FoodInfoDto;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.Optional;

public class FoodInfoService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        private static final FoodInfoService INSTANCE  = new FoodInfoService();
    }

    public static FoodInfoService getInstance() {
        return FoodInfoService.Holder.INSTANCE;
    }

    public void addUserFood(FoodInfoDto foodInfoDto) throws ServerException {
        //TODO create food first
        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            dao.create(foodInfoDto.toEntity());
        }
    }

    public Optional<FoodInfo> findFoodByFoodNameAndUser(String foodName, Long userId) throws ServerException {
        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return dao.findAllByFoodNameFilterByUserIdOrGlobal(foodName, userId);
        }
    }
    public boolean saveFood(FoodInfoDto foodInfoDto) throws ServerException {
        if(!findFoodByFoodNameAndUser(foodInfoDto.getFood().getName(), foodInfoDto.getUser().getId()).isPresent()){
            try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
                return dao.saveFood(foodInfoDto.toEntity());
            } catch (SQLException e) {
                throw new ServerException(e.getMessage());
            }
        }
        return false;

    }
    /*
    if(!FoodInfoService.getInstance().findFoodByFoodNameAndUser(food.getName(), user.getId()).isPresent()){
            food = FoodService.getInstance().addFood(food);
            FoodInfoService.getInstance().addUserFood(food, user);
            request.getSession().setAttribute("formSuccess", "foodAdded");
        }
     */
}
