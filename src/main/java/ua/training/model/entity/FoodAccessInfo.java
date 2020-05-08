package ua.training.model.entity;

import java.time.LocalDateTime;

public class FoodAccessInfo {
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

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }

    ///////////////////////////////////////
    public static class Builder {
        private FoodAccessInfo foodAccessInfo;

        public Builder() {
            foodAccessInfo = new FoodAccessInfo();
        }

        public Builder setId(Long id) {
            foodAccessInfo.id = id;
            return this;
        }

        public Builder setUser(User user) {
            foodAccessInfo.user = user;
            return this;
        }

        public Builder setFood(Food food) {
            foodAccessInfo.food = food;
            return this;
        }

        public Builder setIsGlobal(Boolean isGlobal) {
            foodAccessInfo.isGlobal = isGlobal;
            return this;
        }

        public FoodAccessInfo build() {
            return foodAccessInfo;
        }
    }
}
