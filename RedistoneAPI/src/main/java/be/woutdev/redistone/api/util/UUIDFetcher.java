package be.woutdev.redistone.api.util;

import java.util.UUID;

/**
 * Fetch username by UUID.
 *
 * @author Wout Ceulemans
 */
public interface UUIDFetcher
{
    UUID findByUsername(String username);
}
