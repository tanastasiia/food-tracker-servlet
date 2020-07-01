package ua.training.model.dto;

import ua.training.model.entity.Meal;

import java.math.BigDecimal;
import java.util.Locale;

public class MealDto {
    private String foodName;
    private Integer amount;

    public MealDto(){

    }

    public MealDto(String foodName, Integer amount) {
        this.foodName = foodName;
        this.amount = amount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public static class Builder {
        private MealDto mealDto;

        public Builder() {
            mealDto = new MealDto();
        }

        public MealDto.Builder setFoodName(String foodName ) {
            this.mealDto.foodName = foodName;
            return this;
        }


        public MealDto.Builder setAmount(Integer amount) {
            this.mealDto.amount = amount;
            return this;
        }

        public MealDto build() {
            return mealDto;
        }
    }
}
