package ua.training.data;

import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FoodInfoTestData {

    private UserTestData userTestData = new UserTestData();
    private FoodTestData foodTestData = new FoodTestData();

    public FoodInfo FOOD_INFO_GLOBAL =  new FoodInfo.Builder()
                .setUser(userTestData.ADMIN_USER)
                .setFood(foodTestData.FOOD)
                .setIsGlobal(true).setId(1L).build();


    public FoodInfo  FOOD_INFO_NOT_GLOBAL =  new FoodInfo.Builder()
                .setUser(userTestData.USER)
                .setFood(foodTestData.FOOD)
                .setIsGlobal(false).setId(1L).build();


    public List<FoodInfo> FOOD_INFO_LIST = generateFoodInfoList();

    private List<FoodInfo> generateFoodInfoList() {

        List<Food> foods = foodTestData.FOOD_LIST;
        List<User> users = userTestData.USERS_LIST;

        return IntStream.range(0, Math.min(foods.size(), users.size())).boxed().map(i ->
                new FoodInfo.Builder()
                        .setId((long) i)
                        .setFood(foods.get(i))
                        .setUser(users.get(i))
                        .setIsGlobal(i%2==0)
                        .build())
                .collect(Collectors.toList());
    }
}
