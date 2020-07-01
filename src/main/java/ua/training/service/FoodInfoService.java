package ua.training.service;

import ua.training.utils.ServiceUtil;
import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.dto.FoodDto;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import javax.validation.*;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FoodInfoService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    private static class Holder {
        private static final FoodInfoService INSTANCE = new FoodInfoService();
    }

    public static FoodInfoService getInstance() {
        return FoodInfoService.Holder.INSTANCE;
    }

    public Optional<FoodInfo> findFoodByFoodNameAndUser(String foodName, Long userId) throws ServerException {

        foodName = ServiceUtil.getInstance().capitalizeFirstLetter(foodName);

        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return dao.findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(foodName, userId);
        }
    }

    /**
     * Savw food to database
     * @param foodDto food to save
     * @param user adder
     * @param isGlobal true if visible for all users, fasle if only for adder
     * @return Optional of saved food
     * @throws ServerException
     */
    public Optional<FoodInfo> saveFood(FoodDto foodDto, User user, Boolean isGlobal) throws ServerException {
        if (!findFoodByFoodNameAndUser(foodDto.getName(), user.getId()).isPresent()) {
            try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
                return Optional.of(dao.saveFood(new FoodInfo.Builder()
                        .setFood(foodDto.toEntity())
                        .setIsGlobal(isGlobal)
                        .setUser(user).build()));
            } catch (SQLException e) {
                throw new ServerException(e.getMessage());
            }
        }
        return Optional.empty();
    }

    /**
     * Get all food infos page
     *
     * @param limit  amount of elements
     * @param offset number of first item
     * @return list if food infos
     */
    public List<FoodInfo> findAllFood(int limit, int offset) throws ServerException {
        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return dao.findAll(limit, offset);

        }
    }

    /**
     * Count all food
     */
    public int countAllFood() throws ServerException {
        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return dao.countAll();
        }
    }

}
