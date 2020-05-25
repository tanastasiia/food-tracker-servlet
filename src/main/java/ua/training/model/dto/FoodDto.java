package ua.training.model.dto;

import ua.training.controller.utils.Constants;
import ua.training.model.dao.GenericDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Locale;

public class FoodDto implements GenericDto<FoodDto, Food> {
    private String name;
    private BigDecimal carbs;
    private BigDecimal protein;
    private BigDecimal fat;
    private Integer calories;
    private Locale locale;


    private final Integer MILLIGRAMS_TO_GRAMS = 1000;
    private final BigDecimal GRAMS_TO_MILLIGRAMS = BigDecimal.valueOf(1000);


    private BigDecimal toMilligrams(Integer grams){
        return  BigDecimal.valueOf(grams).divide(GRAMS_TO_MILLIGRAMS, 2).setScale(2, RoundingMode.HALF_UP);
    }
    private Integer toGrams(BigDecimal milligrams){
        return  milligrams.multiply(BigDecimal.valueOf(MILLIGRAMS_TO_GRAMS)).intValue();
    }


    public  FoodDto(){

    }
    public FoodDto(Food food, Locale locale) {
        this.name = locale.equals(Constants.LOCALE_UA)? food.getNameUa():food.getName();
        this.carbs = toMilligrams(food.getCarbs());
        this.protein = toMilligrams(food.getProtein());
        this.fat = toMilligrams(food.getFat());
        this.calories = food.getCalories();
        this.locale = locale;
    }

    @Override
    public Food toEntity() {
        return new Food.Builder()
                .setName(locale.equals(Constants.LOCALE_UA)? null : name)
                .setNameUa(locale.equals(Constants.LOCALE_UA)? name : null)
                .setCarbs(toGrams(carbs))
                .setProtein(toGrams(protein))
                .setFat(toGrams(fat))
                .setCalories(calories).build();
    }
    public Food toEntity(Locale locale) {
        return new Food.Builder()
                .setName(locale.equals(Constants.LOCALE_UA)? null : name)
                .setNameUa(locale.equals(Constants.LOCALE_UA)? name : null)
                .setCarbs(toGrams(carbs))
                .setProtein(toGrams(protein))
                .setFat(toGrams(fat))
                .setCalories(calories).build();
    }

    public String getName() {
        return name;
    }


    public Integer getCalories() {
        return calories;
    }



    @Override
    public String toString() {
        return "FoodDto{" +
                "name='" + name + '\'' +
                ", carbs=" + carbs.setScale(2, RoundingMode.HALF_UP).toString() +
                ", protein=" + protein.setScale(2, RoundingMode.HALF_UP).toString() +
                ", fat=" + fat.setScale(2, RoundingMode.HALF_UP).toString() +
                ", calories=" + calories +
                '}';
    }


    public BigDecimal getCarbs() {
        return carbs;
    }



    public BigDecimal getFat() {
        return fat;
    }
    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }


    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public static class Builder {
        private FoodDto foodDto;

        public Builder() {
            foodDto = new FoodDto();
        }

        public FoodDto.Builder setProtein(BigDecimal protein) {
            this.foodDto.protein = protein;
            return this;
        }


        public FoodDto.Builder setFat(BigDecimal fat) {
            this.foodDto.fat = fat;
            return this;
        }

        public FoodDto.Builder setCarbs(BigDecimal carbs) {
            this.foodDto.carbs = carbs;
            return this;
        }
        public FoodDto.Builder setName(String name) {
            this.foodDto.name = name;
            return this;
        }

        public FoodDto.Builder setCalories(Integer calories) {
            this.foodDto.calories = calories;
            return this;
        }
        public FoodDto.Builder setLocale(Locale locale) {
            this.foodDto.locale = locale;
            return this;
        }

        public FoodDto build() {
            return foodDto;
        }
    }
}
