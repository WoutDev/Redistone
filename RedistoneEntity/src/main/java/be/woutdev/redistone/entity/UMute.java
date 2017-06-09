package be.woutdev.redistone.entity;

import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.mute.Mute;
import be.woutdev.redistone.api.mute.MuteType;
import be.woutdev.redistone.api.mute.SimplifiedMuteType;
import be.woutdev.redistone.entity.converter.EntityIdConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wout on 14/04/2017.
 */
@Entity
public class UMute implements Mute
{
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(value = EnumType.STRING)
    private MuteType type;
    @Transient
    private SimplifiedMuteType simplifiedMuteType;
    @Column
    private String reason;
    @Column
    @Convert(converter = EntityIdConverter.class)
    private EntityId mutedBy;
    @Column
    private Date expireDate;
    @Column
    private Date muteDate;
    @Column
    @Convert(converter = EntityIdConverter.class)
    private EntityId target;

    protected UMute()
    {
    } // JPA requirement

    public UMute(EntityId target, MuteType type, String reason, EntityId mutedBy)
    {
        this.target = target;
        this.type = type;
        this.reason = reason;
        this.mutedBy = mutedBy;
        this.expireDate = new Date();
        this.muteDate = new Date();

        calculateSimplifiedMuteType();
    }

    public UMute(EntityId target, MuteType type, String reason, EntityId mutedBy, Date expireDate)
    {
        this.target = target;
        this.type = type;
        this.reason = reason;
        this.mutedBy = mutedBy;
        this.expireDate = expireDate;
        this.muteDate = new Date();

        calculateSimplifiedMuteType();
    }

    public UMute(EntityId target, MuteType type, String reason, EntityId mutedBy, Date expireDate, Date muteDate)
    {
        this.target = target;
        this.type = type;
        this.reason = reason;
        this.mutedBy = mutedBy;
        this.expireDate = expireDate;
        this.muteDate = muteDate;

        calculateSimplifiedMuteType();
    }

    public MuteType getType()
    {
        return type;
    }

    public SimplifiedMuteType getSimplifiedType()
    {
        return simplifiedMuteType;
    }

    public String getReason()
    {
        return reason;
    }

    public EntityId getMutedBy()
    {
        return mutedBy;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public Date getMuteDate()
    {
        return muteDate;
    }

    public EntityId getTarget()
    {
        return target;
    }

    public boolean isActive()
    {
        return simplifiedMuteType == SimplifiedMuteType.PERMANENT ||
               System.currentTimeMillis() < getExpireDate().getTime();
    }

    @PostLoad
    public void calculateSimplifiedMuteType()
    {
        this.simplifiedMuteType = (type == MuteType.IP_PERMANENT || type ==
                                                                    MuteType.NORMAL_PERMANENT) ? SimplifiedMuteType.PERMANENT : SimplifiedMuteType.TEMPORARILY;
    }
}
