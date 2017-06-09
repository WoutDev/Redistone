package be.woutdev.redistone.impl.data.messaging;

import be.woutdev.redistone.api.data.messaging.Message;
import be.woutdev.redistone.api.data.messaging.MessageListener;
import be.woutdev.redistone.api.data.messaging.RedisMessageManager;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * Created by Wout on 14/04/2017.
 */
public class URedisMessageManager implements RedisMessageManager
{
    private final JedisPool pool;
    private final Gson gson;

    public URedisMessageManager(JedisPool pool, Gson gson)
    {
        this.pool = pool;
        this.gson = gson;

        /*this.banDao = new RedisBanDao(pool);
        this.muteDao = new RedisMuteDao(pool);
        this.userDao = new RedisUserDao(profileHandler, pool);

        new JedisPool(new JedisPoolConfig(), (String) databaseOptions.get("hostname"),
                                  (int) databaseOptions.get("port"));

        this.gson = new GsonBuilder().create();*/
    }

    @Override
    public void registerListener(MessageListener listener)
    {
        try (Jedis jedis = pool.getResource())
        {
            jedis.subscribe(listener, "update");
        }
    }

    @Override
    public void sendUpdate(String s, UUID uuid)
    {
        try (Jedis jedis = pool.getResource())
        {
            jedis.publish("update", gson.toJson(new Message(uuid, new String[] {}, s)));
        }
    }

    @Override
    public void sendUpdate(Message message)
    {
        try (Jedis jedis = pool.getResource())
        {
            jedis.publish("update", gson.toJson(message));
        }
    }
}
