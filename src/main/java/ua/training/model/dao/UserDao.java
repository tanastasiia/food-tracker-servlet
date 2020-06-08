package ua.training.model.dao;

import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User>  {
    User create(User user) throws ServerException;

    void updateUser(User user) throws ServerException;

    void updateUserRole(long userId, String role) throws ServerException;

    List<User> findAll(int limit, int offset) throws ServerException;

    int countAll() throws ServerException;

    Optional<User> findByUsername(String username) throws ServerException;
}
