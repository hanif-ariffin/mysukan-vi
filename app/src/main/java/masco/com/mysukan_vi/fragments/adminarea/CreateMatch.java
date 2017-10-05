package masco.com.mysukan_vi.fragments.adminarea;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.fragments.BaseFragment;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.models.SingleScoreMatch;
import masco.com.mysukan_vi.models.TripleScoreMatch;

public class CreateMatch extends BaseFragment {

    private Spinner teamOne, teamTwo, scoreOne, scoreTwo, scoreThree, scoreFour, scoreFive, scoreSix;
    ArrayAdapter<CharSequence> teamAdapter, scoreAdapter;
    private Button submitButton;
    private TextView text, datePicker1;
    private EditText teamOneCustomName, teamTwoCustomName;
    public String sport_name, hour, minute;
    private static String lCustomNameOne, lCustomNameTwo;
    private Bundle args;
    private DatabaseReference databaseSport;
    private List<String> checker;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private long unixTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView;

        //get argument from previous fragment
        args = getArguments();
        sport_name = args.getString("sport_name");

        rootView = inflater.inflate(R.layout.submit_score_norm, container, false);

        teamOne = (Spinner) rootView.findViewById(R.id.teamOne);
        teamTwo = (Spinner) rootView.findViewById(R.id.teamTwo);

        teamOneCustomName = (EditText) rootView.findViewById(R.id.custom_team_name_one);
        teamTwoCustomName = (EditText) rootView.findViewById(R.id.custom_team_name_two);

        teamAdapter = ArrayAdapter.createFromResource(getContext(), R.array.team_list, android.R.layout.simple_spinner_item);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamOne.setAdapter(teamAdapter);
        teamTwo.setAdapter(teamAdapter);


        datePicker1 = (TextView) rootView.findViewById(R.id.datePicker);
        datePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.DialogTheme, mTimeSetListener, hour, minute, true);
                timePickerDialog.show();
            }
        });

        //array for checker
        checker = Arrays.asList("badminton_men_doubles", "badminton_women_doubles", "badminton_mixed_doubles", "squash_men_singles", "squash_women_singles", "volleyball");

        unixTime = 0;
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                datePicker1.setText(singleDigit(i) + ":" + singleDigit(i1));

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
        text = (TextView) rootView.findViewById(R.id.submit_score_norm_header);
        text.setText(" " + sport_name + " ");

        //Change string to sync with database string

        sport_name = NameManager.UserToDatabase(sport_name);

        //Database
        databaseSport = FirebaseDatabase.getInstance().getReference("games");
        submitButton = (Button) rootView.findViewById(R.id.BTN_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseSport.push().getKey();

                if (teamOneCustomName.getText().length() > 9 || teamTwoCustomName.getText().length() > 9) {
                    Toast.makeText(getContext(), "Custom team name must be less than 9 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    if (unixTime == 0) {
                        Toast.makeText(getContext(), "Please set the schedule!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!checker.contains(sport_name)) {

                            SingleScoreMatch sport = new SingleScoreMatch(unixTime, id, teamOne.getSelectedItem().toString(), teamTwo.getSelectedItem().toString(), teamOneCustomName.getText().toString(), teamTwoCustomName.getText().toString(), 0L, 0L);
                            databaseSport.child(sport_name).child(id).setValue(sport);
                            Toast.makeText(getContext(), "Sport added", Toast.LENGTH_SHORT).show();

                        } else {

                            TripleScoreMatch sport = new TripleScoreMatch(unixTime, id, teamOne.getSelectedItem().toString(), teamTwo.getSelectedItem().toString(), teamOneCustomName.getText().toString(), teamTwoCustomName.getText().toString(), 0L, 0L, 0L, 0L, 0L, 0L);
                            databaseSport.child(sport_name).child(id).setValue(sport);
                            Toast.makeText(getContext(), "Sport added", Toast.LENGTH_SHORT).show();

                        }
                    }
                }


            }
        });

        return rootView;
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