package be.woutdev.redistone.impl.module.event;

import be.woutdev.redistone.api.module.event.Cancelable;

/**
 * Created by Wout on 14/04/2017.
 */
public class UCancelable implements Cancelable
{
    private boolean isCancelled;

    public boolean isCancelled()
    {
        return isCancelled;
    }

    public void setCancelled(boolean b)
    {
        isCancelled = b;
    }
}
