package tremblay412.com.mysukan;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Akarin on 9/9/2017.
 */

public class Match {

    private Team teamOne;
    private Team teamTwo;
    private boolean finished = false;
    private Team winner = null;

    public Match() {
        //Default constructor for use by Firebase database
    }

    public Match(Team teamOne, Team teamTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public boolean isFinished() {
        return (winner != null);
    }

    public void setWinner(int index) throws Exception {
        if (index == 0) {
            winner = teamOne;
        } else if (index == 1) {
            winner = teamTwo;
        } else {
            throw new Exception("A generic Match class only contain 2 teams, perhaps you wish to extend this class and override?");
        }
    }

    public Team getWinner() throws Exception {
        if (isFinished()) {
            return winner;
        } else {
            throw new Exception("No winner is decided yet.");
        }
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public Team getTeam(int index) throws Exception {
        if (index == 0) {
            return teamOne;
        } else if (index == 1) {
            return teamTwo;
        } else {
            throw new Exception("A generic Match class only contain 2 teams, perhaps you wish to extend this class and override?");
        }
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("team_one", teamOne);
        result.put("team_two", teamOne);
        result.put("winner", winner);

        return result;
    }
    // [END post_to_map]
}
