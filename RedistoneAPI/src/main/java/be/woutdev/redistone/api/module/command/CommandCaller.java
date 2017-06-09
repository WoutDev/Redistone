package be.woutdev.redistone.api.module.command;

import be.woutdev.redistone.api.entity.EntityId;

/**
 * Created by Wout on 13/04/2017.
 */
public interface CommandCaller extends EntityId
{
    void sendMessage(String message);

    void sendUnformattedMessage(String message);
}
