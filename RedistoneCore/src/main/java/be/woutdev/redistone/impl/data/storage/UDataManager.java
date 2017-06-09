package be.woutdev.redistone.impl.data.storage;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.config.Config;
import be.woutdev.redistone.api.data.messaging.RedisMessageManager;
import be.woutdev.redistone.api.data.storage.DataManager;
import be.woutdev.redistone.api.data.storage.DataStorageType;
import be.woutdev.redistone.api.data.storage.adapter.DataAdapter;
import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.permission.Rank;
import be.woutdev.redistone.api.profiles.ProfileHandler;
import be.woutdev.redistone.api.warning.Warning;
import be.woutdev.redistone.entity.*;
import be.woutdev.redistone.impl.data.messaging.URedisMessageManager;
import be.woutdev.redistone.impl.data.storage.adapter.USQLDataAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.gson.EntityIdAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.gson.RankAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.gson.WarningAdapter;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericBanDao;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericMuteDao;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericUserDao;
import be.woutdev.redistone.impl.data.storage.adapter.model.sql.SQLBanDao;
import be.woutdev.redistone.impl.data.storage.adapter.model.sql.SQLMuteDao;
import be.woutdev.redistone.impl.data.storage.adapter.model.sql.SQLUserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.cfg.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by Wout on 26/05/2017.
 */
public class UDataManager implements DataManager
{
    private final Gson gson;
    private final DataAdapter adapter;
    private final RedisMessageManager messageManager;
    private final GenericUserDao userDao;
    private final GenericBanDao banDao;
    private final GenericMuteDao muteDao;

    public UDataManager(Config config, ProfileHandler profileHandler)
    {
        this.gson = new GsonBuilder().serializeNulls()
                                     .registerTypeHierarchyAdapter(EntityId.class, new EntityIdAdapter())
                                     .registerTypeAdapter(Rank.class, new RankAdapter())
                                     .registerTypeAdapter(Warning.class, new WarningAdapter())
                                     .create();

        HashMap<String, Object> messagingOptions = (HashMap<String, Object>) config.get("messaging");
        this.messageManager = new URedisMessageManager(new JedisPool(new JedisPoolConfig(),
                                                                     (String) messagingOptions.get("hostname"),
                                                                     (int) messagingOptions.get("port")), gson);

        HashMap<String, Object> databaseOptions = (HashMap<String, Object>) config.get("database");

        DataStorageType type = Stream.of(DataStorageType.values()).filter(e -> e.name().equalsIgnoreCase(
                String.valueOf(databaseOptions.get("type")))).findFirst().orElse(null);

        if (type == null)
        {
            throw new RuntimeException("Invalid storage type " + databaseOptions.get("type") + "!");
        }

        if (type == DataStorageType.SQL)
        {
            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.dialect", (String) databaseOptions.get("sql-dialect"));
            cfg.setProperty("hibernate.connection.driver_class", (String) databaseOptions.get("sql-driver"));
            cfg.setProperty("hibernate.connection.url", (String) databaseOptions.get("sql-url"));
            cfg.setProperty("hibernate.connection.username", (String) databaseOptions.get("username"));
            cfg.setProperty("hibernate.connection.password", (String) databaseOptions.get("password"));
            cfg.setProperty("hibernate.connection.autoReconnect", "true");
            cfg.setProperty("hibernate.hbm2ddl.auto", "update");
            cfg.setProperty("hibernate.c3p0.min_size", "10");
            cfg.setProperty("hibernate.c3p0.max_statements", "50");
            cfg.setProperty("hibernate.hbm2ddl.auto", "update");
            cfg.addAnnotatedClass(UBan.class);
            cfg.addAnnotatedClass(UMute.class);
            cfg.addAnnotatedClass(UWarning.class);
            cfg.addAnnotatedClass(UOfflineUser.class);
            cfg.addAnnotatedClass(BasicProfile.class);

            profileHandler.getProfiles().forEach((n, p) -> cfg.addAnnotatedClass(p.getClass()));

            adapter = new USQLDataAdapter(cfg.buildSessionFactory());

            userDao = new SQLUserDao((USQLDataAdapter) adapter);
            banDao = new SQLBanDao((USQLDataAdapter) adapter);
            muteDao = new SQLMuteDao((USQLDataAdapter) adapter);
        }
        else
        {
            /*OgmConfiguration cfg = new OgmConfiguration();
            cfg.setProperty("hibernate.ogm.datastore.provider", "mongodb");
            cfg.setProperty("hibernate.ogm.datastore.host", (String) databaseOptions.get("host"));
            cfg.setProperty("hibernate.ogm.datastore.port", (String) databaseOptions.get("port"));
            cfg.setProperty("hibernate.ogm.datastore.database", (String) databaseOptions.get("database"));
            cfg.setProperty("hibernate.ogm.datastore.create_database", "true");

            adapter = new UMongoDataAdapter(cfg.buildSessionFactory());

            userDao = new MongoUserDao((UMongoDataAdapter) adapter);
            banDao = new MongoBanDao((UMongoDataAdapter) adapter);
            muteDao = new MongoMuteDao((UMongoDataAdapter) adapter);*/

            adapter = null;
            userDao = null;
            banDao = null;
            muteDao = null;
        }
    }

    @Override
    public DataStorageType getType()
    {
        return adapter.getType();
    }

    @Override
    public DataAdapter getAdapter()
    {
        return adapter;
    }

    @Override
    public RedisMessageManager getRedisMessageManager()
    {
        return messageManager;
    }

    @Override
    public Gson getGson()
    {
        return gson;
    }

    public GenericUserDao getUserDao()
    {
        return userDao;
    }

    public GenericBanDao getBanDao()
    {
        return banDao;
    }

    public GenericMuteDao getMuteDao()
    {
        return muteDao;
    }
}
