package ua.training.model.dao;

import ua.training.model.entity.FoodInfo;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FoodInfoDao  extends GenericDao<FoodInfo> {

    List<FoodInfo> findAll(int limit, int offset) throws ServerException;

    int countAll() throws ServerException;

    FoodInfo create(FoodInfo foodInfo) throws ServerException;

    FoodInfo saveFood(FoodInfo foodInfo) throws ServerException, SQLException;

    Optional<FoodInfo> findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(String foodName, Long userId) throws ServerException;
}
