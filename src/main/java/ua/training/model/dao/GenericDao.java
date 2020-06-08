package ua.training.model.dao;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity) throws ServerException;
    void close() throws ServerException;
}
