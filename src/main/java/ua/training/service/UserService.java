package ua.training.service;

import ua.training.controller.utils.Utility;
import ua.training.model.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.util.List;
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
        Optional<User> user = Optional.empty();
        try (UserDao dao = daoFactory.createUserDao()) {
            user =  dao.findByUsername(username);
        }
        return user;
    }

    public void registrate(User user) throws ServerException {
        //Optional<User> user = Optional.empty();
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
        }
    }


}
