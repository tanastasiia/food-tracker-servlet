package ua.training.model.dao;

import java.rmi.ServerException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity) throws ServerException;
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
