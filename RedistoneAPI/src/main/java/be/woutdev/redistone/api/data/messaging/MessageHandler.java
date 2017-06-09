package be.woutdev.redistone.api.data.messaging;

/**
 * Functional interface that can be used as lambda to handle updates.
 *
 * @author Wout Ceulemans
 */
public interface MessageHandler
{
    /**
     * Handle the update!
     *
     * @param message The message
     */
    void handle(Message message);
}
