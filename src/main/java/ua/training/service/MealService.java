package ua.training.service;

import ua.training.controller.utils.Constants;
import ua.training.model.DaoFactory;
import ua.training.model.dao.MealDao;
import ua.training.model.dto.MealDto;
import ua.training.model.dto.UserMealStatDto;
import ua.training.model.entity.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.ServerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MealService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private final BigDecimal HUNDRED = new BigDecimal(100);
    private final BigDecimal THOUSAND = new BigDecimal(1000);

    private static class Holder {
        private static final MealService INSTANCE = new MealService();
    }

    public static MealService getInstance() {
        return MealService.Holder.INSTANCE;
    }

    public void saveMeal(Meal meal) throws ServerException {
        System.out.println("saveMeal");
        try (MealDao dao = daoFactory.createMealDao()) {
            dao.create(meal);
        }
    }

    public List<MealDto> todaysUserMeals(Long userId, Locale locale) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.findByUserIdAndDateTimeBetween(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.now(), LocalTime.MAX))
                    .stream()
                    .map(meal -> new MealDto(
                            locale.equals(Constants.LOCALE_UA) ? meal.getFood().getNameUa() : meal.getFood().getName(),
                            meal.getAmount(),
                            meal.getDateTime().toLocalDate(),
                            meal.getDateTime().toLocalTime()
                    ))
                    .collect(Collectors.toList());

        }
    }

    public List<MealDto> fildAllUserMeals(Long userId, Locale locale) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.findByUserId(userId).stream().map(meal ->
                    new MealDto(
                            locale.equals(Constants.LOCALE_UA) ? meal.getFood().getNameUa() : meal.getFood().getName(),
                            meal.getAmount(),
                            meal.getDateTime().toLocalDate(),
                            meal.getDateTime().toLocalTime()
                    ))
                    .collect(Collectors.toList());

        }
    }

    //TODO method udate todays user stat


    public UserMealStatDto todaysUserStatistics(Long userId) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            List<Meal> meals = dao.findByUserIdAndDateTimeBetween(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
            return new UserMealStatDto(
                    sumFoodCalories(meals),
                    sumFoodElements(meals, Food::getFat) ,
                    sumFoodElements(meals, Food::getProtein) ,
                    sumFoodElements(meals, Food::getCarbs) );

        }
    }

    public Integer todaysUserCalories(Long userId) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            List<Meal> meals = dao.findByUserIdAndDateTimeBetween(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
            return sumFoodCalories(meals);

        }
    }


    private Integer sumFoodCalories(List<Meal> meals) throws ServerException {
        return meals.stream()
                    .mapToInt(meal -> meal.getFood().getCalories() * meal.getAmount() / 100)
                    .reduce(Integer::sum)
                    .orElse(0);
    }


    private   BigDecimal sumFoodElements(List<Meal> meals, Function<Food, Integer> getter){
        return   meals.stream()
                .map(meal -> new BigDecimal(getter.apply(meal.getFood()) * meal.getAmount()).divide(HUNDRED, 1))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).divide(THOUSAND, 1, RoundingMode.HALF_UP);
    }



}
