package tremblay412.com.mysukan.fragment.adminarea;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.helper.BaseFragment;
import tremblay412.com.mysukan.helper.NameSwitcher;

public class EditScoreFragment extends BaseFragment {

    private Bundle args;
    public String sport_name;
    private TextView headerText;
    private DatabaseReference database;

    public List<SportSet> sportSet;
    public List<SportNorm> sportNorm;
    private static List<String> data1 = new ArrayList<>();
    private List<String> checker;

    private ListView currentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_edit_score,container,false);

        checker = Arrays.asList("badminton_men_doubles","badminton_women_doubles","badminton_mixed_doubles","squash_men_singles","squash_women_singles");

        sportSet = new ArrayList<>();
        sportNorm = new ArrayList<>();

        args = getArguments();
        sport_name =  args.getString("sport_name");
        headerText = (TextView)view.findViewById(R.id.textView5);
        headerText.setText(sport_name);

        //switch name
        NameSwitcher switcher = new NameSwitcher();
        sport_name = switcher.UserToDatabase(sport_name);


        final ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data1);
        //retrieve data from database
        database = FirebaseDatabase.getInstance().getReference("games").child(sport_name);
        database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sportSet.clear();
                data1.clear();
                for(DataSnapshot sportSnapshot : dataSnapshot.getChildren()){
                    if(checker.contains(sport_name)) {
                        SportNorm sport = sportSnapshot.getValue(SportNorm.class);
                        sportNorm.add(sport);
                        data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                        lArrayAdapter.notifyDataSetChanged();
                    }
                    else{
                        SportSet sport = sportSnapshot.getValue(SportSet.class);
                        sportSet.add(sport);
                        data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                        lArrayAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        currentList = (ListView)view.findViewById(R.id.listView11);
        currentList.setAdapter(lArrayAdapter);

        currentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NameSwitcher switcher1 = new NameSwitcher();
                sport_name = switcher1.DatabaseToUser(sport_name);
                args = new Bundle();
                args.putString("sport_type",sport_name);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fr = new SubmitScore2();
                fr.setArguments(args);
                fragmentTransaction.replace(R.id.edit_score,fr,fr.toString());
                fragmentTransaction.commit();
            }
        });

        return view;
    }


}
