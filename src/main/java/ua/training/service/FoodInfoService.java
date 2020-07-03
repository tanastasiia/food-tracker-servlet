package ua.training.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.dto.FoodDto;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;
import ua.training.utils.ServiceUtil;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

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
     *
     * @param foodDto  food to save
     * @param user     adder
     * @param isGlobal true if visible for all users, fasle if only for adder
     * @return Optional of saved food
     * @throws ServerException
     */
    public Optional<FoodInfo> saveFood(FoodDto foodDto, User user, Boolean isGlobal) throws ServerException {
        Food.Builder foodBuilder = new Food.Builder()
                .setCarbs(ServiceUtil.getInstance().toGrams(foodDto.getCarbs()))
                .setProtein(ServiceUtil.getInstance().toGrams(foodDto.getProtein()))
                .setFat(ServiceUtil.getInstance().toGrams(foodDto.getFat()))
                .setCalories(foodDto.getCalories());

        if (user.getRole().equals("ROLE_USER")) {
            foodBuilder = foodDto.getLocale().equals(ServiceUtil.getInstance().LOCALE_UA)
                    ? foodBuilder.setNameUa(foodDto.getName())
                    : foodBuilder.setName(foodDto.getName());
        } else {
            foodBuilder = foodBuilder.setName(foodDto.getName()).setNameUa(foodDto.getNameUa());
        }
        if ((!foodDto.getName().isEmpty() && findFoodByFoodNameAndUser(foodDto.getName(), user.getId()).isPresent())
                || (!foodDto.getNameUa().isEmpty() && findFoodByFoodNameAndUser(foodDto.getNameUa(), user.getId()).isPresent())) {
            return Optional.empty();
        }

        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return Optional.of(dao.saveFood(new FoodInfo.Builder()
                    .setFood(foodBuilder.build())
                    .setIsGlobal(isGlobal)
                    .setUser(user).build()));
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
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
     * Get all food names
     *
     * @param userId  userId
     * @return list if food names
     */
    public List<String> findAllFoodNamesForUser(long userId, Locale locale) throws ServerException {
        try (FoodInfoDao dao = daoFactory.createFoodInfoDao()) {
            return dao.findAllUserFood(userId).stream()
                    .map(foodInfo -> ServiceUtil.getInstance().getLocalizedFoodName(foodInfo.getFood(), locale))
                    .collect(Collectors.toList());

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
