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
 * Serializer personalizado para convertir un objeto Team en un JsonElement.
 */
public class TeamSerializer implements JsonSerializer<Team> {

    @Override
    public JsonElement serialize(Team team, Type typeOfSrc, JsonSerializationContext context) {
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
