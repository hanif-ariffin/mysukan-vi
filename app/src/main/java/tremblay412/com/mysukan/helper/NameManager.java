package tremblay412.com.mysukan.helper;

import android.util.Log;

import tremblay412.com.mysukan.R;

/**
 * Created by Haziq on 2017-09-11.
 */

public class NameManager {

    private static final String TAG = "NameManager";

    public static String DatabaseToUser(String data) {
        switch (data) {
            case "soccer":
                data = "Soccer";
                break;
            case "badminton_men_doubles":
                data = "Badminton Men Doubles";
                break;
            case "badminton_women_doubles":
                data = "Badminton Women Doubles";
                break;
            case "badminton_mixed_doubles":
                data = "Badminton Mixed Doubles";
                break;
            case "squash_men_singles":
                data = "Squash Men Singles";
                break;
            case "squash_women_singles":
                data = "Squash Women Singles";
                break;
            case "frisbee":
                data = "Frisbee";
                break;
            case "dodgeball":
                data = "Dodgeball";
                break;
            case "netball":
                data = "Netball";
                break;
            case "basketball":
                data = "Basketball";
                break;
            case "sepak_takraw":
                data = "Sepak Takraw";
                break;
            case "volleyball":
                data = "Volleyball";
                break;
            case "fifa":
                data = "Fifa";
                break;
            case "rocket_league":
                data = "Rocket League";
                break;
            default:
                Log.wtf(TAG, "Unable to find matching replacement for data:" + data);
                data = "UNDEFINED";
                break;
        }

        return data;
    }

    public static String UserToDatabase(String data) {

        switch (data) {
            case "Soccer":
                data = "soccer";
                break;
            case "Badminton Men Doubles":
                data = "badminton_men_doubles";
                break;
            case "Badminton Women Doubles":
                data = "badminton_women_doubles";
                break;
            case "Badminton Mixed Doubles":
                data = "badminton_mixed_doubles";
                break;
            case "Squash Men Singles":
                data = "squash_men_singles";
                break;
            case "Squash Women Singles":
                data = "squash_women_singles";
                break;
            case "Frisbee":
                data = "frisbee";
                break;
            case "Dodgeball":
                data = "dodgeball";
                break;
            case "Netball":
                data = "netball";
                break;
            case "Basketball":
                data = "basketball";
                break;
            case "Sepak Takraw":
                data = "sepak_takraw";
                break;
            case "Volleyball":
                data = "volleyball";
                break;
            case "Fifa":
                data = "fifa";
                break;
            case "Rocket League":
                data = "rocket_league";
                break;
            default:
                Log.wtf(TAG, "Unable to find matching replacement for data:" + data);
                data = "UNDEFINED";
                break;
        }
        return data;
    }

    public static int getImageId(String data) {

        int dataInt = 0;

        switch (data) {
            case "Carleton":
                dataInt = R.drawable.carleton_logo;
                break;
            case "Toronto":
                dataInt = R.drawable.uoft_logo;
                break;
            case "Waterloo":
                dataInt = R.drawable.waterloo_logo;
                break;
            case "McMaster":
                dataInt = R.drawable.mcmaster_logo;
                break;
            case "McGill":
                dataInt = R.drawable.mcgill_logo;
                break;
            case "Guelph":
                dataInt = R.drawable.guelph_logo;
                break;
            case "UBC":
                dataInt = R.drawable.ubc_logo;
                break;
            case "Ottawa":
                dataInt = R.drawable.uottawa_logo;
                break;
            case "Queens":
                dataInt = R.drawable.queens_logo;
                break;
            default:
                Log.wtf(TAG, "Unable to find matching image for data:" + data);
                dataInt = 0;
                break;
        }
        return dataInt;
    }
}