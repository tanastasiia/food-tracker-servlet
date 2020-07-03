package ua.training.model.dao;

import java.rmi.ServerException;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity) throws ServerException;
    void close() throws ServerException;
}
