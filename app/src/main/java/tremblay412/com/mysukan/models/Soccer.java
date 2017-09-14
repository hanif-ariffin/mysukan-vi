package tremblay412.com.mysukan.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akarin on 12/09/17.
 */

public class Soccer {

    public Long match_date, team_1_score_1, team_2_score_1;
    public String team_1_name, team_2_name, id;

    // Functions requuired for Firebase
    public Soccer() {
        // Default constructor for Firebase
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("match_date", match_date);
        map.put("team_1_name", team_1_name);
        map.put("team_1_score_1", team_1_score_1);
        map.put("team_2_name", team_2_name);
        map.put("team_2_score", team_2_score_1);

        return map;
    }
}
