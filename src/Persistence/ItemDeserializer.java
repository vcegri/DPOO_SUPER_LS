package Persistence;

import Business.Item;
import Business.SuperItem; // Sustituir con subclases reales
import Business.StandardItem; // Sustituir con subclases reales
import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Deserializador personalizado para convertir un JsonElement en un objeto Item.
 */
public class ItemDeserializer implements JsonDeserializer<Item> {

    /**
     * Deserializa un JsonElement en un objeto Item.
     *
     * @param json       Elemento JSON a deserializar.
     * @param typeOfT    Tipo de destino de la deserialización.
     * @param context    Contexto de deserialización.
     * @return Objeto Item deserializado.
     * @throws JsonParseException Si hay un error durante la deserialización.
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
