package tremblay412.com.mysukan;

/**
 * Created by Haziq on 2017-09-10.
 */

public class SportNorm {
    String team_1_name,team_2_name ,id;
    int team_1_score_1,team_2_score_1, match_date;

    public SportNorm(){}

    public String getTeam_1_name() {
        return team_1_name;
    }

    public void setTeam_1_name(String team_1_name) {
        this.team_1_name = team_1_name;
    }

    public void setTeam_2_name(String team_2_name) {
        this.team_2_name = team_2_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeam_1_score_1(int team_1_score_1) {
        this.team_1_score_1 = team_1_score_1;
    }

    public void setTeam_2_score_1(int team_2_score_1) {
        this.team_2_score_1 = team_2_score_1;
    }

    public String getTeam_2_name() {
        return team_2_name;
    }

    public String getId() {
        return id;
    }

    public int getTeam_1_score_1() {
        return team_1_score_1;
    }

    public int getTeam_2_score_1() {
        return team_2_score_1;
    }

    public SportNorm(String id, String team_1_name, String team_2_name, int team_1_score, int team_2_score){
        this.id = id;
        this.team_1_name=  team_1_name;
        this.team_2_name = team_2_name;
        this.team_1_score_1 = team_1_score;
        this.team_2_score_1 = team_2_score;

    }


}
