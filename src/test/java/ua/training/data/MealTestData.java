package ua.training.data;

import ua.training.model.entity.Food;
import ua.training.model.entity.Meal;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealTestData {

    private UserTestData userTestData = new UserTestData();
    private FoodTestData foodTestData = new FoodTestData();
    private BigDecimal HUNDRED = BigDecimal.valueOf(100);

    public Meal MEAL = new Meal.Builder().setId(1L).setAmount(200)
            .setDateTime(LocalDateTime.of(2020, 5, 23, 12, 42, 45))
            .setUser(userTestData.USER)
            .setFood(foodTestData.FOOD)
            .build();


    public int TODAYS_CALORIES = 50 * 2 + 100 * 340 / 100 + 60 * 4 + 330 * 250 / 100 + 450 * 310 / 100;
    public BigDecimal TODAYS_PROTEIN = BigDecimal.valueOf(500 * 200).divide(HUNDRED, 1)
            .add(BigDecimal.valueOf(800 * 340).divide(HUNDRED, 1))
            .add(BigDecimal.valueOf(1000 * 400).divide(HUNDRED, 1))
            .add(BigDecimal.valueOf(1200 * 250).divide(HUNDRED, 1))
            .add(BigDecimal.valueOf(5500 * 310).divide(HUNDRED, 1))
            .divide(BigDecimal.valueOf(1000), 1, RoundingMode.HALF_UP);


    public List<Meal> TODAYS_MEALS_LIST = new ArrayList<>(Arrays.asList(
            new Meal.Builder()
                    .setId(1L)
                    .setAmount(200)
                    .setDateTime(LocalDateTime.now())
                    .setFood(foodTestData.FOOD_LIST.get(0))
                    .setUser(userTestData.USERS_LIST.get(0))
                    .build(),
            new Meal.Builder()
                    .setId(2L)
                    .setAmount(340)
                    .setDateTime(LocalDateTime.now())
                    .setFood(foodTestData.FOOD_LIST.get(1))
                    .setUser(userTestData.USERS_LIST.get(1))
                    .build(),
            new Meal.Builder()
                    .setId(3L)
                    .setAmount(400)
                    .setDateTime(LocalDateTime.now())
                    .setFood(foodTestData.FOOD_LIST.get(2))
                    .setUser(userTestData.USERS_LIST.get(2))
                    .build(),
            new Meal.Builder()
                    .setId(4L)
                    .setAmount(250)
                    .setDateTime(LocalDateTime.now())
                    .setFood(foodTestData.FOOD_LIST.get(3))
                    .setUser(userTestData.USERS_LIST.get(3))
                    .build(),
            new Meal.Builder()
                    .setId(5L)
                    .setAmount(310)
                    .setDateTime(LocalDateTime.now())
                    .setFood(foodTestData.FOOD_LIST.get(4))
                    .setUser(userTestData.USERS_LIST.get(4))
                    .build()

    ));
}
