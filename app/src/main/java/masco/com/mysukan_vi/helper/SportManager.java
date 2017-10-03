package masco.com.mysukan_vi.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarin on 12/09/17.
 */

public class SportManager {

    public static List<String> getGames() {
        List<String> listOfGames = new ArrayList<>();
        listOfGames.add("Soccer");
        listOfGames.add("Badminton Men Doubles");
        listOfGames.add("Badminton Women Doubles");
        listOfGames.add("Badminton Mixed Doubles");
        listOfGames.add("Squash Men Singles");
        listOfGames.add("Squash Women Singles");
        listOfGames.add("Frisbee");
        listOfGames.add("Dodgeball");
        listOfGames.add("Netball");
        listOfGames.add("Basketball");
        listOfGames.add("Sepak Takraw");
        listOfGames.add("Volleyball");
        listOfGames.add("Fifa");
        listOfGames.add("Rocket League");
        return listOfGames;
    }

    public static boolean isSingleScore(String sportName) {
        if (sportName.equals("Badminton Men Doubles") || sportName.equals("Badminton Women Doubles") || sportName.equals("Badminton Mixed Doubles") || sportName.equals("Squash Men Singles") || sportName.equals("Squash Women Singles")) {
            return false;
        } else {
            return true;
        }
    }
}