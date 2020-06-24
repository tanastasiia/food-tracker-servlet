package ua.training.model.entity;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Objects;

public class Food {

    private Long id;
    private String name;
    private String nameUa;
    private Integer carbs;
    private Integer protein;
    private Integer fat;
    private Integer calories;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameUa() {
        return nameUa;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public Integer getProtein() {
        return protein;
    }

    public Integer getFat() {
        return fat;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameUa='" + nameUa + '\'' +
                ", carbs=" + carbs +
                ", protein=" + protein +
                ", fat=" + fat +
                ", calories=" + calories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id) &&
                Objects.equals(name, food.name) &&
                Objects.equals(nameUa, food.nameUa) &&
                Objects.equals(carbs, food.carbs) &&
                Objects.equals(protein, food.protein) &&
                Objects.equals(fat, food.fat) &&
                Objects.equals(calories, food.calories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameUa, carbs, protein, fat, calories);
    }

    ////////////////////
    public static class Builder {
        private Food food;

        public Builder() {
            food = new Food();
        }

        public Builder setId(Long id) {
            food.id = id;
            return this;
        }

        public Builder setName(String name) {
            food.name = name;
            return this;
        }

        public Builder setNameUa(String nameUa) {
            food.nameUa = nameUa;
            return this;
        }

        public Builder setCarbs(Integer carbs) {
            food.carbs = carbs;
            return this;
        }

        public Builder setProtein(Integer protein) {
            food.protein = protein;
            return this;
        }


        public Builder setFat(Integer fat) {
            food.fat = fat;
            return this;
        }

        public Builder setCalories(Integer calories) {
            food.calories = calories;
            return this;
        }

        public Food build() {
            return food;
        }
    }
}
