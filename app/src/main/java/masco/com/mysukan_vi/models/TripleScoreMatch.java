package masco.com.mysukan_vi.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * TripleScoreMatch is NOT an exact copy of SingleScoreMatch, there are differences in its utility.
 * Specifically, SportDetailActivity will ALWAYS use TripleScoreMatch even when the sport is supposed to be a SingleScoreMatch.
 * Please refer to the documentation in SportDetailActivity for more information on this design.
 * Created by r3xas on 9/15/2017.
 */

public class TripleScoreMatch {
    public Long match_date, team_1_score_1, team_2_score_1, team_1_score_2, team_2_score_2, team_1_score_3, team_2_score_3;
    public String team_1_name, team_2_name, id, custom_name_1, custom_name_2;

    // Functions required for Firebase
    public TripleScoreMatch() {
        // Default constructor for Firebase
    }

    public TripleScoreMatch(Long match_date, String id, String team_1_name, String team_2_name, String custom_name_1, String custom_name_2, Long team_1_score_1, Long team_2_score_1, Long team_1_score_2, Long team_2_score_2, Long team_1_score_3, Long team_2_score_3) {
        this.id = id;
        this.team_1_name = team_1_name;
        this.team_2_name = team_2_name;
        this.custom_name_1 = custom_name_1;
        this.custom_name_2 = custom_name_2;
        this.team_1_score_1 = team_1_score_1;
        this.team_2_score_1 = team_2_score_1;
        this.team_1_score_2 = team_1_score_2;
        this.team_2_score_2 = team_2_score_2;
        this.team_1_score_3 = team_1_score_3;
        this.team_2_score_3 = team_2_score_3;
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
        map.put("team_1_score_2", team_1_score_2);
        map.put("team_1_score_3", team_1_score_3);

        // Second team
        map.put("team_2_name", team_2_name);
        map.put("custom_name_2", custom_name_2);
        map.put("team_2_score_1", team_2_score_1);
        map.put("team_2_score_2", team_2_score_2);
        map.put("team_2_score_3", team_2_score_3);

        return map;
    }
}
