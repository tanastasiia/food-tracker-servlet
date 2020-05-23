package ua.training.model.dao;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity) throws ServerException;
    Optional<T> findById(Long id) throws ServerException;
    List<T> findAll();
    void update(T entity);
    void delete(Long id);
    void close() throws ServerException;
}
