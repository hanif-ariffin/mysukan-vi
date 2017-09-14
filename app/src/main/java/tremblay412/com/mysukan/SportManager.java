package tremblay412.com.mysukan;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarin on 12/09/17.
 */

public class SportManager {

    private final static String TAG = "SportManager";

    public static List<String> getListOfSport() {
        List<String> sports = new ArrayList<>();
        sports.add("Soccer");
        return sports;
    }

    public static String convertToDb(String sportName) {
        String result = "";
        switch (sportName) {
            case ("Soccer"):
                result = "soccer";
                break;
            default:
                Log.wtf(TAG, "SPORT NAME NOT FOUND");
                result = "SPORT NAME NOT FOUND";
        }

        return result;
    }

    public static List<String> getGames() {
        List<String> lGames = new ArrayList<>();
        lGames.add("Volleyball");
        lGames.add("Telematch (F)");
        lGames.add("Dodgeball");
        lGames.add("Frisbee");
        lGames.add("Badminton");
        lGames.add("Squash");
        lGames.add("Volleyball (F)");
        lGames.add("Netball");
        lGames.add("Basketball");
        lGames.add("Football");
        lGames.add("Fifa (E-Games)");
        lGames.add("Rocket League (E-games)");
        return lGames;
    }
}