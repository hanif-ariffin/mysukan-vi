package tremblay412.com.mysukan;

import java.util.ArrayList;

/**
 * Created by Akarin on 9/9/2017.
 */

public class SportDetail {

    private String sportName;
    private ArrayList<Match> matches;

    public boolean equals(SportDetail sport) {
        return sport.getSportName().equals(sportName);
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addMatch(Team teamOne, Team teamTwo) {
        matches.add(new Match(teamOne, teamTwo));
    }
}
