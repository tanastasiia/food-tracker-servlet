package ua.training.data;

import ua.training.model.dto.FoodDto;
import ua.training.model.entity.Food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FoodTestData {

    public Food FOOD = new Food.Builder().setName("Apple").setNameUa("Яблуко")
            .setCalories(50).setCarbs(3000).setFat(2000).setProtein(500).setId(1L).build();

    public FoodDto FOOD_DTO = new FoodDto.Builder().setName("Apple").setNameUa("Яблуко")
            .setCalories(50).setCarbs(BigDecimal.valueOf(3)).setFat(BigDecimal.valueOf(2))
            .setProtein(BigDecimal.valueOf(0.5)).setLocale(new Locale("ua")).build();

    public Food FOOD_NO_NAME = new Food.Builder().setNameUa("Яблуко")
            .setCalories(50).setCarbs(3000).setFat(2000).setProtein(500).setId(1L).build();

    public Food FOOD_NO_NAME_UA = new Food.Builder().setName("Apple")
            .setCalories(50).setCarbs(3000).setFat(2000).setProtein(500).setId(1L).build();

    public List<Food> FOOD_LIST =  new ArrayList<>(Arrays.asList(
                new Food.Builder().setName("Apple").setNameUa("Яблуко")
                        .setCalories(50).setCarbs(3000).setFat(2000).setProtein(500).setId(1L).build(),
                new Food.Builder().setName("Pineapple").setNameUa("Ананас")
                        .setCalories(100).setCarbs(2000).setFat(2000).setProtein(800).setId(2L).build(),
                new Food.Builder().setName("Orange").setNameUa("Апельсин")
                        .setCalories(60).setCarbs(4000).setFat(2000).setProtein(1000).setId(3L).build(),
                new Food.Builder().setName("Pasta").setNameUa("Паста")
                        .setCalories(330).setCarbs(31000).setFat(2000).setProtein(1200).setId(4L).build(),
                new Food.Builder().setName("French fries").setNameUa("Картопля фрі")
                        .setCalories(450).setCarbs(44000).setFat(42000).setProtein(5500).setId(5L).build()
        ));
}
