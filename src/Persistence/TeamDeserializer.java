package Persistence;
import Business.Team;
import Business.TeamMember;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Deserializer personalizado para convertir un JsonElement en un objeto Team.
 */
public class TeamDeserializer implements JsonDeserializer<Team> {

    @Override
    public Team deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();

        JsonArray membersArray = jsonObject.getAsJsonArray("members");
        ArrayList<TeamMember> members = new ArrayList<>();
        for (JsonElement memberElement : membersArray) {
            TeamMember member = context.deserialize(memberElement, TeamMember.class);
            members.add(member);
        }

        return new Team(name, members);
    }
}
