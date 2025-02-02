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
