package be.woutdev.redistone.api.module.event;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Wout on 13/04/2017.
 */
public abstract class EventListener<T extends Event>
{
    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {};
    private final Type type = typeToken.getType();

    public final Type getType()
    {
        return type;
    }

    public abstract void handle(T e);
}
