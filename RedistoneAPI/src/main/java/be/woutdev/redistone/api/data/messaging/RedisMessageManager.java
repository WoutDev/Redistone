package be.woutdev.redistone.api.data.messaging;

import java.util.UUID;

/**
 * The message manager that handles data messaging related stuff with Redis
 *
 * @author Wout Ceulemans
 */
public interface RedisMessageManager
{
    /**
     * Register a MessageListener to listen for updates.
     * This is a blocking operation
     *
     * @param listener A new instance of the MessageListener
     */
    void registerListener(MessageListener listener);

    /**
     * Send an update out that can be caught through a MessageListener on all servers which use this Core.
     *
     * @param type The type of update
     * @param id The unique id identifying the object to update
     */
    void sendUpdate(String type, UUID id);

    /**
     * Send a update through a custom Message-object
     *
     * @param message The message to send
     */
    void sendUpdate(Message message);
}
