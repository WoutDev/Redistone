package be.woutdev.redistone.api.data.messaging;

import java.util.UUID;

/**
 * Used to send updates through Redis pub/sub
 *
 * @author Wout Ceulemans
 * @see RedisMessageManager
 */
public class Message
{
    private UUID id;
    private String[] args;
    private String type;

    /**
     * Create a new Message-object with an unique identifying id, arguments (empty if not applicable) and a type.
     *
     * @param id The id
     * @param args The arguments, empty if not applicable for the update
     * @param type The type of update
     */
    public Message(UUID id, String[] args, String type)
    {
        this.id = id;
        this.args = args;
        this.type = type;
    }

    /**
     * Get the id
     *
     * @return The id
     */
    public UUID getId()
    {
        return id;
    }

    /**
     * Get the arguments
     *
     * @return The arguments
     */
    public String[] getArgs()
    {
        return args;
    }

    /**
     * Get the type of update
     *
     * @return The type of update
     */
    public String getType()
    {
        return type;
    }
}
