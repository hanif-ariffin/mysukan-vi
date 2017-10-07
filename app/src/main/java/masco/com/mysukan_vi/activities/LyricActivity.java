package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.view.Menu;

import masco.com.mysukan_vi.R;

/**
 * Created by User on 2017-10-04.
 */

public class LyricActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_lyric);

        getSupportActionBar().setTitle("Theme Song Lyric");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
    }


}
