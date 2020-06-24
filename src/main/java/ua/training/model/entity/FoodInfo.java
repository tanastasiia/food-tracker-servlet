package ua.training.model.entity;

import java.util.Objects;

public class FoodInfo {

    private Long id;
    private Food food;
    private User user;
    private Boolean isGlobal;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getIsGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodInfo foodInfo = (FoodInfo) o;
        return Objects.equals(id, foodInfo.id) &&
                Objects.equals(food, foodInfo.food) &&
                Objects.equals(user, foodInfo.user) &&
                Objects.equals(isGlobal, foodInfo.isGlobal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, food, user, isGlobal);
    }

    @Override
    public String toString() {
        return "FoodInfo{" +
                "id=" + id +
                ", food=" + food +
                ", user=" + user +
                ", isGlobal=" + isGlobal +
                '}';
    }

    ///////////////////////////////////////
    public static class Builder {
        private FoodInfo foodInfo;

        public Builder() {
            foodInfo = new FoodInfo();
        }

        public Builder setId(Long id) {
            foodInfo.id = id;
            return this;
        }

        public Builder setUser(User user) {
            foodInfo.user = user;
            return this;
        }

        public Builder setFood(Food food) {
            foodInfo.food = food;
            return this;
        }

        public Builder setIsGlobal(Boolean isGlobal) {
            foodInfo.isGlobal = isGlobal;
            return this;
        }

        public FoodInfo build() {
            return foodInfo;
        }
    }
}
