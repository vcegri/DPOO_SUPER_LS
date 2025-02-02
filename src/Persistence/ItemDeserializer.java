package Persistence;

import Business.Item;
import Business.SuperItem; // Sustituir con subclases reales
import Business.StandardItem; // Sustituir con subclases reales
import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Custom deserializer to convert a JsonElement into a Item object.
 */
public class ItemDeserializer implements JsonDeserializer<Item> {

    /**
     * Deserializes a JsonElement into a Item object.
     *
     * @param json       JSON element to deserialize.
     * @param typeOfT    Target type of the deserialization.
     * @param context    Deserialization context.
     * @return Deserialized Character object.
     * @throws JsonParseException If there is an error during deserialization.
     */
    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        int power = jsonObject.get("power").getAsInt();
        int durability = jsonObject.get("durability").getAsInt();
        String classe = jsonObject.get("class").getAsString();

        switch (classe) {
            case "Superarmor", "Superweapon":
                return new SuperItem(id, name, power, durability, classe);
            case "Weapon", "Armor":
                return new StandardItem(id, name, power, durability, classe);
            default:
                throw new JsonParseException("Unknown item type: " + classe);
        }
    }
}
