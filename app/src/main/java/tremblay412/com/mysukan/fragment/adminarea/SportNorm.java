package tremblay412.com.mysukan.fragment.adminarea;

/**
 * Created by Haziq on 2017-09-10.
 */

public class SportNorm {
    String team_1_name,team_2_name;
    int team_1_score_1,team_2_score_1;

    public SportNorm(){}

    public SportNorm( String team_1_name,String team_2_name, int team_1_score, int team_2_score){

        this.team_1_name=  team_1_name;
        this.team_2_name = team_2_name;
        this.team_1_score_1 = team_1_score;
        this.team_2_score_1 = team_2_score;
    }


}
