package ua.training.service;

import ua.training.model.dto.UserDto;
import ua.training.utils.Constants;
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
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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

    public void saveMeal(MealDto mealDto, FoodInfo foodInfo, UserDto user) throws ServerException {
        Meal meal = new Meal.Builder()
                .setAmount(mealDto.getAmount())
                .setDateTime(mealDto.getDate().atTime(mealDto.getTime()))
                .setUser(user.toEntity())
                .setFood(foodInfo.getFood()).build();
        try (MealDao dao = daoFactory.createMealDao()) {
            dao.create(meal);
        }
    }

    public List<Meal> findAllMeals(int limit, int offset) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.findAll(limit, offset);
        }
    }

    public int countAllMeals() throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.countAllMeals();
        }
    }

    public List<MealDto> todaysUserMeals(Long userId, Locale locale) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.findByUserIdAndDateTimeBetween(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.now(), LocalTime.MAX))
                    .stream()
                    .map(meal -> new MealDto(
                            getNameByLocale(meal, locale),
                            meal.getAmount(),
                            meal.getDateTime().toLocalDate(),
                            meal.getDateTime().toLocalTime()
                    ))
                    .collect(Collectors.toList());

        }
    }


    public List<MealDto> findAllUserMeals(Long userId, Locale locale, int limit, int offset) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.findAllByUserId(userId, limit, offset).stream().map(meal ->
                    new MealDto(
                            getNameByLocale(meal, locale),
                            meal.getAmount(),
                            meal.getDateTime().toLocalDate(),
                            meal.getDateTime().toLocalTime()
                    ))
                    .collect(Collectors.toList());

        }
    }

    public int countAllUsersMeals(Long userId) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.countAllUserMeals(userId);
        }
    }

    public UserMealStatDto todaysUserStatistics(Long userId) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            List<Meal> meals = dao.findByUserIdAndDateTimeBetween(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
            return new UserMealStatDto(
                    sumFoodCalories(meals),
                    sumFoodElements(meals, Food::getFat),
                    sumFoodElements(meals, Food::getProtein),
                    sumFoodElements(meals, Food::getCarbs));

        }
    }

    public Integer todaysUserCalories(Long userId) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            List<Meal> meals = dao.findByUserIdAndDateTimeBetween(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
            return sumFoodCalories(meals);

        }
    }


    private Integer sumFoodCalories(List<Meal> meals) {
        return meals.stream()
                .mapToInt(meal -> meal.getFood().getCalories() * meal.getAmount() / 100)
                .reduce(Integer::sum)
                .orElse(0);
    }


    private BigDecimal sumFoodElements(List<Meal> meals, Function<Food, Integer> getter) {
        return meals.stream()
                .map(meal -> new BigDecimal(getter.apply(meal.getFood()) * meal.getAmount()).divide(HUNDRED, 1))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).divide(THOUSAND, 1, RoundingMode.HALF_UP);
    }

    private String getNameByLocale(Meal meal, Locale locale) {
        Optional<String> nameUa = Optional.ofNullable(meal.getFood().getNameUa());
        Optional<String> name = Optional.ofNullable(meal.getFood().getName());
        //TODO orElseThrow
        return locale.equals(Constants.LOCALE_UA) ? nameUa.orElse(name.orElse("")) : name.orElse(nameUa.orElse(""));

    }


}
