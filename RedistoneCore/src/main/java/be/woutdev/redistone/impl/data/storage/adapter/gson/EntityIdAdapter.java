package be.woutdev.redistone.impl.data.storage.adapter.gson;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.console.ConsoleUser;
import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.entity.IPEntity;
import be.woutdev.redistone.api.user.OfflineUser;
import be.woutdev.redistone.entity.UIPEntity;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by Wout on 23/04/2017.
 */
public class EntityIdAdapter extends TypeAdapter<EntityId>
{
    @Override
    public void write(JsonWriter writer, EntityId id) throws IOException
    {
        writer.value(id.getEntityIdentifier());
    }

    @Override
    public EntityId read(JsonReader reader) throws IOException
    {
        String[] id = reader.nextString().split(Pattern.quote("|"));

        if (id[0].equalsIgnoreCase("USER"))
        {
            OfflineUser user = API.getUserManager().findByUniqueId(UUID.fromString(id[1]));

            if (user != null)
            {
                return user;
            }
            else
            {
                return API.getUserManager().findOfflineByUniqueId(UUID.fromString(id[1]));
            }
        }
        else if (id[0].equalsIgnoreCase("CONSOLE"))
        {
            return new ConsoleUser(id[1]);
        }
        else
        {
            return new UIPEntity(id[1]);
        }
    }
}
