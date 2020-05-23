package ua.training.model.dto;

import java.math.BigDecimal;

public class UserMealStatDto {
    private Integer calories;
    private BigDecimal fat;
    private BigDecimal protein;
    private BigDecimal carbs;

    public UserMealStatDto(Integer calories, BigDecimal fat, BigDecimal protein, BigDecimal carbs) {
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }

    public BigDecimal getCarbs() {
        return carbs;
    }

    public void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }


}
