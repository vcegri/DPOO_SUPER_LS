package Persistence;

import Business.Character;
import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Custom deserializer to convert a JsonElement into a Character object.
 */
public class CharacterDeserializer implements JsonDeserializer<Character> {

    /**
     * Deserializes a JsonElement into a Character object.
     *
     * @param json       JSON element to deserialize.
     * @param typeOfT    Target type of the deserialization.
     * @param context    Deserialization context.
     * @return Deserialized Character object.
     * @throws JsonParseException If there is an error during deserialization.
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
