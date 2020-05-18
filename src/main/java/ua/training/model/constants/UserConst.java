package ua.training.model.constants;

public interface UserConst {
    String ID = "id";
    String USERNAME = "username";
    String FIRST_NAME = "first_name";
    String LAST_NAME = "last_name";
    String PASSWORD = "password";
    String ROLE = "role";
    String HEIGHT = "height";
    String WEIGHT = "weight";
    String ACTIVITY_LEVEL = "activity_level";
    String AGE = "age";
    String GENDER = "gender";

    String FIND_ALL_BY_USERNAME = "select * from users where username = ?";
    String FIND_BY_ID = "select * from food WHERE id=?";
    String CREATE ="INSERT INTO users (" +
            "username, first_name, last_name, password, role, height, weight,  activity_level, age, gender" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
}
