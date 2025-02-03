package Persistence;
import Business.Team;
import Business.TeamMember;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Custom deserializer to convert a JsonElement into a Team object.
 */
public class TeamDeserializer implements JsonDeserializer<Team> {

    /**
     * Deserializes a JsonElement into a Team object.
     *
     * @param json       JSON element to deserialize.
     * @param typeOfT    Target type of the deserialization.
     * @param context    Deserialization context.
     * @return Deserialized Character object.
     * @throws JsonParseException If there is an error during deserialization.
     */
    @Override
    public Team deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        ArrayList<TeamMember> members = new ArrayList<>();
        JsonArray membersArray = jsonObject.getAsJsonArray("members");

        for (JsonElement memberElement : membersArray) {
            JsonObject reviewObject = memberElement.getAsJsonObject();
            Long id = reviewObject.get("id").getAsLong();
            String strategy = reviewObject.get("strategy").getAsString();
            TeamMember member = new TeamMember(id, strategy);
            members.add(member);
        }

        return new Team(name, members);
    }
}
