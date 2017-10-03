package masco.com.mysukan_vi.helper;

import masco.com.mysukan_vi.R;

/**
 * Created by Haziq on 2017-09-11.
 */

public class NameManager {

    public static class UniversityNames {
        public static final String CARLETON = "Carleton";
        public static final String TORONTO = "Toronto";
        public static final String WATERLOO = "Waterloo";
        public static final String MCMASTER = "McMaster";
        public static final String MCGILL = "McGill";
        public static final String GUELPH = "Guelph";
        public static final String UBC = "UBC";
        public static final String OTTAWA = "Ottawa";
        public static final String QUEENS = "Queens";
        public static final String CALGARY = "Calgary";

        public static String[] getUniversityNames() {
            return new String[]{
                    CARLETON, TORONTO, WATERLOO, MCMASTER, MCGILL, GUELPH, UBC, OTTAWA, QUEENS, CALGARY
            };
        }
    }

    public static class SportTypeSafeNames {
        public static final String SOCCER = "soccer";
        public static final String BADMINTON_MEN_DOUBLES = "badminton_men_doubles";
        public static final String BADMINTON_WOMEN_DOUBLES = "badminton_women_doubles";
        public static final String BADMINTON_MIXED_DOUBLES = "badminton_mixed_doubles";
        public static final String SQUASH_MEN_SINGLES = "squash_men_singles";
        public static final String SQUASH_WOMEN_SINGLES = "squash_women_singles";
        public static final String FRISBEE = "frisbee";
        public static final String DODGEBALL = "dodgeball";
        public static final String NETBALL = "netball";
        public static final String BASKETBALL = "basketball";
        public static final String SEPAK_TAKRAW = "sepak_takraw";
        public static final String VOLLEYBALL = "volleyball";
        public static final String FIFA = "fifa";
        public static final String ROCKET_LEAGUE = "rocket_league";

        public static String[] getSportTypeSafeNames() {
            return new String[]{
                    SOCCER, BADMINTON_MEN_DOUBLES, BADMINTON_WOMEN_DOUBLES, BADMINTON_MIXED_DOUBLES,
                    SQUASH_MEN_SINGLES, SQUASH_WOMEN_SINGLES, FRISBEE, DODGEBALL, NETBALL, BASKETBALL,
                    SEPAK_TAKRAW, VOLLEYBALL, FIFA, ROCKET_LEAGUE
            };
        }
    }

    public static class SportCasualNames {
        public static final String SOCCER = "Soccer";
        public static final String BADMINTON_MEN_DOUBLES = "Badminton Men Doubles";
        public static final String BADMINTON_WOMEN_DOUBLES = "Badminton Women Doubles";
        public static final String BADMINTON_MIXED_DOUBLES = "Badminton Mixed Doubles";
        public static final String SQUASH_MEN_SINGLES = "Squash Men Singles";
        public static final String SQUASH_WOMEN_SINGLES = "Squash Women Singles";
        public static final String FRISBEE = "Frisbee";
        public static final String DODGEBALL = "Dodgeball";
        public static final String NETBALL = "Netball";
        public static final String BASKETBALL = "Basketball";
        public static final String SEPAK_TAKRAW = "Sepak Takraw";
        public static final String VOLLEYBALL = "Volleyball";
        public static final String FIFA = "Fifa";
        public static final String ROCKET_LEAGUE = "Rocket League";

        public static String[] getSportCasualNames() {
            return new String[]{
                    SOCCER, BADMINTON_MEN_DOUBLES, BADMINTON_WOMEN_DOUBLES, BADMINTON_MIXED_DOUBLES,
                    SQUASH_MEN_SINGLES, SQUASH_WOMEN_SINGLES, FRISBEE, DODGEBALL, NETBALL, BASKETBALL,
                    SEPAK_TAKRAW, VOLLEYBALL, FIFA, ROCKET_LEAGUE
            };
        }
    }

    private static final String TAG = "NameManager";
    private static final String UNDEFINED_CONVERSION_FROM_DATABASE_TO_USER = "Undefined conversion from database to user. Please report this error to the developer!";

    public static String DatabaseToUser(String data) {
        switch (data) {
            case SportTypeSafeNames.SOCCER:
                data = SportCasualNames.SOCCER;
                break;
            case SportTypeSafeNames.BADMINTON_MEN_DOUBLES:
                data = SportCasualNames.BADMINTON_MEN_DOUBLES;
                break;
            case SportTypeSafeNames.BADMINTON_WOMEN_DOUBLES:
                data = SportCasualNames.BADMINTON_WOMEN_DOUBLES;
                break;
            case SportTypeSafeNames.BADMINTON_MIXED_DOUBLES:
                data = SportCasualNames.BADMINTON_MIXED_DOUBLES;
                break;
            case SportTypeSafeNames.SQUASH_MEN_SINGLES:
                data = SportCasualNames.SQUASH_MEN_SINGLES;
                break;
            case SportTypeSafeNames.SQUASH_WOMEN_SINGLES:
                data = SportCasualNames.SQUASH_WOMEN_SINGLES;
                break;
            case SportTypeSafeNames.FRISBEE:
                data = SportCasualNames.FRISBEE;
                break;
            case SportTypeSafeNames.DODGEBALL:
                data = SportCasualNames.DODGEBALL;
                break;
            case SportTypeSafeNames.NETBALL:
                data = SportCasualNames.NETBALL;
                break;
            case SportTypeSafeNames.BASKETBALL:
                data = SportCasualNames.BASKETBALL;
                break;
            case SportTypeSafeNames.SEPAK_TAKRAW:
                data = SportCasualNames.SEPAK_TAKRAW;
                break;
            case SportTypeSafeNames.VOLLEYBALL:
                data = SportCasualNames.VOLLEYBALL;
                break;
            case SportTypeSafeNames.FIFA:
                data = SportCasualNames.FIFA;
                break;
            case SportTypeSafeNames.ROCKET_LEAGUE:
                data = SportCasualNames.ROCKET_LEAGUE;
                break;
            default:
                data = UNDEFINED_CONVERSION_FROM_DATABASE_TO_USER;
                break;
        }

        return data;
    }

    public static String UserToDatabase(String data) {

        switch (data) {
            case SportCasualNames.SOCCER:
                data = SportTypeSafeNames.SOCCER;
                break;
            case SportCasualNames.BADMINTON_MEN_DOUBLES:
                data = SportTypeSafeNames.BADMINTON_MEN_DOUBLES;
                break;
            case SportCasualNames.BADMINTON_WOMEN_DOUBLES:
                data = SportTypeSafeNames.BADMINTON_WOMEN_DOUBLES;
                break;
            case SportCasualNames.BADMINTON_MIXED_DOUBLES:
                data = SportTypeSafeNames.BADMINTON_MIXED_DOUBLES;
                break;
            case SportCasualNames.SQUASH_MEN_SINGLES:
                data = SportTypeSafeNames.SQUASH_MEN_SINGLES;
                break;
            case SportCasualNames.SQUASH_WOMEN_SINGLES:
                data = SportTypeSafeNames.SQUASH_WOMEN_SINGLES;
                break;
            case SportCasualNames.FRISBEE:
                data = SportTypeSafeNames.FRISBEE;
                break;
            case SportCasualNames.DODGEBALL:
                data = SportTypeSafeNames.DODGEBALL;
                break;
            case SportCasualNames.NETBALL:
                data = SportTypeSafeNames.NETBALL;
                break;
            case SportCasualNames.BASKETBALL:
                data = SportTypeSafeNames.BASKETBALL;
                break;
            case SportCasualNames.SEPAK_TAKRAW:
                data = SportTypeSafeNames.SEPAK_TAKRAW;
                break;
            case SportCasualNames.VOLLEYBALL:
                data = SportTypeSafeNames.VOLLEYBALL;
                break;
            case SportCasualNames.FIFA:
                data = SportTypeSafeNames.FIFA;
                break;
            case SportCasualNames.ROCKET_LEAGUE:
                data = SportTypeSafeNames.ROCKET_LEAGUE;
                break;
            default:
                data = UNDEFINED_CONVERSION_FROM_DATABASE_TO_USER;
                break;
        }
        return data;
    }

    public static int getImageId(String data) {

        int dataInt = 0;

        switch (data) {
            case UniversityNames.CARLETON:
                dataInt = R.drawable.carleton_logo;
                break;
            case UniversityNames.TORONTO:
                dataInt = R.drawable.uoft_logo;
                break;
            case UniversityNames.WATERLOO:
                dataInt = R.drawable.waterloo_logo;
                break;
            case UniversityNames.MCMASTER:
                dataInt = R.drawable.mcmaster_logo;
                break;
            case UniversityNames.MCGILL:
                dataInt = R.drawable.mcgill_logo;
                break;
            case UniversityNames.GUELPH:
                dataInt = R.drawable.guelph_logo;
                break;
            case UniversityNames.UBC:
                dataInt = R.drawable.ubc_logo;
                break;
            case UniversityNames.OTTAWA:
                dataInt = R.drawable.uottawa_logo;
                break;
            case UniversityNames.QUEENS:
                dataInt = R.drawable.queens_logo;
                break;
            case UniversityNames.CALGARY:
                dataInt = R.drawable.calgary_logo;
                break;
            default:
                dataInt = 0;
                break;
        }
        return dataInt;
    }
}