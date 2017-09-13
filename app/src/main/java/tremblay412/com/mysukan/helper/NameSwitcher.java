package tremblay412.com.mysukan.helper;

/**
 * Created by Haziq on 2017-09-11.
 */

public class NameSwitcher {
    String data;
    public NameSwitcher(){}

    public String DatabaseToUser(String data){
        this.data = data;

        switch (data){
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
        }
        
        return data;
    }
    
    public String UserToDatabase(String data){
        this.data = data;

        switch (data){
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
        }
        return data;
    }
}
