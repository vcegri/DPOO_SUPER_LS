package Business;

import java.util.ArrayList;

public class Team {

    private final String name;
    private final ArrayList<TeamMember> memberList;

    public Team(String name, ArrayList<TeamMember> memberList) {
        this.name = name;
        this.memberList = memberList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TeamMember> getMemberList() {
        return memberList;
    }
}
