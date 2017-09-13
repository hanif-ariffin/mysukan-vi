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
}