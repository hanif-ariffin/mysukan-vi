package masco.com.mysukan_vi.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.main.SportDetailActivity;
import masco.com.mysukan_vi.activities.other.BaseActivity;
import masco.com.mysukan_vi.fragments.BaseFragment;
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

        sports = getSportListActivityButtons();
        for (int a = 0; a < NameManager.SportTypeSafeNames.array.length; a++) {
            final int finalA = a;
            sports.get(a).buttonId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SportListActivity.this, "User clicked at:" + NameManager.SportTypeSafeNames.array[finalA], Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SportListActivity.this, CreateMatchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(NameManager.SPORT_NAME, NameManager.SportTypeSafeNames.array[finalA]); //Your id
                    intent.putExtras(bundle); //Put your id to your next Intent
                    startActivity(intent);

                }
            });
        }
    }
}
