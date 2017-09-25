package tremblay412.com.mysukan.fragments.adminarea;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.fragments.BaseFragment;
import tremblay412.com.mysukan.helper.NameManager;

public class EditScore extends BaseFragment {

    private Spinner teamOne, teamTwo, scoreOne, scoreTwo, scoreThree, scoreFour, scoreFive, scoreSix;
    ArrayAdapter<CharSequence> teamAdapter, scoreAdapter;
    private Button submit;
    private TextView textHeader, teamOneName , teamTwoName;
    private List<String> checker;
    public String sport_name, id;
    private long match_date;

    private Bundle args;
    private DatabaseReference databaseSport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView;

        //array for checker
        checker = Arrays.asList("badminton_men_doubles", "badminton_women_doubles", "badminton_mixed_doubles", "squash_men_singles", "squash_women_singles");

        //get argument from previous fragment
        args = getArguments();
        sport_name = args.getString("sport_name");
        id = args.getString("id");
        match_date = args.getLong("match_date");

        // check which templete sport need to be use
        if (checker.contains(sport_name)) {
            rootView = inflater.inflate(R.layout.edit_score_set, container, false);

            scoreOne = (Spinner) rootView.findViewById(R.id.scoreOne);
            scoreTwo = (Spinner) rootView.findViewById(R.id.scoreTwo);
            scoreThree = (Spinner) rootView.findViewById(R.id.scoreThree);
            scoreFour = (Spinner) rootView.findViewById(R.id.scoreFour);
            scoreFive = (Spinner) rootView.findViewById(R.id.scoreFive);
            scoreSix = (Spinner) rootView.findViewById(R.id.scoreSix);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(), R.array.number, android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scoreOne.setAdapter(scoreAdapter);
            scoreTwo.setAdapter(scoreAdapter);
            scoreThree.setAdapter(scoreAdapter);
            scoreFour.setAdapter(scoreAdapter);
            scoreFive.setAdapter(scoreAdapter);
            scoreSix.setAdapter(scoreAdapter);

        } else {
            rootView = inflater.inflate(R.layout.edit_score_norm, container, false);

            scoreOne = (Spinner) rootView.findViewById(R.id.scoreOne);
            scoreTwo = (Spinner) rootView.findViewById(R.id.scoreTwo);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(), R.array.number, android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scoreOne.setAdapter(scoreAdapter);
            scoreTwo.setAdapter(scoreAdapter);
        }

        sport_name = NameManager.DatabaseToUser(sport_name);

        // team Textview
        teamTwoName = (TextView)  rootView.findViewById(R.id.teamTwo);
        teamOneName = (TextView)  rootView.findViewById(R.id.teamOne);
        teamOneName.setText(args.getString("teamOne"));
        teamTwoName.setText(args.getString("teamTwo"));

        //set text for the header
        textHeader = (TextView) rootView.findViewById(R.id.textView10);
        textHeader.setText(sport_name);

        // change sport_name back to database
        sport_name = NameManager.UserToDatabase(sport_name);

        //Database creation
        databaseSport = FirebaseDatabase.getInstance().getReference("games");

        //On click listener for button
        submit = (Button) rootView.findViewById(R.id.BTN_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseSport = FirebaseDatabase.getInstance().getReference("games").child(sport_name).child(id);
                if (!checker.contains(sport_name)) {
                    SportNorm sport = new SportNorm(match_date,id, teamOneName.getText().toString(), teamTwoName.getText().toString(), Integer.parseInt(scoreOne.getSelectedItem().toString()), Integer.parseInt(scoreTwo.getSelectedItem().toString()));
                    databaseSport.setValue(sport);
                } else {
                    SportSet sport = new SportSet(match_date,id, teamOneName.getText().toString(), teamTwoName.getText().toString(), Integer.parseInt(scoreOne.getSelectedItem().toString()), Integer.parseInt(scoreTwo.getSelectedItem().toString())
                            , Integer.parseInt(scoreThree.getSelectedItem().toString()), Integer.parseInt(scoreFour.getSelectedItem().toString())
                            , Integer.parseInt(scoreFive.getSelectedItem().toString()), Integer.parseInt(scoreSix.getSelectedItem().toString()));
                    databaseSport.setValue(sport);
                }
            }
        });

        return rootView;
    }
}