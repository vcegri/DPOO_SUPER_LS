package Business;

import java.util.ArrayList;

public class Team {

    private final String name;
    private final ArrayList<Integer> memberList;

    public Team(String name, ArrayList<Integer> memberList) {
        this.name = name;
        this.memberList = memberList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getMemberList() {
        return memberList;
    }
}
