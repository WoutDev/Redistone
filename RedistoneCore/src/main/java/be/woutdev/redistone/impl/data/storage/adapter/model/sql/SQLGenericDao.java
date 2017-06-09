package be.woutdev.redistone.impl.data.storage.adapter.model.sql;

import be.woutdev.redistone.impl.data.storage.adapter.USQLDataAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.model.CloseableSession;
import com.google.common.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Wout on 27/05/2017.
 */
public abstract class SQLGenericDao<T>
{
    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {};
    private final Type type = typeToken.getType();
    private final USQLDataAdapter adapter;
    private final String identifier;

    protected SQLGenericDao(USQLDataAdapter adapter, String identifier)
    {
        this.adapter = adapter;
        this.identifier = identifier;
    }

    public void insertObj(T obj)
    {
        try (CloseableSession manager = adapter.getEntityManager())
        {
            manager.getInstance().getTransaction().begin();
            manager.getInstance().persist(obj);
            manager.getInstance().getTransaction().commit();
        }
    }

    public void updateObj(T obj)
    {
        try (CloseableSession manager = adapter.getEntityManager())
        {
            manager.getInstance().getTransaction().begin();
            manager.getInstance().merge(obj);
            manager.getInstance().getTransaction().commit();
        }
    }

    public void deleteObj(T obj)
    {
        try (CloseableSession manager = adapter.getEntityManager())
        {
            manager.getInstance().getTransaction().begin();
            manager.getInstance().delete(obj);
            manager.getInstance().getTransaction().commit();
        }
    }

    public T find(Object id)
    {
        try (CloseableSession manager = adapter.getEntityManager())
        {
            return (T) manager.getInstance().get(typeToken.getRawType(), (Serializable) id);
        }
    }

    public List<T> findAll()
    {
        try (CloseableSession manager = adapter.getEntityManager())
        {
            return manager.getInstance().createQuery("SELECT o FROM " + type.getTypeName() + " o").list();
        }
    }

    public USQLDataAdapter getAdapter()
    {
        return adapter;
    }

    protected String getId(T object)
    {
        Object id = "";
        try
        {
            Field idField = null;

            try
            {
                idField = object.getClass().getDeclaredField(identifier);
            }
            catch (NoSuchFieldException ex)
            {
                // ignored
            }

            if (idField == null)
            {
                idField = object.getClass().getSuperclass().getDeclaredField(identifier);
            }

            idField.setAccessible(true);
            id = idField.get(object);
            idField.setAccessible(false);
        }
        catch (NoSuchFieldException | IllegalAccessException ignored)
        {

        }

        return id.toString();
    }
}
