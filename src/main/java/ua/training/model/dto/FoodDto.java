package ua.training.model.dto;

import ua.training.utils.Constants;
import ua.training.model.entity.Food;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class FoodDto {

    private String name;
    private String nameUa;

    private BigDecimal carbs;
    private BigDecimal protein;
    private BigDecimal fat;
    private Integer calories;
    private Locale locale;

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

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public Locale getLocale() {
        return locale;
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
        public FoodDto.Builder setNameUa(String nameUa) {
            this.foodDto.nameUa = nameUa;
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
