package tremblay412.com.mysukan;

import android.support.v7.app.AppCompatActivity;
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

    private Spinner teamOne,teamTwo,scoreOne,scoreTwo;
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
        View rootView = inflater.inflate(R.layout.activity_submit_score, container, false);

        //get data from previous fragment
        args = getArguments();
        name = args.getString("sport_type");
        text = (TextView)rootView.findViewById(R.id.textView10);
        text.setText(name);



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

        //Database
        databaseSport = FirebaseDatabase.getInstance().getReference("games");

        submit = (Button)rootView.findViewById(R.id.BTN_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseSport.push().getKey();
                Sport sport = new Sport(teamOne.getSelectedItem().toString(),teamTwo.getSelectedItem().toString(),scoreOne.getSelectedItem().toString(),scoreTwo.getSelectedItem().toString());

                databaseSport.child(name).child(id).setValue(sport);

                Toast.makeText(getContext(),"Sport added", Toast.LENGTH_LONG).show();

            }
        });

        return rootView;
    }
}
