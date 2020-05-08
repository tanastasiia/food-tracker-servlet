package ua.training.model.dao;

import ua.training.model.dao.GenericDao;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsername(String username) throws ServerException;
}
