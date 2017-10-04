package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.view.Menu;

import masco.com.mysukan_vi.R;

/**
 * Activity showing the sponsors of this event.
 * Created by Akarin on 9/29/2017.
 */

public class SponsorsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        getSupportActionBar().setTitle("MySukan VI Sponsors");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
