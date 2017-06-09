package be.woutdev.redistone.impl.data.storage.adapter;

import be.woutdev.redistone.api.data.storage.DataStorageType;
import be.woutdev.redistone.api.data.storage.adapter.SQLDataAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.model.CloseableSession;
import org.hibernate.SessionFactory;

/**
 * Created by Wout on 26/05/2017.
 */
public class USQLDataAdapter extends UDataAdapter implements SQLDataAdapter
{
    private final SessionFactory factory;

    public USQLDataAdapter(SessionFactory factory)
    {
        super(DataStorageType.SQL);

        this.factory = factory;
    }

    public CloseableSession getEntityManager()
    {
        return new CloseableSession(getSessionFactory().openSession());
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return factory;
    }

    @Override
    public void shutdown()
    {
        factory.close();
    }
}
