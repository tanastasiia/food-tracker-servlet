package ua.training.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.dto.FoodDto;
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

    public Optional<FoodInfo> findFoodByFoodNameAndUser(String foodName, Long userId) throws ServerException {

        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return dao.findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(foodName, userId);
        }
    }
    public Optional<FoodInfo> saveFood(FoodInfo foodInfo) throws ServerException {
        if(!findFoodByFoodNameAndUser(foodInfo.getFood().getName(), foodInfo.getUser().getId()).isPresent()){
            try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
                return Optional.of(dao.saveFood(foodInfo));
            } catch (SQLException e) {
                throw new ServerException(e.getMessage());
            }
        }
        return Optional.empty();

    }
    public Optional<FoodInfo> saveFood(FoodDto foodDto, User user) throws ServerException {
        if(!findFoodByFoodNameAndUser(foodDto.getName(), user.getId()).isPresent()){
            try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
                return Optional.of(dao.saveFood(new FoodInfo.Builder().setFood(foodDto.toEntity()).setIsGlobal(false).setUser(user).build()));
            } catch (SQLException e) {
                throw new ServerException(e.getMessage());
            }
        }
        return Optional.empty();

    }
    /*
    if(!FoodInfoService.getInstance().findFoodByFoodNameAndUser(food.getName(), user.getId()).isPresent()){
            food = FoodService.getInstance().addFood(food);
            FoodInfoService.getInstance().addUserFood(food, user);
            request.getSession().setAttribute("formSuccess", "foodAdded");
        }
     */
}
