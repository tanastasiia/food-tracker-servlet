package ua.training.model.constants;

public interface FoodConst {
    String ID = "id";
    String NAME = "name";
    String NAME_UA = "name_ua";
    String CARBS = "carbs_mg";
    String FAT = "fat_mg";
    String PROTEIN = "protein_mg";
    String CALORIES = "calories";

    String FIND_ALL = "select * from food";
    String FIND_BY_NAME = "select * from food WHERE name=?";
    String FIND_BY_NAME_UA = "select * from food WHERE name_ua=?";

    String CREATE = "INSERT INTO food (name, name_ua, carbs_mg, fat_mg, protein_mg, calories) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
}
