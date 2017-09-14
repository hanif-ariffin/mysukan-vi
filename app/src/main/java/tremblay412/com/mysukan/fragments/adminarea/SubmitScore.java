package tremblay412.com.mysukan.fragments.adminarea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tremblay412.com.mysukan.helper.NameManager;
import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.fragments.BaseFragment;

public class SubmitScore extends BaseFragment {

    private Spinner teamOne,teamTwo,scoreOne,scoreTwo, scoreThree, scoreFour, scoreFive, scoreSix;
    ArrayAdapter<CharSequence> teamAdapter, scoreAdapter;
    private Button submitButton;
    private TextView text;
    public String sport_name;
    private Bundle args;
    private DatabaseReference databaseSport;
    private ImageView team_1_logo, team_2_logo;
    private NameManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView;

        manager = new NameManager();

        //get argument from previous fragment
        args = getArguments();
        sport_name = args.getString("sport_name");

        if(sport_name == "Soccer" || sport_name == "Frisbee" ){
            rootView = inflater.inflate(R.layout.submit_score_norm, container, false);

            //ImageView construction
            team_1_logo = (ImageView) rootView.findViewById(R.id.imageView2);
            team_2_logo = (ImageView) rootView.findViewById(R.id.imageView3);


            teamOne = (Spinner)rootView.findViewById(R.id.teamOne);
            teamTwo = (Spinner)rootView.findViewById(R.id.teamTwo);
            scoreOne = (Spinner)rootView.findViewById(R.id.scoreOne);
            scoreTwo = (Spinner)rootView.findViewById(R.id.scoreTwo);

            teamAdapter = ArrayAdapter.createFromResource(getContext(),R.array.team_list,android.R.layout.simple_spinner_item);
            teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            teamOne.setAdapter(teamAdapter);
            teamTwo.setAdapter(teamAdapter);

            teamOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    System.out.println(manager.getImageId(sport_name));
                    team_1_logo.setImageResource(manager.getImageId(sport_name));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    System.out.println("ss");
                }
            });

            scoreAdapter = ArrayAdapter.createFromResource(getContext(),R.array.number,android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scoreOne.setAdapter(scoreAdapter);
            scoreTwo.setAdapter(scoreAdapter);
        }
        else
        {
            rootView = inflater.inflate(R.layout.submit_score_set, container, false);

            //ImageView construction
            team_1_logo = (ImageView) rootView.findViewById(R.id.imageView2);
            team_2_logo = (ImageView) rootView.findViewById(R.id.imageView3);

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

        //set textHeader
        text = (TextView)rootView.findViewById(R.id.textView10);
        text.setText(sport_name);

        //Change string to sync with database string

        sport_name = manager.UserToDatabase(sport_name);

        //Database
        databaseSport = FirebaseDatabase.getInstance().getReference("games");

        submitButton = (Button)rootView.findViewById(R.id.BTN_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseSport.push().getKey();

                if(sport_name == "soccer" || sport_name == "frisbee" ){
                    SportNorm sport = new SportNorm(id,teamOne.getSelectedItem().toString(),teamTwo.getSelectedItem().toString(),Integer.parseInt(scoreOne.getSelectedItem().toString()),Integer.parseInt(scoreTwo.getSelectedItem().toString()));
                    databaseSport.child(sport_name).child(id).setValue(sport);
                    Toast.makeText(getContext(),"Sport added", Toast.LENGTH_LONG).show();
                }
                else
                {
                    SportSet sport = new SportSet(id,teamOne.getSelectedItem().toString(),teamTwo.getSelectedItem().toString(),Integer.parseInt(scoreOne.getSelectedItem().toString()),Integer.parseInt(scoreTwo.getSelectedItem().toString())
                                                    ,Integer.parseInt(scoreThree.getSelectedItem().toString()),Integer.parseInt(scoreFour.getSelectedItem().toString())
                                                    ,Integer.parseInt(scoreFive.getSelectedItem().toString()),Integer.parseInt(scoreSix.getSelectedItem().toString()));
                    databaseSport.child(sport_name).child(id).setValue(sport);
                    Toast.makeText(getContext(),"Sport added", Toast.LENGTH_LONG).show();
                }


            }
        });

        return rootView;
    }
}
