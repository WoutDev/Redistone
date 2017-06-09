package be.woutdev.redistone.entity;

import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.warning.Warning;
import be.woutdev.redistone.entity.converter.EntityIdConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wout on 22/05/2017.
 */
@Entity
public class UWarning implements Warning
{
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String reason;
    @Column
    @Convert(converter = EntityIdConverter.class)
    private EntityId warnedBy;
    @Column
    private Date warnDate;

    protected UWarning()
    {
    } // JPA requirement

    public UWarning(String reason, EntityId warnedBy, Date warnDate)
    {
        this.reason = reason;
        this.warnedBy = warnedBy;
        this.warnDate = warnDate;
    }

    public String getReason()
    {
        return reason;
    }

    public EntityId getWarnedBy()
    {
        return warnedBy;
    }

    public Date getWarnDate()
    {
        return warnDate;
    }
}
