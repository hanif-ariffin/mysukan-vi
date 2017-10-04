package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.view.Menu;

import masco.com.mysukan_vi.R;

/**
 * Info of the developers. YOU!
 * Created by Akarin on 9/30/2017.
 */

public class InfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setTitle("Info");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
