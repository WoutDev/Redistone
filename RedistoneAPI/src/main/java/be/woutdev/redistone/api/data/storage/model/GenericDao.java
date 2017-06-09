package be.woutdev.redistone.api.data.storage.model;

import java.util.List;

/**
 * A generic data access object
 *
 * @author Wout Ceulemans
 */
public interface GenericDao<T>
{
    void insert(T object);

    void update(T object);

    void delete(T object);

    T find(Object id);

    List<T> findAll();
}
