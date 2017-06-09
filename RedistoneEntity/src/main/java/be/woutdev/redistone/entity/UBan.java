package be.woutdev.redistone.entity;

import be.woutdev.redistone.api.ban.Ban;
import be.woutdev.redistone.api.ban.BanType;
import be.woutdev.redistone.api.ban.SimplifiedBanType;
import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.entity.converter.EntityIdConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wout on 14/04/2017.
 */
@Entity
public class UBan implements Ban
{
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private BanType type;
    @Transient
    private SimplifiedBanType simplifiedBanType;
    @Column
    private String reason;
    @Column
    @Convert(converter = EntityIdConverter.class)
    private EntityId bannedBy;
    @Column
    private Date expireDate;
    @Column
    private Date banDate;
    @Column
    @Convert(converter = EntityIdConverter.class)
    private EntityId target;

    protected UBan()
    {
    } // JPA requirement

    public UBan(EntityId target, BanType type, String reason, EntityId bannedBy)
    {
        this.target = target;
        this.type = type;
        this.reason = reason;
        this.bannedBy = bannedBy;
        this.expireDate = new Date();
        this.banDate = new Date();

        calculateSimplifiedBanType();
    }

    public UBan(EntityId target, BanType type, String reason, EntityId bannedBy, Date expireDate)
    {
        this.target = target;
        this.type = type;
        this.reason = reason;
        this.bannedBy = bannedBy;
        this.expireDate = expireDate;
        this.banDate = new Date();

        calculateSimplifiedBanType();
    }

    public UBan(EntityId target, BanType type, String reason, EntityId bannedBy, Date expireDate, Date banDate)
    {
        this.target = target;
        this.type = type;
        this.reason = reason;
        this.bannedBy = bannedBy;
        this.expireDate = expireDate;
        this.banDate = banDate;

        calculateSimplifiedBanType();
    }

    @PostLoad
    public void calculateSimplifiedBanType()
    {
        this.simplifiedBanType = (type == BanType.IP_PERMANENT || type ==
                                                                  BanType.NORMAL_PERMANENT) ? SimplifiedBanType.PERMANENT : SimplifiedBanType.TEMPORARILY;
    }

    public BanType getType()
    {
        return type;
    }

    public SimplifiedBanType getSimplifiedType()
    {
        return simplifiedBanType;
    }

    public String getReason()
    {
        return reason;
    }

    public EntityId getBannedBy()
    {
        return bannedBy;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public Date getBanDate()
    {
        return banDate;
    }

    public EntityId getTarget()
    {
        return target;
    }

    public boolean isActive()
    {
        return (simplifiedBanType == SimplifiedBanType.PERMANENT) ||
               System.currentTimeMillis() < getExpireDate().getTime();
    }

    public long getId()
    {
        return id;
    }
}
