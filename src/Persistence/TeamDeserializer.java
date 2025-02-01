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
        //JsonArray jsonArray = json.getAsJsonArray();
        //ArrayList<Team> teamList = new ArrayList<>();

        //for (int i = 0; i < jsonArray.size(); i++) {
          //  JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            String name = jsonObject.get("name").getAsString();
            ArrayList<TeamMember> members = new ArrayList<>();
            JsonArray membersArray = jsonObject.getAsJsonArray("members");

            for (JsonElement memberElement : membersArray) {
                JsonObject reviewObject = memberElement.getAsJsonObject();
                Long id = reviewObject.get("id").getAsLong();
                String strategy = reviewObject.get("strategy").getAsString();
                TeamMember member = new TeamMember(id, strategy);
                //TeamMember member = context.deserialize(memberElement, TeamMember.class);
                members.add(member);
            }
            //teamList.add(new Team(name, members));
        //}
        return new Team(name, members);
    }
}
