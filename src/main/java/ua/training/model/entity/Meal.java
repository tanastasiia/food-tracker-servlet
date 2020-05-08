package ua.training.model.entity;

import java.time.LocalDateTime;

public class Meal {
    private Long id;
    private User user;
    private Food food;
    private Integer amount;
    private LocalDateTime dateTime;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

//////////////////////////////////////////////////
    public static class Builder {
        private Meal meal;

        public Builder(){
            meal = new Meal();
        }

        public Builder setId(Long id) {
            meal.id = id;
            return this;
        }

        public Builder setUser(User user) {
            meal.user = user;
            return this;
        }

        public Builder setFood(Food food) {
            meal.food = food;
            return this;
        }
        public Builder setAmount(Integer amount) {
            meal.amount = amount;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            meal.dateTime = dateTime;
            return this;
        }

        public Meal build(){
            return meal;
        }


    }
}
