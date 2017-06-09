package be.woutdev.redistone.api.module;

/**
 * Exception thrown when loading a module is not successful.
 *
 * @author Wout Ceulemans
 * @see ModuleManager
 */
public class ModuleLoadException extends Exception
{
    private final String message;

    public ModuleLoadException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
