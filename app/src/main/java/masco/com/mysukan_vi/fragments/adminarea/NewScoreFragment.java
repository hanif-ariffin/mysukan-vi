package masco.com.mysukan_vi.fragments.adminarea;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.other.BaseActivity;
import masco.com.mysukan_vi.helper.ListAdapter;
import masco.com.mysukan_vi.helper.SportManager;

public class NewScoreFragment extends BaseActivity {


    // UI references.
    private ListView iListGames;
    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage_sports_listview);


        //Listview for sport
        iListGames = (ListView) findViewById(R.id.activity_adminpage_select_sport_sports_listview);
        ListAdapter lArrayAdapter = new ListAdapter(this, R.layout.listview_component, SportManager.getGames());
        iListGames.setAdapter(lArrayAdapter);

        //Listener for listview

        iListGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Open up EditScoreActivity for " + SportManager.getGames().get(i), Toast.LENGTH_SHORT);
            }
        });
    }
}
