package be.woutdev.redistone.impl.data.storage.adapter.model;

import org.hibernate.Session;

/**
 * Created by Wout on 27/05/2017.
 */
public class CloseableSession implements AutoCloseable
{
    private final Session session;

    public CloseableSession(Session session)
    {
        this.session = session;
    }

    public Session getInstance()
    {
        return session;
    }

    @Override
    public void close()
    {
        session.close();
    }
}
