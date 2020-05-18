package ua.training.model.dto;

import ua.training.model.dao.GenericDao;
import ua.training.model.entity.Food;

public class FoodDto implements GenericDto<FoodDto, Food> {
    private String name;
    private String nameUa;
    private Integer carbs;
    private Integer protein;
    private Integer fat;
    private Integer calories;

    public FoodDto(Food food) {
        this.name = food.getName();
        this.nameUa = food.getNameUa();
        this.carbs = food.getCarbs();
        this.protein = food.getProtein();
        this.fat = food.getFat();
        this.calories = food.getCalories();
    }

    @Override
    public Food toEntity() {
        return new Food.Builder()
                .setName(name).setNameUa(nameUa).setCarbs(carbs)
                .setProtein(protein).setFat(fat).setCalories(calories).build();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "FoodDto{" +
                "name='" + name + '\'' +
                ", nameUa='" + nameUa + '\'' +
                ", carbs=" + carbs +
                ", protein=" + protein +
                ", fat=" + fat +
                ", calories=" + calories +
                '}';
    }
}
