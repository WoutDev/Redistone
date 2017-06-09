package be.woutdev.redistone.impl.data.storage.adapter.gson;

import be.woutdev.redistone.api.warning.Warning;
import be.woutdev.redistone.entity.UWarning;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Wout on 23/05/2017.
 */
public class WarningAdapter extends TypeAdapter<Warning>
{
    @Override
    public void write(JsonWriter jsonWriter, Warning warning) throws IOException
    {
        new GsonBuilder().create().toJson(warning, UWarning.class, jsonWriter);
    }

    @Override
    public Warning read(JsonReader jsonReader) throws IOException
    {
        return new GsonBuilder().create().fromJson(jsonReader, UWarning.class);
    }
}
