package be.woutdev.redistone.api.time;

/**
 * The TimeFormatter used to format data.
 *
 * @author Seth Mcallister
 */
public interface TimeFormatter
{
    String getFormatted(long time);

    long getTimeFromString(String time);
}
