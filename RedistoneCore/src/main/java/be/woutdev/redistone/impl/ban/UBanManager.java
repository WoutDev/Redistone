package be.woutdev.redistone.impl.ban;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.ban.Ban;
import be.woutdev.redistone.api.ban.BanManager;
import be.woutdev.redistone.api.ban.BanType;
import be.woutdev.redistone.api.ban.SimplifiedBanType;
import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.entity.IPEntity;
import be.woutdev.redistone.entity.UBan;
import be.woutdev.redistone.impl.data.storage.UDataManager;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericBanDao;
import be.woutdev.redistone.impl.user.UUser;

import java.util.Date;

/**
 * Created by Wout on 14/04/2017.
 */
public class UBanManager implements BanManager
{
    private final GenericBanDao dao;

    public UBanManager(UDataManager manager)
    {
        this.dao = manager.getBanDao();
    }

    public void addBan(Ban ban)
    {
        dao.insert((UBan) ban);
    }

    public void removeBan(Ban b)
    {
        dao.delete((UBan) b);
    }

    public Ban getBan(EntityId target)
    {
        return dao.find(target.getEntityIdentifier());
    }

    @Override
    public Ban createBan(EntityId target, String reason, BanType type, EntityId bannedBy)
    {
        return new UBan(target, type, reason, bannedBy, null);
    }

    @Override
    public Ban createBan(EntityId target, String reason, BanType type, EntityId bannedBy, Date expireDate)
    {
        if (type == BanType.IP_PERMANENT || type == BanType.IP_TEMPORARILY)
        {
            if (target instanceof IPEntity)
            {
                return new UBan(target, type, reason, bannedBy, expireDate);
            }

            target = ((UUser) target).getStandardProfile().getMostRecentIP();
        }

        return new UBan(target, type, reason, bannedBy, expireDate);
    }

    @Override
    public boolean isBanned(EntityId id)
    {
        Ban ban = getBan(id);

        return ban != null && ban.isActive();
    }

    @Override
    public String getBanMessage(Ban ban)
    {
        String message = "";

        String type = "";

        if (ban.getSimplifiedType() == SimplifiedBanType.TEMPORARILY)
        {
            type = "temporarily banned";
        }
        else
        {
            type = "banned";
        }

        message += "&cYou were " + type + " from the HCFPvP network.\n";

        if (ban.getSimplifiedType() == SimplifiedBanType.TEMPORARILY)
        {
            message += "&c&oExpiration: " + API.getTimeFormatter().getFormatted(ban.getExpireDate().getTime()) + "\n";
        }

        message += "&cInformation: ts.hcfpvp.net";

        return message;
    }
}
