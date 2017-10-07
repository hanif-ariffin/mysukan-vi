package masco.com.mysukan_vi.fragments.adminarea;

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

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.fragments.BaseFragment;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.helper.SportManager;
import masco.com.mysukan_vi.models.SingleScoreMatch;
import masco.com.mysukan_vi.models.TripleScoreMatch;

public class EditScore extends BaseFragment {

    private Spinner teamOneScoreOne, teamTwoScoreOne, teamOneScoreTwo, teamTwoScoreTwo, teamOneScoreThree, teamTwoScoreThree;
    ArrayAdapter<CharSequence> scoreAdapter;
    private Button submit, delete;
    private TextView textHeader, teamOneName, teamTwoName;
    private String id, customNameOne, customNameTwo, uniTeamNameOne, uniTeamNameTwo;
    private static String sport_name;
    private long match_date;

    private Bundle args;
    private DatabaseReference databaseSport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView;

        //get argument from previous fragment
        args = getArguments();
        sport_name = args.getString("sport_name");
        id = args.getString("id");
        match_date = args.getLong("match_date");

        // check which templete sport need to be use
        if (!SportManager.isSingleScore(sport_name)) {
            rootView = inflater.inflate(R.layout.edit_score_set, container, false);

            teamOneScoreOne = (Spinner) rootView.findViewById(R.id.edit_score_set_team_1_score_1);
            teamOneScoreTwo = (Spinner) rootView.findViewById(R.id.edit_score_set_team_1_score_2);
            teamOneScoreThree = (Spinner) rootView.findViewById(R.id.edit_score_set_team_1_score_3);

            teamTwoScoreOne = (Spinner) rootView.findViewById(R.id.edit_score_set_team_2_score_1);
            teamTwoScoreTwo = (Spinner) rootView.findViewById(R.id.edit_score_set_team_2_score_2);
            teamTwoScoreThree = (Spinner) rootView.findViewById(R.id.edit_score_set_team_2_score_3);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(), R.array.number, android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            teamOneScoreOne.setAdapter(scoreAdapter);
            teamTwoScoreOne.setAdapter(scoreAdapter);
            teamOneScoreTwo.setAdapter(scoreAdapter);
            teamTwoScoreTwo.setAdapter(scoreAdapter);
            teamOneScoreThree.setAdapter(scoreAdapter);
            teamTwoScoreThree.setAdapter(scoreAdapter);

        } else {
            rootView = inflater.inflate(R.layout.edit_score_norm, container, false);

            teamOneScoreOne = (Spinner) rootView.findViewById(R.id.edit_score_set_team_1_score_1);
            teamTwoScoreOne = (Spinner) rootView.findViewById(R.id.edit_score_set_team_2_score_1);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(), R.array.number, android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            teamOneScoreOne.setAdapter(scoreAdapter);
            teamTwoScoreOne.setAdapter(scoreAdapter);
        }

        sport_name = NameManager.DatabaseToUser(sport_name);

        //set text for the header
        textHeader = (TextView) rootView.findViewById(R.id.edit_score_set_title);
        textHeader.setText(sport_name);


        customNameOne = args.getString("customNameTeamOne");
        customNameTwo = args.getString("customNameTeamTwo");

        uniTeamNameOne = args.getString("teamOne");
        uniTeamNameTwo = args.getString("teamTwo");

        // team Textview
        teamTwoName = (TextView) rootView.findViewById(R.id.edit_score_set_team_1_name);
        teamOneName = (TextView) rootView.findViewById(R.id.edit_score_set_team_2_name);

        if (customNameOne != null) {
            if (!customNameOne.isEmpty()) {
                teamOneName.setText(customNameOne);
            } else {
                teamOneName.setText(uniTeamNameOne);
            }
        } else {
            teamOneName.setText(uniTeamNameOne);
        }
        if (customNameTwo != null) {
            if (!customNameTwo.isEmpty()) {
                teamTwoName.setText(customNameTwo);
            } else {
                teamTwoName.setText(uniTeamNameTwo);
            }
        } else {
            teamTwoName.setText(uniTeamNameTwo);
        }


        // change sport_name back to database
        sport_name = NameManager.UserToDatabase(sport_name);

        //Database creation
        databaseSport = FirebaseDatabase.getInstance().getReference("games");

        //On click listener for button
        if (!SportManager.isSingleScore(sport_name)) {
            submit = (Button) rootView.findViewById(R.id.edit_score_set_button_submit);
        } else {
            submit = (Button) rootView.findViewById((R.id.activity_create_match_button_submit));
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseSport = FirebaseDatabase.getInstance().getReference("games").child(sport_name).child(id);
                if (SportManager.isSingleScore(sport_name)) {
                    SingleScoreMatch sport = new SingleScoreMatch(match_date, id, uniTeamNameOne, uniTeamNameTwo, customNameOne, customNameTwo, Long.parseLong(teamOneScoreOne.getSelectedItem().toString()), Long.parseLong(teamTwoScoreOne.getSelectedItem().toString()));
                    databaseSport.setValue(sport);
                    Toast.makeText(getContext(), "Score Updated", Toast.LENGTH_SHORT).show();
                } else {
                    TripleScoreMatch sport = new TripleScoreMatch(match_date, id, uniTeamNameOne, uniTeamNameTwo, customNameOne, customNameTwo, Long.parseLong(teamOneScoreOne.getSelectedItem().toString()), Long.parseLong(teamTwoScoreOne.getSelectedItem().toString())
                            , Long.parseLong(teamOneScoreTwo.getSelectedItem().toString()), Long.parseLong(teamTwoScoreTwo.getSelectedItem().toString())
                            , Long.parseLong(teamOneScoreThree.getSelectedItem().toString()), Long.parseLong(teamTwoScoreThree.getSelectedItem().toString()));
                    databaseSport.setValue(sport);
                    Toast.makeText(getContext(), "Score Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete = (Button) rootView.findViewById(R.id.edit_score_norm_button_delete);
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