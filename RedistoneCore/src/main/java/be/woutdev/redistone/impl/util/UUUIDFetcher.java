package be.woutdev.redistone.impl.util;

import be.woutdev.redistone.api.util.UUIDFetcher;

import java.util.Collections;
import java.util.UUID;

/**
 * Created by Wout on 14/04/2017.
 */
public class UUUIDFetcher implements UUIDFetcher
{
    public UUID findByUsername(String s)
    {
        try
        {
            return new be.woutdev.redistone.impl.util.UUIDFetcher(Collections.singletonList(s)).call().get(s);
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
