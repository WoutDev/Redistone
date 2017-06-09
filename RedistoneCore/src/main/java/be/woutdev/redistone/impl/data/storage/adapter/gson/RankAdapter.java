package be.woutdev.redistone.impl.data.storage.adapter.gson;

import be.woutdev.redistone.api.permission.Rank;
import be.woutdev.redistone.entity.permission.URank;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Wout on 14/05/2017.
 */
public class RankAdapter extends TypeAdapter<Rank>
{
    @Override
    public void write(JsonWriter jsonWriter, Rank rank) throws IOException
    {
        new GsonBuilder().create().toJson(rank, URank.class, jsonWriter);
    }

    @Override
    public Rank read(JsonReader jsonReader) throws IOException
    {
        return new GsonBuilder().create().fromJson(jsonReader, URank.class);
    }
}
