package masco.com.mysukan_vi.activities;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.helper.SportManager;
import masco.com.mysukan_vi.models.SingleScoreMatch;
import masco.com.mysukan_vi.models.TripleScoreMatch;

public class CreateMatchActivity extends BaseActivity {

    ArrayAdapter<CharSequence> teamAdapter;
    private Button submitButton;
    private TextView text, datePicker;
    private EditText teamOneCustomName, teamTwoCustomName;
    public String sport_name, hour, minute;
    private Bundle args;
    private DatabaseReference databaseSport;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private long unixTime;
    private Spinner teamOneName, teamTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);

        //get argument from previous fragment
        args = getIntent().getExtras();
        sport_name = args.getString("sport_name");

        teamOneName = (Spinner) findViewById(R.id.activity_create_match_team_1_name);
        teamTwoName = (Spinner) findViewById(R.id.activity_create_match_team_2_name);

        teamOneCustomName = (EditText) findViewById(R.id.activity_create_match_team_1_custom_name);
        teamTwoCustomName = (EditText) findViewById(R.id.activity_create_match_team_2_custom_name);

        teamAdapter = ArrayAdapter.createFromResource(this, R.array.team_list, android.R.layout.simple_spinner_item);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamOneName.setAdapter(teamAdapter);
        teamTwoName.setAdapter(teamAdapter);


        datePicker = (TextView) findViewById(R.id.activity_create_match_date_picker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), R.style.DialogTheme, mTimeSetListener, hour, minute, true);
                timePickerDialog.show();
            }
        });

        unixTime = 0;
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                datePicker.setText(singleDigit(i) + ":" + singleDigit(i1));

                hour = String.valueOf(i);
                minute = String.valueOf(i1);

                String date = "7";
                String month = "October";
                String year = "2017";
                String seconds = "00";

                String dateString = date + " " + month + " " + year + " " + hour + ":" + minute + ":" + seconds + " " + "EDT";
                DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z");

                try {
                    Date dateData = dateFormat.parse(dateString);
                    unixTime = dateData.getTime() / 1000;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };


        //set textHeader
        text = (TextView) findViewById(R.id.edit_score_set_title);
        text.setText(" " + sport_name + " ");

        //Change string to sync with database string

        sport_name = NameManager.UserToDatabase(sport_name);

        //Database
        databaseSport = FirebaseDatabase.getInstance().getReference("games");
        submitButton = (Button) findViewById(R.id.edit_score_set_button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseSport.push().getKey();

                if (teamOneCustomName.getText().length() > 9 || teamTwoCustomName.getText().length() > 9) {
                    Toast.makeText(getApplicationContext(), "Custom team name must be less than 9 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    if (unixTime == 0) {
                        Toast.makeText(getApplicationContext(), "Please set the schedule!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (SportManager.isSingleScore(sport_name)) {

                            SingleScoreMatch sport = new SingleScoreMatch(unixTime, id, teamOneName.getSelectedItem().toString(), teamTwoName.getSelectedItem().toString(), teamOneCustomName.getText().toString(), teamTwoCustomName.getText().toString(), 0L, 0L);
                            databaseSport.child(sport_name).child(id).setValue(sport);
                            Toast.makeText(getApplicationContext(), "Sport added", Toast.LENGTH_SHORT).show();

                        } else {

                            TripleScoreMatch sport = new TripleScoreMatch(unixTime, id, teamOneName.getSelectedItem().toString(), teamTwoName.getSelectedItem().toString(), teamOneCustomName.getText().toString(), teamTwoCustomName.getText().toString(), 0L, 0L, 0L, 0L, 0L, 0L);
                            databaseSport.child(sport_name).child(id).setValue(sport);
                            Toast.makeText(getApplicationContext(), "Sport added", Toast.LENGTH_SHORT).show();

                        }
                    }
                }


            }
        });
    }

    //this method is to add 0 for time example 05:07
    private String singleDigit(int aNumber) {

        if (aNumber >= 0 && aNumber < 10)
            return "0" + String.valueOf(aNumber);
        else {
            return String.valueOf(aNumber);
        }
    }
}