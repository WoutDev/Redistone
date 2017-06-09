package be.woutdev.redistone.impl.data.storage.adapter.model.sql;

import be.woutdev.redistone.entity.UMute;
import be.woutdev.redistone.impl.data.storage.adapter.USQLDataAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericMuteDao;

/**
 * Created by Wout on 28/05/2017.
 */
public class SQLMuteDao extends SQLGenericDao<UMute> implements GenericMuteDao
{
    public SQLMuteDao(USQLDataAdapter adapter)
    {
        super(adapter, "");
    }

    @Override
    public void insert(UMute uMute)
    {
        super.insertObj(uMute);
    }

    @Override
    public void update(UMute uMute)
    {
        super.updateObj(uMute);
    }

    @Override
    public void delete(UMute uMute)
    {
        super.deleteObj(uMute);
    }

    @Override
    protected String getId(UMute object)
    {
        return object.getTarget().getEntityIdentifier();
    }
}
