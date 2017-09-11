package tremblay412.com.mysukan;

/**
 * Created by Haziq on 2017-09-10.
 */

public class SportSet {
    String team_1_name,team_2_name;
    int team_1_score_1,team_2_score_1, team_1_score_2, team_2_score_2, team_1_score_3, team_2_score_3;

    public SportSet( String team_1_name,String team_2_name, int team_1_score_1, int team_2_score_1, int team_1_score_2, int team_2_score_2, int team_1_score_3, int team_2_score_3){

        this.team_1_name=  team_1_name;
        this.team_2_name = team_2_name;
        this.team_1_score_1 = team_1_score_1;
        this.team_2_score_1 = team_2_score_1;
        this.team_1_score_2 = team_1_score_2;
        this.team_2_score_2 = team_2_score_2;
        this.team_1_score_3 = team_1_score_3;
        this.team_2_score_3 = team_2_score_3;
    }
}
