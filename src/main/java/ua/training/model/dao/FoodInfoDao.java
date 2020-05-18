package ua.training.model.dao;

import ua.training.model.dto.FoodInfoDto;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.Optional;

public interface FoodInfoDao  extends GenericDao<FoodInfo>  {

    //Optional<FoodInfo> findByUserId(Long userId) throws ServerException;
    //Optional<FoodInfo> findByFoodId(Long foodId) throws ServerException;
    Optional<FoodInfo> findAllByFoodNameFilterByUserIdOrGlobal(String foodName, Long userId) throws ServerException;
    Optional<FoodInfo> findByFoodName(Long foodName) throws ServerException;
    Optional<FoodInfo> findByFoodNameUa(Long foodNameUa) throws ServerException;
    boolean saveFood(FoodInfo foodInfo) throws ServerException, SQLException;
}
