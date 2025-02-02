package Persistence;

import Business.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Custom deserializer to convert a JsonElement into a Stat object.
 */
public class StatDeserializer implements JsonDeserializer<Stat> {

    /**
     * Deserializes a JsonElement into a Stat object.
     *
     * @param json       JSON element to deserialize.
     * @param typeOfT    Target type of the deserialization.
     * @param context    Deserialization context.
     * @return Deserialized Character object.
     * @throws JsonParseException If there is an error during deserialization.
     */
    @Override
    public Stat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        int gamesPlayed = jsonObject.has("games_played") ? jsonObject.get("games_played").getAsInt() : 0;
        int gamesWon = jsonObject.has("games_won") ? jsonObject.get("games_won").getAsInt() : 0;
        int koDone = jsonObject.has("KO_done") ? jsonObject.get("KO_done").getAsInt() : 0;
        int koReceived = jsonObject.has("KO_received") ? jsonObject.get("KO_received").getAsInt() : 0;

        Stat stat = new Stat(name);

        stat.updateStats(gamesPlayed, gamesWon, koDone, koReceived);

        return stat;
    }
}
