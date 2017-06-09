package be.woutdev.redistone.api.data.storage;

import be.woutdev.redistone.api.data.messaging.RedisMessageManager;
import be.woutdev.redistone.api.data.storage.adapter.DataAdapter;
import com.google.gson.Gson;

/**
 * Main data storage manager
 *
 * @author Wout Ceulemans
 */
public interface DataManager
{
    /**
     * Get the type of data storage that is configured for this server.
     *
     * @return The type of data storage configured
     */
    DataStorageType getType();

    /**
     * Get the implementation of the data storage that is configured for this server.
     *
     * @return The implementation of data storage configured
     */
    DataAdapter getAdapter();

    /**
     * Get the RedisMessageManager to manage messaging with Redis.
     *
     * @return A RedisMessageManager implementation
     */
    RedisMessageManager getRedisMessageManager();

    /**
     * Get the standard Gson instance containing some standard adapters for the Core.
     *
     * @return The Core's Gson instance
     */
    Gson getGson();
}
