package ua.training.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * to display at admin page
 */
public class MealRecordDto  {

    private String foodName;
    private Integer amount;
    private LocalDate date;
    private LocalTime time;

    public MealRecordDto(String foodName, Integer amount, LocalDate date, LocalTime time) {
        this.foodName = foodName;
        this.amount = amount;
        this.date = date;
        this.time = time;
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
