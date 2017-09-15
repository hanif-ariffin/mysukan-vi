package tremblay412.com.mysukan.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by r3xas on 9/15/2017.
 */

public class TripleScoreMatch {
    public Long match_date, team_1_score_1, team_2_score_1, team_1_score_2, team_2_score_2, team_1_score_3, team_2_score_3;
    public String team_1_name, team_2_name, id;

    // Functions required for Firebase
    public TripleScoreMatch() {
        // Default constructor for Firebase
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();

        // Match data
        map.put("id", id);
        map.put("match_date", match_date);

        // First team
        map.put("team_1_name", team_1_name);
        map.put("team_1_score_1", team_1_score_1);
        map.put("team_1_score_2", team_1_score_2);
        map.put("team_1_score_3", team_1_score_3);

        // Second team
        map.put("team_2_name", team_2_name);
        map.put("team_2_score_1", team_2_score_1);
        map.put("team_2_score_2", team_2_score_2);
        map.put("team_2_score_3", team_2_score_3);

        return map;
    }
}
