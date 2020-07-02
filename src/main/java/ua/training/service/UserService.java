package ua.training.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.model.DaoFactory;
import ua.training.model.dao.MealDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.*;

import java.rmi.ServerException;
import java.time.LocalDate;
import java.time.Period;
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


    /**
     * Get users page
     * @param limit number of elements
     * @param offset number of first item
     * @return users
     * @throws ServerException
     */
    public List<User> findAllUsers(int limit, int offset) throws ServerException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll(limit, offset);
        }
    }

    /**
     * Count all users in database
     */
    public int countAllUsers() throws ServerException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.countAll();
        }
    }

    /**
     * Authentication by username
     * @param username username of user
     * @param password password of user
     * @return optional of sighed in user
     * @throws ServerException
     */
    public Optional<User> authentication(String username, String password) throws ServerException {

        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username)
                    .filter(u -> BCrypt.checkpw(password, u.getPassword()));
        }

    }

    /**
     * Finding user by username
     */
    private Optional<User> findByUsername(String username) throws ServerException {

        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username);
        }

    }

    /**
     * Registration of user
     * @param user user to registrate
     * @return true if succecfully registered false if user with such username already exists
     * @throws ServerException
     */
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

    /**
     * Updating user account information
     * @param newUser user with updated data
     * @param user user to update
     * @throws ServerException
     */
    public void updateAccount(User newUser, User user) throws ServerException {

        newUser.setId(user.getId());
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.updateUser(newUser);
        }
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setHeight(newUser.getHeight());
        user.setWeight(newUser.getWeight());
        user.setDateOfBirth(newUser.getDateOfBirth());
        user.setActivityLevel(newUser.getActivityLevel());
        user.setGender(newUser.getGender());

    }

    /**
     * Change role of user to opposite
     * @param userId of user whose role is being changed
     * @param role current user role
     * @throws ServerException
     */
    public void changeRole(long userId, String role) throws ServerException {

        role = role.equals(Role.ROLE_ADMIN.name()) ? Role.ROLE_USER.name() : Role.ROLE_ADMIN.name();

        try (UserDao dao = daoFactory.createUserDao()) {
            dao.updateUserRole(userId, role);
        }

    }

}
