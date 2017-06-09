package be.woutdev.redistone.api.data.storage.adapter;

import org.hibernate.SessionFactory;

/**
 * Extending on the DataAdapter interface to provide an API for data storage with SQL
 *
 * @author Wout Ceulemans
 */
public interface SQLDataAdapter extends DataAdapter
{
    /**
     * Get the SessionFactory initialized by the Core
     *
     * @return A ready to use SessionFactory
     */
    SessionFactory getSessionFactory();
}
