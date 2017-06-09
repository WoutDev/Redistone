package be.woutdev.redistone.impl.data.storage.adapter.model.sql;

import be.woutdev.redistone.entity.UOfflineUser;
import be.woutdev.redistone.impl.data.storage.adapter.USQLDataAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericUserDao;

/**
 * Created by Wout on 28/05/2017.
 */
public class SQLUserDao extends SQLGenericDao<UOfflineUser> implements GenericUserDao
{
    public SQLUserDao(USQLDataAdapter adapter)
    {
        super(adapter, "uniqueId");
    }

    @Override
    public void insert(UOfflineUser user)
    {
        super.insertObj(user);
    }

    @Override
    public void update(UOfflineUser user)
    {
        super.updateObj(user);
    }

    @Override
    public void delete(UOfflineUser user)
    {
        super.deleteObj(user);
    }
}
