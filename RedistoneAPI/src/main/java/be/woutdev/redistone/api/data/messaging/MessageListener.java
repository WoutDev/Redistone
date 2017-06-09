package be.woutdev.redistone.api.data.messaging;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.JedisPubSub;

/**
 * A JedisPubSub implementation that does the boilerplate updating for you.
 *
 * @author Wout Ceulemans
 * @see MessageHandler
 */
public class MessageListener extends JedisPubSub
{
    private final String type;
    private final MessageHandler handler;
    private final Gson gson;

    /**
     * Create a new MessageListener to listen for a specified type of update
     *
     * @param type The type to listen to
     * @param handler The implementation of the functional interface MessageHandler which will be called upon a update.
     */
    public MessageListener(String type, MessageHandler handler)
    {
        this.type = type;
        this.handler = handler;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public void onMessage(String channel, String message)
    {
        if (channel.equals("update"))
        {
            Message msg = gson.fromJson(message, Message.class);

            if (msg.getType().equalsIgnoreCase(type))
            {
                handler.handle(msg);
            }
        }
    }
}
