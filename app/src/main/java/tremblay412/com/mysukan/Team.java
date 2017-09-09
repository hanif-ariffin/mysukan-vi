package tremblay412.com.mysukan;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Akarin on 9/9/2017.
 */

public class Team {
    private String teamName;

    public Team() {
        //Default constructor to be used by Firebase database
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getName() {
        return teamName;
    }

    public boolean equals(Team compareTeam) {
        return compareTeam.teamName.equals(teamName);
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("team_name", teamName);

        return result;
    }
    // [END post_to_map]
}
