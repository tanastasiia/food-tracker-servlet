package ua.training.service;

import ua.training.model.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.Meal;
import ua.training.model.entity.User;

import javax.servlet.http.HttpSession;
import java.rmi.ServerException;
import java.util.Optional;

public class UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        private static final UserService INSTANCE  = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<User> findByUsername(String username) throws ServerException {

        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username);
        }

    }

    public boolean register(User user) throws ServerException {

        if (findByUsername(user.getUsername()).isPresent()){
            return false;
        }
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
            return true;
        }
    }

    public void addFood(Food food, User adder){


    }
    public void addMeal(Meal food){

    }


}
