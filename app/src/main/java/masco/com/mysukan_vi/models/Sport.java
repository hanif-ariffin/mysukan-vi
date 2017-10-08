package masco.com.mysukan_vi.models;

import android.view.View;

/**
 * Created by Akarin on 10/8/2017.
 */

public class Sport {

    public View buttonId;
    public String value;

    public Sport(String value, View buttonId) {
        this.value = value;
        this.buttonId = buttonId;
    }
}
