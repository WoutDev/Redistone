package be.woutdev.redistone.api.config;

import java.util.List;

/**
 * Config used in all modules and even by the Core itself.
 *
 * @author Wout Ceulemans
 */
public interface Config
{
    /**
     * Load or reload the configuration
     */
    void load();

    /**
     * Get a String from the config
     *
     * @param key The key to get the String from
     *
     * @return The String or null
     */
    String getString(String key);

    /**
     * Get a Character from the config
     *
     * @param key The key to get the Character from
     *
     * @return The Character or null
     */
    Character getChar(String key);

    /**
     * Get a Short from the config
     *
     * @param key The key to get the Short from
     *
     * @return The Short or null
     */
    Short getShort(String key);

    /**
     * Get a Integer from the config
     *
     * @param key The key to get the Integer from
     *
     * @return The Integer or null
     */
    Integer getInt(String key);

    /**
     * Get a Long from the config
     *
     * @param key The key to get the Long from
     *
     * @return The Long or null
     */
    Long getLong(String key);

    /**
     * Get a Float from the config
     *
     * @param key The key to get the Float from
     *
     * @return The Float or null
     */
    Float getFloat(String key);

    /**
     * Get a Double from the config
     *
     * @param key The key to get the Double from
     *
     * @return The Double or null
     */
    Double getDouble(String key);

    /**
     * Get a Boolean from the config
     *
     * @param key The key to get the Boolean from
     *
     * @return The Boolean or null
     */
    Boolean getBoolean(String key);

    /**
     * Get a List of String from the config
     *
     * @param key The key to get the List of String from
     *
     * @return The List of String or null
     */
    List<String> getStringList(String key);

    /**
     * Get a Object from the config
     *
     * @param key The key to get the Object from
     *
     * @return The Object or null
     */
    Object get(String key);

    /**
     * Check if a key is present in the Config
     *
     * @param key The key to check
     *
     * @return If the key is present
     */
    boolean hasKey(String key);
}
