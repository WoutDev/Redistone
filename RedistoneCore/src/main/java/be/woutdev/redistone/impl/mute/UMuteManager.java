package be.woutdev.redistone.impl.mute;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.mute.Mute;
import be.woutdev.redistone.api.mute.MuteManager;
import be.woutdev.redistone.api.mute.MuteType;
import be.woutdev.redistone.api.mute.SimplifiedMuteType;
import be.woutdev.redistone.entity.UMute;
import be.woutdev.redistone.impl.data.storage.UDataManager;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericMuteDao;

import java.util.Date;

/**
 * Created by Wout on 14/04/2017.
 */
public class UMuteManager implements MuteManager
{
    private final GenericMuteDao dao;

    public UMuteManager(UDataManager manager)
    {
        this.dao = manager.getMuteDao();
    }

    public void addMute(Mute mute)
    {
        dao.insert((UMute) mute);
    }

    public void removeMute(Mute mute)
    {
        dao.delete((UMute) mute);
    }

    public Mute getMute(EntityId target)
    {
        return dao.find(target.getEntityIdentifier());
    }

    @Override
    public Mute createMute(EntityId s, String s1, MuteType muteType, EntityId s2)
    {
        return new UMute(s, muteType, s1, s2);
    }

    @Override
    public Mute createMute(EntityId s, String s1, MuteType muteType, EntityId s2, Date date)
    {
        return new UMute(s, muteType, s1, s2, date);
    }

    @Override
    public boolean isMuted(EntityId id)
    {
        Mute mute = getMute(id);

        return mute != null && mute.isActive();
    }

    @Override
    public String getMuteMessage(Mute mute)
    {
        String message = "";

        message += "&cYou were " + (mute.getSimplifiedType() == SimplifiedMuteType.TEMPORARILY ? "temporarily" : "") +
                   " muted and can no longer chat on the HCFPvP network.\n";

        if (mute.getSimplifiedType() == SimplifiedMuteType.TEMPORARILY)
        {
            message += "&c&oExpiration: " + API.getTimeFormatter().getFormatted(mute.getExpireDate().getTime()) + "\n";
        }

        message += "&cInformation and appeal: ts.hcfpvp.net";

        return message;
    }
}
