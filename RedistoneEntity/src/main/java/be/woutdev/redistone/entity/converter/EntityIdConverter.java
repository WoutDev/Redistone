package be.woutdev.redistone.entity.converter;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.console.ConsoleUser;
import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.user.OfflineUser;
import be.woutdev.redistone.entity.UIPEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by Wout on 3/06/2017.
 */
@Converter
public class EntityIdConverter implements AttributeConverter<EntityId, String>
{
    public String convertToDatabaseColumn(EntityId entityId)
    {
        return entityId.getEntityIdentifier();
    }

    public EntityId convertToEntityAttribute(String s)
    {
        String[] id = s.split(Pattern.quote("|"));

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
