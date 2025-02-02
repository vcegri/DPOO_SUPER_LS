package Persistence;
import Business.Team;
import Business.TeamMember;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Custom serializer to convert a Team object into a JsonElement.
 */
public class TeamSerializer implements JsonSerializer<Team> {

    /**
     * Serializes a Team object into a JsonElement.
     *
     * @param team    Team object to serialize.
     * @param type    Type of the object to serialize.
     * @param context Serialization context.
     * @return JsonElement representing the serialized Team.
     */
    @Override
    public JsonElement serialize(Team team, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", team.getName());

        JsonArray membersArray = new JsonArray();
        for (TeamMember member : team.getMemberList()) {
            JsonElement memberJson = context.serialize(member, TeamMember.class);
            membersArray.add(memberJson);
        }

        jsonObject.add("members", membersArray);

        return jsonObject;
    }
}
