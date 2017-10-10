package masco.com.mysukan_vi.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.other.BaseActivity;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.models.Sport;

/**
 * SportListFragment is the fragment for display the Scoreboard.
 * The design on the UI is NOT dynamic.
 * If you need to add something you have to manually add them here.
 * Considering that there are only a predefined amount of sports, this was argued as the better design than having a ListView.
 * Created by r3xas on 9/25/2017.
 */

public class SportListActivity extends BaseActivity {

    private static final String TAG = "SportListFragment";
    public ArrayList<Sport> sports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sportlist);
        getSupportActionBar().setTitle(getString(R.string.static_final_string_select_sport));
        sports = getSportListActivityButtons();
        for (int a = 0; a < NameManager.SportTypeSafeNames.array.length; a++) {
            final int finalA = a;
            sports.get(a).buttonId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SportListActivity.this, CreateMatchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(NameManager.SPORT_NAME, sports.get(finalA).sportId); //Your id
                    intent.putExtras(bundle); //Put your id to your next Intent
                    startActivity(intent);

                }
            });
        }
    }
}
