package ua.training.model.constants;

public interface FoodInfoConst {
    String TABLE_NAME = "food_info";

    String ID = "id";
    String USER_ID = "user_id";
    String FOOD_ID = "food_id";
    String IS_GLOBAL = "is_global";

    String FIND_ALL = "select * from food_info";

    String FIND_ALL_GLOBAL = "select * from food_info where is_global=TRUE";

    String FIND_ALL_BY_FOOD_NAME_FILTER_BY_USER_ID_OR_GLOBAL = "SELECT * FROM food_info " +
            "INNER JOIN food ON food_info.food_id=food.id " +
            "LEFT OUTER JOIN users ON food_info.adder_user_id=users.id " +
            "WHERE  food.name=? AND (food_info.adder_user_id=? OR food_info.is_global=TRUE)";

    String FIND_ALL_BY_FOOD_NAME_FILTER_BY_USER_ID_OR_GLOBAL_UA = "SELECT * FROM food_info " +
            "INNER JOIN food ON food_info.food_id=food.id " +
            "LEFT OUTER JOIN users ON food_info.adder_user_id=users.id " +
            "WHERE  food.name_ua=? AND (food_info.adder_user_id=? OR food_info.is_global=TRUE)";



    /*String FIND_FOOD_BY_USERNAME_OR_GLOBAL_UA ="SELECT food.name_ua, food.fat, food.carbs, food.protein, food.calories " +
            "FROM food_info " +
            "INNER JOIN food ON food_info.food_id=food.id " +
            "LEFT OUTER JOIN users ON food_info.adder_user_id=users.id " +
            "WHERE users.username=? OR food_info.is_global=TRUE";*/

    String CREATE ="INSERT INTO food_info (food_id, adder_user_id,  is_global) VALUES (?, ?, ?)";

}
