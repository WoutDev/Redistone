package be.woutdev.redistone.impl.data.storage.adapter.model.sql;

import be.woutdev.redistone.entity.UBan;
import be.woutdev.redistone.impl.data.storage.adapter.USQLDataAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericBanDao;

/**
 * Created by Wout on 27/05/2017.
 */
public class SQLBanDao extends SQLGenericDao<UBan> implements GenericBanDao
{
    public SQLBanDao(USQLDataAdapter adapter)
    {
        super(adapter, "");
    }

    @Override
    public void insert(UBan uBan)
    {
        super.insertObj(uBan);
    }

    @Override
    public void update(UBan uBan)
    {
        super.updateObj(uBan);
    }

    @Override
    public void delete(UBan uBan)
    {
        super.deleteObj(uBan);
    }

    @Override
    protected String getId(UBan object)
    {
        return object.getTarget().getEntityIdentifier();
    }
}
