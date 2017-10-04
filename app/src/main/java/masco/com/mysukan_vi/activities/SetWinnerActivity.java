package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.fragments.BaseFragment;
import masco.com.mysukan_vi.models.WinnerMatch;


/**
 * Created by User on 2017-10-04.
 */

public class SetWinnerActivity extends BaseActivity{

    private Spinner firstPlace, secondPlace, thirdPlace;
    ArrayAdapter<CharSequence> teamAdapter;
    DatabaseReference databaseWinner;
    private String sport_name;
    private Bundle args;
    private Button submitButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_winner);


        getSupportActionBar().setTitle("Announce Winner");
        firstPlace = (Spinner) findViewById(R.id.first_place_spinner);
        secondPlace = (Spinner) findViewById(R.id.second_place_spinner);
        thirdPlace = (Spinner) findViewById(R.id.third_place_spinner);

        teamAdapter = ArrayAdapter.createFromResource(this, R.array.team_list, android.R.layout.simple_spinner_item);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        firstPlace.setAdapter(teamAdapter);
        secondPlace.setAdapter(teamAdapter);
        thirdPlace.setAdapter(teamAdapter);

        databaseWinner = FirebaseDatabase.getInstance().getReference("ranking");

        //already database_name
        args = getIntent().getExtras();;
        sport_name = args.getString("sport_name");

        submitButton = (Button) findViewById(R.id.submit_set_winner);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WinnerMatch winner = new WinnerMatch(firstPlace.getSelectedItem().toString(),secondPlace.getSelectedItem().toString(),thirdPlace.getSelectedItem().toString());
                databaseWinner.child(sport_name).setValue(winner);
                Toast.makeText(SetWinnerActivity.this, "Winner has been announced!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.winner).setVisible(false);
            menu.findItem(R.id.menu_item_developers_info).setVisible(false);
            menu.findItem(R.id.menu_item_sponsors).setVisible(false);
            menu.findItem(R.id.menu_item_overall_score).setVisible(false);
        }
        return true;
    }

}

