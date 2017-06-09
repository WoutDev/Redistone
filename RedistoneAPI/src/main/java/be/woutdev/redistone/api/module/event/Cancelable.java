package be.woutdev.redistone.api.module.event;

/**
 * Created by Wout on 13/04/2017.
 */
public interface Cancelable
{
    boolean isCancelled();

    void setCancelled(boolean cancelled);
}
