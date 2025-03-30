package org.example.bluemoon.dao;

import java.util.List;

public interface CrudDAO<T, ID> {
    void save(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(ID key) throws Exception;

    T get(ID key) throws Exception;

    List<T> getAll() throws Exception;
}
