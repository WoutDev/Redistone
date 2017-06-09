package be.woutdev.redistone.api.data.storage.adapter;

import be.woutdev.redistone.api.data.storage.DataStorageType;

/**
 * Generic DataAdapter interface that will be implemented by the required type of adapter
 */
public interface DataAdapter
{
    /**
     * Get the type of data storage that is configured for this server.
     *
     * @return The type of data storage configured
     */
    DataStorageType getType();

    /**
     * Shutdown the DataAdapter
     */
    void shutdown();
}
