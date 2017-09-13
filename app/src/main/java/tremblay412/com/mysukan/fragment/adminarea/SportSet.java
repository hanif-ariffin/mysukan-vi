package tremblay412.com.mysukan.fragment.adminarea;

/**
 * Created by Haziq on 2017-09-10.
 */

public class SportSet {
    String team_1_name,team_2_name;
    int team_1_score_1,team_2_score_1, team_1_score_2, team_2_score_2, team_1_score_3, team_2_score_3;

    public SportSet(){}

    public SportSet(String team_1_name, String team_2_name, int team_1_score_1, int team_2_score_1, int team_1_score_2, int team_2_score_2, int team_1_score_3, int team_2_score_3){

        this.team_1_name=  team_1_name;
        this.team_2_name = team_2_name;
        this.team_1_score_1 = team_1_score_1;
        this.team_2_score_1 = team_2_score_1;
        this.team_1_score_2 = team_1_score_2;
        this.team_2_score_2 = team_2_score_2;
        this.team_1_score_3 = team_1_score_3;
        this.team_2_score_3 = team_2_score_3;
    }
    public void setTeam_1_name(String team_1_name) {
        this.team_1_name = team_1_name;
    }

    public void setTeam_2_name(String team_2_name) {
        this.team_2_name = team_2_name;
    }

    public void setTeam_1_score_1(int team_1_score_1) {
        this.team_1_score_1 = team_1_score_1;
    }

    public void setTeam_2_score_1(int team_2_score_1) {
        this.team_2_score_1 = team_2_score_1;
    }

    public void setTeam_1_score_2(int team_1_score_2) {
        this.team_1_score_2 = team_1_score_2;
    }

    public void setTeam_2_score_2(int team_2_score_2) {
        this.team_2_score_2 = team_2_score_2;
    }

    public void setTeam_1_score_3(int team_1_score_3) {
        this.team_1_score_3 = team_1_score_3;
    }

    public void setTeam_2_score_3(int team_2_score_3) {
        this.team_2_score_3 = team_2_score_3;
    }

    public String getTeam_1_name() {
        return team_1_name;
    }

    public String getTeam_2_name() {
        return team_2_name;
    }

    public int getTeam_1_score_1() {
        return team_1_score_1;
    }

    public int getTeam_2_score_1() {
        return team_2_score_1;
    }

    public int getTeam_1_score_2() {
        return team_1_score_2;
    }

    public int getTeam_2_score_2() {
        return team_2_score_2;
    }

    public int getTeam_1_score_3() {
        return team_1_score_3;
    }

    public int getTeam_2_score_3() {
        return team_2_score_3;
    }

}
