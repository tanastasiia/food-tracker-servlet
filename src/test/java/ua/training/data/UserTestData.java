package ua.training.data;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.model.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTestData {

    private static final String PASSWORD_1 = BCrypt.hashpw("qwerty", BCrypt.gensalt());
    private static final String PASSWORD_2 = BCrypt.hashpw("sdsd", BCrypt.gensalt());
    private static final String PASSWORD_3 = BCrypt.hashpw("jkljl", BCrypt.gensalt());
    private static final String PASSWORD_4 = BCrypt.hashpw("123qw", BCrypt.gensalt());
    private static final String PASSWORD_5 = BCrypt.hashpw("123", BCrypt.gensalt());

    public User ADMIN_USER = new User.Builder()
            .setId(2L).setWeight(60).setRole("ROLE_ADMIN").setHeight(170).setGender("MALE").setActivityLevel("SECOND")
            .setUsername("annat").setDateOfBirth(LocalDate.now().minusYears(22)).setLastName("Doe").setFirstName("Jane").setPassword(PASSWORD_1).build();


    public User USER = new User.Builder()
            .setId(2L).setWeight(60).setRole("ROLE_USER").setHeight(170).setGender("MALE").setActivityLevel("SECOND")
            .setUsername("annat").setDateOfBirth(LocalDate.now().minusYears(22)).setLastName("Doe").setFirstName("Jane").setPassword(PASSWORD_1).build();


    public User USER_2 = new User.Builder()
            .setId(3L).setWeight(56).setRole("ROLE_USER").setHeight(160).setGender("MALE").setActivityLevel("FIRST")
            .setUsername("dfgh").setDateOfBirth(LocalDate.now().minusYears(32)).setLastName("Jiel").setFirstName("Jake")
            .setPassword(PASSWORD_2).build();


    public List<User> USERS_LIST = new ArrayList<>(Arrays.asList(
            new User.Builder()
                    .setId(2L).setWeight(60).setRole("ROLE_ADMIN").setHeight(170).setGender("MALE").setActivityLevel("SECOND")
                    .setUsername("annat").setDateOfBirth(LocalDate.now().minusYears(22)).setLastName("Doe").setFirstName("Jane")
                    .setPassword(PASSWORD_1).build(),
            new User.Builder()
                    .setId(3L).setWeight(56).setRole("ROLE_USER").setHeight(160).setGender("MALE").setActivityLevel("FIRST")
                    .setUsername("dfgh").setDateOfBirth(LocalDate.now().minusYears(32)).setLastName("Jiel").setFirstName("Jake")
                    .setPassword(PASSWORD_2).build(),
            new User.Builder()
                    .setId(4L).setWeight(67).setRole("ROLE_USER").setHeight(180).setGender("MALE").setActivityLevel("FORTH")
                    .setUsername("tommy").setDateOfBirth(LocalDate.now().minusYears(42)).setLastName("Ковальва").setFirstName("Юлія")
                    .setPassword(PASSWORD_3).build(),
            new User.Builder()
                    .setId(5L).setWeight(77).setRole("ROLE_USER").setHeight(190).setGender("MALE").setActivityLevel("FIFTH")
                    .setUsername("fiveone").setDateOfBirth(LocalDate.now().minusYears(34)).setLastName("Bittlesome").setFirstName("Max")
                    .setPassword(PASSWORD_4).build(),
            new User.Builder()
                    .setId(6L).setWeight(50).setRole("ROLE_ADMIN").setHeight(166).setGender("FEMALE").setActivityLevel("THIRD")
                    .setUsername("lrrred").setDateOfBirth(LocalDate.now().minusYears(18)).setLastName("Loganson").setFirstName("Carl")
                    .setPassword(PASSWORD_5).build()
    ));
}
