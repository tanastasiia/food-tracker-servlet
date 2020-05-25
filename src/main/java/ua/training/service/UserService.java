package ua.training.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.model.DaoFactory;
import ua.training.model.dao.MealDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.*;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        private static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<User> findByUsername(String username) throws ServerException {

        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username);
        }

    }

    public List<User> findAllUsers(int limit, int offset) throws ServerException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll(limit, offset);

        }
    }

    public int countAllUsers() throws ServerException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.countAll();
        }
    }

    public Optional<UserDto> authentication(String username, String password) throws ServerException {

        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username)
                    .filter(u -> BCrypt.checkpw(password, u.getPassword()))
                    .map(UserDto::new);
        }

    }

    public boolean register(User user) throws ServerException {

        if (!findByUsername(user.getUsername()).isPresent()) {
            try (UserDao dao = daoFactory.createUserDao()) {
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                dao.create(user);
                return true;
            }
        }
        return false;
    }

    public void updateAccount(User newUser, UserDto user) throws ServerException {
        newUser.setId(user.getId());
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.updateUser(newUser);
        }
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setHeight(newUser.getHeight());
        user.setWeight(newUser.getWeight());
        user.setAge(newUser.getAge());
        user.setActivityLevel(newUser.getActivityLevel());
        user.setGender(newUser.getGender());

    }

    public void changeRole(long userId, String role) throws ServerException {
        if(role.equals(Role.ADMIN.name())){
            role = Role.USER.name();
        }
        else{
            role = Role.ADMIN.name();
        }
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.updateUserRole(userId, role);
        }

    }



    public int countCaloriesNorm(User user) {
        return (int) (ActivityLevel.valueOf(user.getActivityLevel()).getValue()
                * (Gender.valueOf(user.getGender()).getValue()
                + 10 * user.getWeight()
                + 6.25 * user.getHeight()
                - 5 * user.getAge()));
    }

}
