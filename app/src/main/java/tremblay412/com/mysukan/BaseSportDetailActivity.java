package tremblay412.com.mysukan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        iListGames = (ListView) findViewById(R.id.sport_match_list);
        text_placeholder = (TextView) findViewById(R.id.text_placeholder);

        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FirebaseConnector.getInstance().getMatches());
        iListGames.setAdapter(lArrayAdapter);
        iListGames.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        text_placeholder.setText(FirebaseConnector.getInstance().getMatches().get(position));
    }
}
