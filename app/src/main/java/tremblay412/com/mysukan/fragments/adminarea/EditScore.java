package tremblay412.com.mysukan.fragments.adminarea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.fragments.BaseFragment;
import tremblay412.com.mysukan.helper.NameManager;
import tremblay412.com.mysukan.models.SingleScoreMatch;
import tremblay412.com.mysukan.models.TripleScoreMatch;

public class EditScore extends BaseFragment {

    private Spinner scoreOne, scoreTwo, scoreThree, scoreFour, scoreFive, scoreSix;
    ArrayAdapter<CharSequence> scoreAdapter;
    private Button submit, delete;
    private TextView textHeader, teamOneName, teamTwoName;
    private List<String> checker;
    private String id, customNameOne, customNameTwo, uniTeamNameOne, uniTeamNameTwo ;
    private static String sport_name;
    private long match_date;

    private Bundle args;
    private DatabaseReference databaseSport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView;

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

        //set text for the header
        textHeader = (TextView) rootView.findViewById(R.id.submit_score_norm_header);
        textHeader.setText(sport_name);


        customNameOne = args.getString("customNameTeamOne");
        customNameTwo = args.getString("customNameTeamTwo");

        uniTeamNameOne = args.getString("teamOne");
        uniTeamNameTwo = args.getString("teamTwo");

        // team Textview
        teamTwoName = (TextView) rootView.findViewById(R.id.teamTwo);
        teamOneName = (TextView) rootView.findViewById(R.id.teamOne);

        if( customNameOne != null){
            if( !customNameOne.isEmpty()){
                teamOneName.setText(customNameOne);
            }else{
                teamOneName.setText(uniTeamNameOne);
            }
        }else {
            teamOneName.setText(uniTeamNameOne);
        }
        if( customNameTwo != null){
            if( !customNameTwo.isEmpty()){
                teamTwoName.setText(customNameTwo);
            }else{
                teamTwoName.setText(uniTeamNameTwo);
            }
        }else {
            teamTwoName.setText(uniTeamNameTwo);
        }


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
                    SingleScoreMatch sport = new SingleScoreMatch(match_date, id, uniTeamNameOne, uniTeamNameTwo, customNameOne,customNameTwo, Long.parseLong(scoreOne.getSelectedItem().toString()), Long.parseLong(scoreTwo.getSelectedItem().toString()));
                    databaseSport.setValue(sport);
                    Toast.makeText(getContext(), "Score Updated", Toast.LENGTH_LONG).show();
                } else {
                    TripleScoreMatch sport = new TripleScoreMatch(match_date, id, uniTeamNameOne, uniTeamNameTwo, customNameOne,customNameTwo, Long.parseLong(scoreOne.getSelectedItem().toString()), Long.parseLong(scoreTwo.getSelectedItem().toString())
                            , Long.parseLong(scoreThree.getSelectedItem().toString()), Long.parseLong(scoreFour.getSelectedItem().toString())
                            , Long.parseLong(scoreFive.getSelectedItem().toString()), Long.parseLong(scoreSix.getSelectedItem().toString()));
                    databaseSport.setValue(sport);
                    Toast.makeText(getContext(), "Score Updated", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete = (Button) rootView.findViewById(R.id.BTN_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("games").child(sport_name).child(id).removeValue();
                getActivity().getSupportFragmentManager().popBackStack();
                Toast.makeText(getContext(), sport_name + " : " + uniTeamNameOne + " vs " + uniTeamNameTwo + " deleted", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}