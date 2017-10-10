package masco.com.mysukan_vi.models;

import android.view.View;

/**
 * Created by Akarin on 10/8/2017.
 */

public class Sport {

    public View buttonId;
    public String sportId, sportName;

    public Sport(String sportId, String sportName, View button) {
        this.sportId = sportId;
        this.sportName = sportName;
        this.buttonId = button;
    }
}
