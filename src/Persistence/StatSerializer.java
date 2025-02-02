package Persistence;
import Business.Stat;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Serializer personalizado para convertir una instancia de Stat a un JsonElement.
 */
public class StatSerializer implements JsonSerializer<Stat> {

    /**
     * Serializes a Stat object into a JsonElement.
     *
     * @param stat    Stat object to serialize.
     * @param type    Type of the object to serialize.
     * @param context Serialization context.
     * @return JsonElement representing the serialized Team.
     */
    @Override
    public JsonElement serialize(Stat stat, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", stat.getName());
        jsonObject.addProperty("games_played", stat.getGamesPlayed());
        jsonObject.addProperty("games_won", stat.getGamesWon());
        jsonObject.addProperty("KO_done", stat.getKoDone());
        jsonObject.addProperty("KO_received", stat.getKoReceived());

        return jsonObject;
    }
}
