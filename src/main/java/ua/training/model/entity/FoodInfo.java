package ua.training.model.entity;

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

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
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
