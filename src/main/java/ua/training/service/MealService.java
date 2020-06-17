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

    /**
     * Save meal to database
     *
     * @param mealDto  meal
     * @param foodInfo food info
     * @param user     user which consumed food
     */
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

    /**
     * Get meals page
     *
     * @param limit  amount of elements
     * @param offset number of first item
     * @return list of meals
     */
    public List<Meal> findAllMeals(int limit, int offset) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.findAll(limit, offset);
        }
    }

    /**
     * Count all meals
     */
    public int countAllMeals() throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.countAllMeals();
        }
    }

    /**
     * Get todays users meals
     *
     * @param userId user id
     * @param locale locale for food name
     * @return list of meals
     */
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

    /**
     * Get all users meals page
     *
     * @param limit  amount of elements
     * @param offset number of first item
     * @return list if meals
     */
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

    /**
     * Count all users meals
     */
    public int countAllUsersMeals(Long userId) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            return dao.countAllUserMeals(userId);
        }
    }

    /**
     * Count todays consumed calories, fat, protein, carbs for user
     *
     * @param userId user id of user
     * @return sum of calories, fat, protein, carbs of consumed today food
     */
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

    /**
     * Count todays consumed calories for user
     *
     * @param userId user id of user
     * @return sum of calories of consumed today food
     */
    public Integer todaysUserCalories(Long userId) throws ServerException {
        try (MealDao dao = daoFactory.createMealDao()) {
            List<Meal> meals = dao.findByUserIdAndDateTimeBetween(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
            return sumFoodCalories(meals);

        }
    }

    /**
     * Sum food calories in meals
     *
     * @param meals meals to take food from
     * @return sum of calories
     */
    private Integer sumFoodCalories(List<Meal> meals) {
        return meals.stream()
                .mapToInt(meal -> meal.getFood().getCalories() * meal.getAmount() / 100)
                .reduce(Integer::sum)
                .orElse(0);
    }


    /**
     * Sum food elements (fat, carbs or protein) in meals
     *
     * @param meals  meals to take food from
     * @param getter food element getter
     * @return sum of food elements in grams
     */
    private BigDecimal sumFoodElements(List<Meal> meals, Function<Food, Integer> getter) {
        return meals.stream()
                .map(meal -> new BigDecimal(getter.apply(meal.getFood()) * meal.getAmount()).divide(HUNDRED, 1))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).divide(THOUSAND, 1, RoundingMode.HALF_UP);
    }

    /**
     * Get food name by locale
     *
     * @param meal   meal
     * @param locale locale of needed name
     * @return if locale is ua then name in ukrainian else in english. If needed is null then another
     */
    private String getNameByLocale(Meal meal, Locale locale) {
        Optional<String> nameUa = Optional.ofNullable(meal.getFood().getNameUa());
        Optional<String> name = Optional.ofNullable(meal.getFood().getName());

        return locale.equals(Constants.LOCALE_UA) ? nameUa.orElse(name.orElse("")) : name.orElse(nameUa.orElse(""));

    }


}
