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

public class SubmitScore extends BaseFragment {

    private Spinner teamOne,teamTwo,scoreOne,scoreTwo, scoreThree, scoreFour, scoreFive, scoreSix;
    ArrayAdapter<CharSequence> teamAdapter, scoreAdapter;
    private Button submit;
    private TextView text;
    public String name;

    private Bundle args;
    private DatabaseReference databaseSport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView;
        args = getArguments();
        name = args.getString("sport_type");
        if(name == "Soccer" || name == "Frisbee" ){
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

        //get data from previous fragment
        text = (TextView)rootView.findViewById(R.id.textView10);
        text.setText(name);

        //Change string to sync with database string

        switch (name){
            case "Soccer":
                name = "soccer";
                break;
            case "Badminton Men Doubles":
                name = "badminton_men_doubles";
                break;
            case "Badminton Women Doubles":
                name = "badminton_women_doubles";
                break;
            case "Badminton Mixed Doubles":
                name = "badminton_mixed_doubles";
                break;
            case "Squash Men Singles":
                name = "squash_men_singles";
                break;
            case "Squash Women Singles":
                name = "squash_women_singles";
                break;
            case "Frisbee":
                name = "frisbee";
                break;
            case "Dodgeball":
                name = "dodgeball";
                break;
            case "Netball":
                name = "netball";
                break;
            case "Basketball":
                name = "basketball";
                break;
            case "Sepak Takraw":
                name = "sepak_takraw";
                break;
            case "Volleyball":
                name = "volleyball";
                break;
            case "Fifa":
                name = "fifa";
                break;
            case "Rocket League":
                name = "rocket_league";
                break;
        }


        //Database
        databaseSport = FirebaseDatabase.getInstance().getReference("games");

        submit = (Button)rootView.findViewById(R.id.BTN_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseSport.push().getKey();

                if(name == "soccer" || name == "frisbee" ){
                    SportNorm sport = new SportNorm(teamOne.getSelectedItem().toString(),teamTwo.getSelectedItem().toString(),Integer.parseInt(scoreOne.getSelectedItem().toString()),Integer.parseInt(scoreTwo.getSelectedItem().toString()));
                    databaseSport.child(name).child(id).setValue(sport);
                    Toast.makeText(getContext(),"Sport added", Toast.LENGTH_LONG).show();
                }
                else
                {
                    SportSet sport = new SportSet(teamOne.getSelectedItem().toString(),teamTwo.getSelectedItem().toString(),Integer.parseInt(scoreOne.getSelectedItem().toString()),Integer.parseInt(scoreTwo.getSelectedItem().toString())
                                                    ,Integer.parseInt(scoreThree.getSelectedItem().toString()),Integer.parseInt(scoreFour.getSelectedItem().toString())
                                                    ,Integer.parseInt(scoreFive.getSelectedItem().toString()),Integer.parseInt(scoreSix.getSelectedItem().toString()));
                    databaseSport.child(name).child(id).setValue(sport);
                    Toast.makeText(getContext(),"Sport added", Toast.LENGTH_LONG).show();
                }


            }
        });

        return rootView;
    }
}
