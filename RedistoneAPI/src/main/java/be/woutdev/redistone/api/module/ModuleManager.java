package be.woutdev.redistone.api.module;

import be.woutdev.redistone.api.module.command.CommandCaller;
import be.woutdev.redistone.api.module.command.CommandCallerType;
import be.woutdev.redistone.api.module.event.Event;

import java.util.List;

/**
 * Manages all kinds of operations on PluginModules
 *
 * @author Wout Ceulemans
 * @see PluginModule
 */
public interface ModuleManager
{
    /**
     * Get a list of all enables PluginModules on this server.
     *
     * @return A list of all enables PluginModules
     */
    List<PluginModule> getAllEnabledModules();

    /**
     * Find an enabled module by its name.
     *
     * @param name The name to search for
     *
     * @return The PluginModule if found, otherwise null
     */
    PluginModule findEnabledModule(String name);

    /**
     * Try to load a module. Might throw an exception in case of not succeeding.
     *
     * @param module The PluginModule object to enable
     */
    void enableModule(PluginModule module);

    /**
     * Try to load a module. Might throw an exception in case of not succeeding.
     *
     * @param name The name of the module to load.
     *
     * @throws ModuleLoadException Exception thrown if it doesn't succeed.
     */
    void enableModule(String name) throws ModuleLoadException;

    /**
     * Disable a module.
     *
     * @param module The PluginModule instance to disable.
     */
    void disableModule(PluginModule module);

    /**
     * Disable a module.
     *
     * @param name The module name to disable.
     */
    void disableModule(String name);

    /**
     * Check if a certain PluginModule is enabled.
     *
     * @param module The module to check
     *
     * @return If the specified PluginModule is enabled
     */
    boolean isEnabled(PluginModule module);

    /**
     * Check if a certain PluginModule by name is enabled.
     *
     * @param name The name to check
     *
     * @return If the specified PluginModule by name is enabled
     */
    boolean isEnabled(String name);

    /**
     * Trigger an Event which can be handled by all PluginModules listening for the certain Event
     *
     * @param event The Event to trigger
     *
     * @see Event
     */
    void triggerEvent(Event event);

    /**
     * Trigger an Command which can be handled by all PluginModules listening for the certain command
     *
     * @param caller The CommandCaller
     * @param type The type of the CommandCaller
     * @param name The name of the command
     *
     * @deprecated Removal
     */
    void triggerCommand(CommandCaller caller, CommandCallerType type, String name);
}
