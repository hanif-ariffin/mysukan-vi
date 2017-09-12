package tremblay412.com.mysukan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditScoreFragment extends BaseFragment {

    private Bundle args;
    private String sport_name;
    private TextView headerText;
    private DatabaseReference database;

    public List<SportSet> sportSet;
    private static List<String> data1 = new ArrayList<>();

    private ListView currentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_edit_score,container,false);

        sportSet = new ArrayList<>();

        args = getArguments();
        sport_name =  args.getString("sport_name");
        headerText = (TextView)view.findViewById(R.id.textView5);
        headerText.setText(sport_name);

        switch (sport_name){
            case "Soccer":
                sport_name = "soccer";
                break;
            case "Badminton Men Doubles":
                sport_name = "badminton_men_doubles";
                break;
            case "Badminton Women Doubles":
                sport_name = "badminton_women_doubles";
                break;
            case "Badminton Mixed Doubles":
                sport_name = "badminton_mixed_doubles";
                break;
            case "Squash Men Singles":
                sport_name = "squash_men_singles";
                break;
            case "Squash Women Singles":
                sport_name = "squash_women_singles";
                break;
            case "Frisbee":
                sport_name = "frisbee";
                break;
            case "Dodgeball":
                sport_name = "dodgeball";
                break;
            case "Netball":
                sport_name = "netball";
                break;
            case "Basketball":
                sport_name = "basketball";
                break;
            case "Sepak Takraw":
                sport_name = "sepak_takraw";
                break;
            case "Volleyball":
                sport_name = "volleyball";
                break;
            case "Fifa":
                sport_name = "fifa";
                break;
            case "Rocket League":
                sport_name = "rocket_league";
                break;

        }

        final ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data1);
        //retrieve data from database
        database = FirebaseDatabase.getInstance().getReference("games").child(sport_name);
        database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sportSet.clear();
                data1.clear();
                for(DataSnapshot sportSnapshot : dataSnapshot.getChildren()){
                    SportSet sport = sportSnapshot.getValue(SportSet.class);
                    sportSet.add(sport);
                    data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                    lArrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        currentList = (ListView)view.findViewById(R.id.listView11);


        currentList.setAdapter(lArrayAdapter);

        return view;
    }


}
