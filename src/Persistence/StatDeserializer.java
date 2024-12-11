package Persistence;

import Business.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Deserializador personalizado para convertir un JsonElement en un objeto Stat.
 */
public class StatDeserializer implements JsonDeserializer<Stat> {
    /**
     * Deserializa un JsonElement en un objeto Stat.
     *
     * @param json    Elemento JSON a deserializar.
     * @param typeOfT Tipo de destino de la deserialización.
     * @param context Contexto de deserialización.
     * @return Objeto Stat deserializado.
     * @throws JsonParseException Si hay un error durante la deserialización.
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
