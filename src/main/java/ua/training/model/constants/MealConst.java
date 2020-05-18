package ua.training.model.constants;

public interface MealConst {
    String CREATE = "INSERT INTO meals (user_id, food_id, amount_g, date_time) " +
            "VALUES(?, ?, ?, ?)";
}
