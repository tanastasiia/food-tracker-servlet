package ua.training.model.dao;

import ua.training.model.entity.Food;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.util.Optional;

public interface FoodDao extends GenericDao<Food> {
    Optional<Food> findByName(String name) throws ServerException;
}
