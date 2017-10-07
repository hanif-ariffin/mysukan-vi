package masco.com.mysukan_vi.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017-09-28.
 */

public class WinnerMatch {

    public String first_place, second_place, third_place;

    public WinnerMatch() {

    }

    public WinnerMatch(String first_place, String second_place, String third_place) {
        this.first_place = first_place;
        this.second_place = second_place;
        this.third_place = third_place;
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();

        // WinnerMatch data
        map.put("first_place", first_place);
        map.put("second_place", second_place);
        map.put("third_place", third_place);

        return map;
    }
}
