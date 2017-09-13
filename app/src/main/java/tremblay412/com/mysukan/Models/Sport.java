package tremblay412.com.mysukan.Models;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Akarin on 9/9/2017.
 */

public class Sport {

    private String sportName;
    private ArrayList<Match> matches;

    public Sport(String sportName) {
        this.sportName = sportName;
    }

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

    //Firebase Support

    public Sport() {
        //Default constructor for Firebase
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", sportName);
        result.put("matches", (List) matches);
        return result;
    }

}
