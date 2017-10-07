package masco.com.mysukan_vi.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * SingleScoreMatch is the match that will be pushed to the database when pushing a match with only 1 score.
 * Created by akarin on 12/09/17.
 */

public class SingleScoreMatch {

    public Long match_date, team_1_score_1, team_2_score_1;
    public String team_1_name, team_2_name, custom_name_1, custom_name_2, id;

    // Functions required for Firebase
    public SingleScoreMatch() {
        // Default constructor for Firebase
    }

    public SingleScoreMatch(Long match_date, String id, String team_1_name, String team_2_name, String custom_name_1, String custom_name_2, Long team_1_score, Long team_2_score) {
        this.id = id;
        this.team_1_name = team_1_name;
        this.team_2_name = team_2_name;
        this.custom_name_1 = custom_name_1;
        this.custom_name_2 = custom_name_2;
        this.team_1_score_1 = team_1_score;
        this.team_2_score_1 = team_2_score;
        this.match_date = match_date;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();

        // Match data
        map.put("id", id);
        map.put("match_date", match_date);

        // First team
        map.put("team_1_name", team_1_name);
        map.put("custom_name_1", custom_name_1);
        map.put("team_1_score_1", team_1_score_1);

        // Second team
        map.put("team_2_name", team_2_name);
        map.put("custom_name_2", custom_name_2);
        map.put("team_2_score_1", team_2_score_1);

        return map;
    }
}
