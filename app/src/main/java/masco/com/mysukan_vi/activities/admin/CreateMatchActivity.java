package masco.com.mysukan_vi.activities.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.other.BaseActivity;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.helper.SportManager;
import masco.com.mysukan_vi.models.TripleScoreMatch;

public class CreateMatchActivity extends BaseActivity {

    private static final String TAG = "CreateMatchActivity";

    ArrayAdapter<CharSequence> teamAdapter;
    private Button submitButton;
    private EditText teamOneCustomName, teamTwoCustomName;
    public String sportName;
    public static final int MAX_CUSTOM_NAME = 15;
    private Bundle bundle;
    private Spinner teamOneName, teamTwoName;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);

        getSupportActionBar().setTitle(R.string.create_match_activity_title);
        //get argument from previous fragment
        bundle = getIntent().getExtras();
        sportName = bundle.getString(NameManager.SPORT_NAME);

        teamOneName = (Spinner) findViewById(R.id.activity_adminpage_newmatch_team_1_name);
        teamTwoName = (Spinner) findViewById(R.id.activity_adminpage_newmatch_team_2_name);

        teamOneCustomName = (EditText) findViewById(R.id.activity_adminpage_newmatch_team_1_custom_name);
        teamTwoCustomName = (EditText) findViewById(R.id.activity_adminpage_newmatch_team_2_custom_name);

        teamAdapter = ArrayAdapter.createFromResource(this, R.array.team_list, android.R.layout.simple_spinner_item);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamOneName.setAdapter(teamAdapter);
        teamTwoName.setAdapter(teamAdapter);

        //Database
        databaseReference = FirebaseDatabase.getInstance().getReference("games");
        submitButton = (Button) findViewById(R.id.activity_adminpage_newmatch_button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseReference.push().getKey();

                if (teamOneCustomName.getText().length() > MAX_CUSTOM_NAME || teamTwoCustomName.getText().length() > MAX_CUSTOM_NAME) {
                    Toast.makeText(CreateMatchActivity.this, "Custom team name must be less than 9 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    TripleScoreMatch sport;
                    if (SportManager.isSingleScore(sportName)) {
                        sport = new TripleScoreMatch(
                                System.currentTimeMillis() / 1000, id,
                                teamOneName.getSelectedItem().toString(), teamTwoName.getSelectedItem().toString(),
                                teamOneCustomName.getText().toString(), teamTwoCustomName.getText().toString(),
                                0L, 0L,
                                -1L, -1L,
                                -1L, -1L
                        );
                    } else {
                        sport = new TripleScoreMatch(
                                System.currentTimeMillis() / 1000, id,
                                teamOneName.getSelectedItem().toString(), teamTwoName.getSelectedItem().toString(),
                                teamOneCustomName.getText().toString(), teamTwoCustomName.getText().toString(),
                                0L, 0L,
                                0L, 0L,
                                0L, 0L
                        );
                    }
                    Log.d(TAG, "pushing to db:" + sportName + ":" + id + ":" + sport);
                    databaseReference.child(sportName).child(id).setValue(sport);
                    Toast.makeText(CreateMatchActivity.this, "Sport added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}