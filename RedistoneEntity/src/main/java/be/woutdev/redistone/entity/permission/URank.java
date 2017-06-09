package be.woutdev.redistone.entity.permission;

import be.woutdev.redistone.api.permission.Group;
import be.woutdev.redistone.api.permission.Rank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Wout on 14/04/2017.
 */
@Entity
public class URank implements Rank
{
    @Id
    private long id;
    @ManyToOne(targetEntity = UGroup.class)
    private Group group;
    @Column
    private String server;

    protected URank()
    {
    } // JPA requirement

    public URank(Group group, String server)
    {
        this.group = group;
        this.server = server;
    }

    public Group getGroup()
    {
        return group;
    }

    public String getApplicableServer()
    {
        return server;
    }

    public boolean isGlobal()
    {
        return server.equals("___GLOBAL___");
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public void setServer(String server)
    {
        this.server = server;
    }
}
