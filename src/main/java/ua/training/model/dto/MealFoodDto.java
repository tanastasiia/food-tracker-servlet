package ua.training.model.dto;

import ua.training.model.entity.Food;

import java.time.LocalDate;
import java.time.LocalTime;

public class MealFoodDto {
    private Food food;
    private Integer amount;
    private LocalDate date;
    private LocalTime time;

    public MealFoodDto(Food food, Integer amount, LocalDate date, LocalTime time) {
        this.food = food;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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
