package tremblay412.com.mysukan.Models;

import java.util.ArrayList;

/**
 * Created by Akarin on 9/9/2017.
 */

public class Sport {

    private String sportName;
    private ArrayList<Match> matches;

    public boolean equals(Sport sport) {
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
