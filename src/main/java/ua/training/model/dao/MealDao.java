package ua.training.model.dao;

import ua.training.model.entity.Meal;

import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.List;

public interface MealDao extends GenericDao<Meal> {

    Meal create(Meal meal) throws ServerException;

    List<Meal> findAll(int limit, int offset) throws ServerException;

    int countAllMeals() throws ServerException;

    List<Meal> findAllByUserId(Long userId, int limit, int offset) throws ServerException;

    int countAllUserMeals(Long userId) throws ServerException;

    List<Meal> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime dateTime1,
                                              LocalDateTime dateTime2) throws ServerException;

    List<Meal> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime dateTime1, LocalDateTime dateTime2,
                                              int limit, int offset) throws ServerException;

}
