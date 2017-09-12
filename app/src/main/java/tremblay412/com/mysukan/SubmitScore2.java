package tremblay412.com.mysukan;

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

public class SubmitScore2 extends BaseFragment {

    private Spinner teamOne,teamTwo,scoreOne,scoreTwo, scoreThree, scoreFour, scoreFive, scoreSix;
    ArrayAdapter<CharSequence> teamAdapter, scoreAdapter;
    private Button submit;
    private TextView text;
    private List<String> checker;
    public String sport_name;

    private Bundle args;
    private DatabaseReference databaseSport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView;
        args = getArguments();
        sport_name = args.getString("sport_type");
        if(sport_name == "Soccer" || sport_name == "Frisbee" ){
            rootView = inflater.inflate(R.layout.submit_score_norm, container, false);

            teamOne = (Spinner)rootView.findViewById(R.id.teamOne);
            teamTwo = (Spinner)rootView.findViewById(R.id.teamTwo);
            scoreOne = (Spinner)rootView.findViewById(R.id.scoreOne);
            scoreTwo = (Spinner)rootView.findViewById(R.id.scoreTwo);

            teamAdapter = ArrayAdapter.createFromResource(getContext(),R.array.team_list,android.R.layout.simple_spinner_item);
            teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            teamOne.setAdapter(teamAdapter);
            teamTwo.setAdapter(teamAdapter);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(),R.array.number,android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scoreOne.setAdapter(scoreAdapter);
            scoreTwo.setAdapter(scoreAdapter);
        }
        else
        {
            rootView = inflater.inflate(R.layout.submit_score_set, container, false);

            teamOne = (Spinner)rootView.findViewById(R.id.teamOne);
            teamTwo = (Spinner)rootView.findViewById(R.id.teamTwo);
            scoreOne = (Spinner)rootView.findViewById(R.id.scoreOne);
            scoreTwo = (Spinner)rootView.findViewById(R.id.scoreTwo);
            scoreThree = (Spinner)rootView.findViewById(R.id.scoreThree);
            scoreFour = (Spinner)rootView.findViewById(R.id.scoreFour);
            scoreFive = (Spinner)rootView.findViewById(R.id.scoreFive);
            scoreSix = (Spinner)rootView.findViewById(R.id.scoreSix);

            teamAdapter = ArrayAdapter.createFromResource(getContext(),R.array.team_list,android.R.layout.simple_spinner_item);
            teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            teamOne.setAdapter(teamAdapter);
            teamTwo.setAdapter(teamAdapter);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(),R.array.number,android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scoreOne.setAdapter(scoreAdapter);
            scoreTwo.setAdapter(scoreAdapter);
            scoreThree.setAdapter(scoreAdapter);
            scoreFour.setAdapter(scoreAdapter);
            scoreFive.setAdapter(scoreAdapter);
            scoreSix.setAdapter(scoreAdapter);
        }

        checker = Arrays.asList("badminton_men_doubles","badminton_women_doubles","badminton_mixed_doubles","squash_men_singles","squash_women_singles");
        //get data from previous fragment
        text = (TextView)rootView.findViewById(R.id.textView10);
        text.setText(sport_name);

        //Change string to sync with database string
        NameSwitcher switcher = new NameSwitcher();
        sport_name = switcher.UserToDatabase(sport_name);

        //Database
        databaseSport = FirebaseDatabase.getInstance().getReference("games");

        submit = (Button)rootView.findViewById(R.id.BTN_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseSport.push().getKey();

                if(checker.contains(sport_name)) {
                    SportSet sport = new SportSet(teamOne.getSelectedItem().toString(),teamTwo.getSelectedItem().toString(),Integer.parseInt(scoreOne.getSelectedItem().toString()),Integer.parseInt(scoreTwo.getSelectedItem().toString())
                            ,Integer.parseInt(scoreThree.getSelectedItem().toString()),Integer.parseInt(scoreFour.getSelectedItem().toString())
                            ,Integer.parseInt(scoreFive.getSelectedItem().toString()),Integer.parseInt(scoreSix.getSelectedItem().toString()));
                    databaseSport.child(sport_name).child(id).setValue(sport);
                    Toast.makeText(getContext(),"Sport edited", Toast.LENGTH_LONG).show();
                }else{
                    SportNorm sport = new SportNorm(teamOne.getSelectedItem().toString(),teamTwo.getSelectedItem().toString(),Integer.parseInt(scoreOne.getSelectedItem().toString()),Integer.parseInt(scoreTwo.getSelectedItem().toString()));
                    databaseSport.child(sport_name).child(id).setValue(sport);
                    Toast.makeText(getContext(),"Sport edited", Toast.LENGTH_LONG).show();
                }

            }
        });

        return rootView;
    }
}
