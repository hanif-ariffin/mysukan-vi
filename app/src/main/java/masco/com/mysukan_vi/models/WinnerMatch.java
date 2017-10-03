package masco.com.mysukan_vi.models;

/**
 * Created by User on 2017-09-28.
 */

public class WinnerMatch {

    public String first_place, second_place, third_place;

    public WinnerMatch() {

    }

    public WinnerMatch(String firstPlace, String secondPlace, String thirdPlace) {
        this.first_place = firstPlace;
        this.second_place = secondPlace;
        this.third_place = thirdPlace;
        // Default constructor for Firebase
    }

    public String getFirst_place() {
        return first_place;
    }

    public void setFirst_place(String first_place) {
        this.first_place = first_place;
    }

    public String getSecond_place() {
        return second_place;
    }

    public void setSecond_place(String second_place) {
        this.second_place = second_place;
    }

    public String getThird_place() {
        return third_place;
    }

    public void setThird_place(String third_place) {
        this.third_place = third_place;
    }

}