package be.woutdev.redistone.impl.data.storage.adapter;

import be.woutdev.redistone.api.data.storage.DataStorageType;
import be.woutdev.redistone.api.data.storage.adapter.DataAdapter;

/**
 * Created by Wout on 26/05/2017.
 */
public abstract class UDataAdapter implements DataAdapter
{
    private final DataStorageType type;

    public UDataAdapter(DataStorageType type)
    {
        this.type = type;
    }

    @Override
    public DataStorageType getType()
    {
        return type;
    }

    public abstract void shutdown();
}
