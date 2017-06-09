package be.woutdev.redistone.api.module;

import be.woutdev.redistone.api.config.Config;
import be.woutdev.redistone.api.module.command.CommandListener;
import be.woutdev.redistone.api.module.event.EventListener;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * PluginModule class which has to be inherited from by all PluginModules
 *
 * @author Wout Ceulemans
 */
public abstract class PluginModule
{
    private final List<EventListener> eventListeners;
    private final List<CommandListener> commandListeners;
    private String name;
    private String version;
    private Config config;

    public PluginModule()
    {
        this.eventListeners = Lists.newArrayList();
        this.commandListeners = Lists.newArrayList();
    }

    /**
     * Override to execute code on startup of the module
     */
    public void onEnable()
    {

    }

    /**
     * Override to execute code on exit of the module
     */
    public void onDisable()
    {

    }

    /**
     * Get the Config (if applicable)
     * A file called 'config.yml' should be present in the root of the jar file.
     * If this file is missing, no Config will be created and this will return null.
     *
     * @return A Config object if applicable, null otherwise
     */
    public final Config getConfig()
    {
        return config;
    }

    /**
     * Get the name of the Module, specified in the module.yml
     *
     * @return The name of the module
     */
    public final String getModuleName()
    {
        return name;
    }

    /**
     * Get the version of the Module, specified in the module.yml
     *
     * @return The version of the module
     */
    public final String getModuleVersion()
    {
        return version;
    }

    /**
     * Get all event listeners that are registered by this PluginModule.
     * Use this to register new listeners!
     *
     * @return A List of EventListeners
     */
    public final List<EventListener> getEventListeners()
    {
        return eventListeners;
    }

    /**
     * Get all command listeners that are registered by this PluginModule.
     * Use this to register new command listeners!
     *
     * @return A List of CommandListeners
     */
    public final List<CommandListener> getCommandListeners()
    {
        return commandListeners;
    }
}
