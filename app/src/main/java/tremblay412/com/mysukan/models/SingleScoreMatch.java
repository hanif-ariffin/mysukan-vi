package tremblay412.com.mysukan.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akarin on 12/09/17.
 */

public class SingleScoreMatch {

    public Long match_date, team_1_score, team_2_score;
    public String team_1_name, team_2_name, id;

    // Functions required for Firebase
    public SingleScoreMatch() {
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
        map.put("team_1_score", team_1_score);

        // Second team
        map.put("team_2_name", team_2_name);
        map.put("team_2_score", team_2_score);

        return map;
    }
}
