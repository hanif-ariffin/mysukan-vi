package tremblay412.com.mysukan.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import tremblay412.com.mysukan.R;

/**
 * Created by Akarin on 9/9/2017.
 */

public class BaseSportDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView iListGames;
    TextView text_placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_match_detail);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}
