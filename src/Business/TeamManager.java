package Business;

import java.util.ArrayList;

public class TeamManager {

    public ArrayList<String> getNameOfTeams(){
        ArrayList<String> nameList = new ArrayList<>();

        for (int i = 0; i < teamList.size(); i++) {
            nameList.add(teamList.get(i).getName());
        }
        return (nameList);
    }

    public ArrayList<String> searchTeamsOfCharacter(int character){
        ArrayList<String> teamNameList = new ArrayList<>();
        ArrayList<Integer> memberList = new ArrayList<>();

        for (int i = 0; i < teamList.size(); i++) {
            memberList = teamList.get(i).getMemberList();
            for (int j = 0; j < memberList.size(); j++) {
                if (character == memberList.get(j)){
                    teamNameList.add(teamList.get(i).getName());
                }
            }
        }
        return (teamNameList);
    }

    public boolean comproveIfTeamExist(String newName){
        boolean exist = false;
        ArrayList<String> teamNameList = new ArrayList<>();

        teamNameList = getNameOfTeams(teamList);
        for (int i = 0; i < teamNameList.size(); i++) {
            if (newName.equals(teamNameList.get(i))){
                exist = true;
            }
        }
        return(exist);
    }
}
