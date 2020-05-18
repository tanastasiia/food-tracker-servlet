package ua.training.model.dto;

import ua.training.model.entity.Food;
import ua.training.model.entity.Meal;
import ua.training.model.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealDto implements GenericDto<MealDto, Meal> {

    private String foodName;
    private Integer amount;
    private LocalDate date;
    private LocalTime time;


    public MealDto() {
    }
    public MealDto(String foodName, Integer amount, LocalDate date, LocalTime time) {
        this.foodName = foodName;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    @Override
    public Meal toEntity() {
        return new Meal.Builder().setAmount(amount)
                .setDateTime(date.atTime(time))
                .setFood(new Food.Builder().setName(foodName).build()).build();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
