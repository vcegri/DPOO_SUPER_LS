package Persistence;

import Business.Character;
import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Deserializador personalizado para convertir un JsonElement en un objeto Character.
 */

public class CharacterDeserializer implements JsonDeserializer<Character> {
    /**
     * Deserializa un JsonElement en un objeto Character.
     *
     * @param json       Elemento JSON a deserializar.
     * @param typeOfT    Tipo de destino de la deserialización.
     * @param context    Contexto de deserialización.
     * @return Objeto Character deserializado.
     * @throws JsonParseException Si hay un error durante la deserialización.
     */
    @Override
    public Character deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        int weight = jsonObject.get("weight").getAsInt();

        return new Character((int) id, name, weight);
    }
}
