package tremblay412.com.mysukan.fragments.adminarea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import tremblay412.com.mysukan.fragments.BaseFragment;
import tremblay412.com.mysukan.helper.NameManager;

public class EditScoreFragment extends BaseFragment {

    private Bundle args;
    public String sport_name, id;
    private TextView headerText;
    private DatabaseReference database;

    public List<SportSet> sportSet;
    public List<SportNorm> sportNorm;
    private static List<String> data1 = new ArrayList<>();
    private List<String> checker, arrayId;

    private ListView currentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_edit_score, container, false);

        checker = Arrays.asList("badminton_men_doubles", "badminton_women_doubles", "badminton_mixed_doubles", "squash_men_singles", "squash_women_singles");
        arrayId = new ArrayList<>();

        sportSet = new ArrayList<>();
        sportNorm = new ArrayList<>();

        args = getArguments();
        sport_name = args.getString("sport_name");
        headerText = (TextView) view.findViewById(R.id.textView5);
        headerText.setText(sport_name);

        //switch name
        sport_name = NameManager.UserToDatabase(sport_name);


        final ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data1);
        //retrieve data from database
        database = FirebaseDatabase.getInstance().getReference("games").child(sport_name);
        sportSet.clear();
        sportNorm.clear();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sportSnapshot : dataSnapshot.getChildren()) {
                    if (checker.contains(sport_name)) {
                        SportSet sport = sportSnapshot.getValue(SportSet.class);
                        sportSet.add(sport);
                        arrayId.add(sport.getId());
                        data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                        lArrayAdapter.notifyDataSetChanged();
                    } else {
                        SportNorm sport = sportSnapshot.getValue(SportNorm.class);
                        sportNorm.add(sport);
                        id = sport.getId();
                        data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                        arrayId.add(sport.getId());
                        lArrayAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        currentList = (ListView) view.findViewById(R.id.listView11);
        currentList.setAdapter(lArrayAdapter);

        currentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                args = new Bundle();

                if (checker.contains(sport_name)) {
                    args.putString("teamOne", sportSet.get(i).getTeam_1_name());
                    args.putString("teamTwo", sportSet.get(i).getTeam_2_name());
                    args.putString("id", sportSet.get(i).getId());
                    args.putLong("match_date", sportSet.get(i).match_date);
                } else {
                    args.putString("teamOne", sportNorm.get(i).getTeam_1_name());
                    args.putString("teamTwo", sportNorm.get(i).getTeam_2_name());
                    args.putString("id", sportNorm.get(i).getId());
                    args.putLong("match_date", sportNorm.get(i).match_date);
                }

                args.putString("sport_name", sport_name);
                sport_name = NameManager.DatabaseToUser(sport_name);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fr = new EditScore();
                fragmentTransaction.addToBackStack(null);
                fr.setArguments(args);
                fragmentTransaction.replace(R.id.edit_score, fr, fr.toString());
                fragmentTransaction.commit();
            }
        });

        return view;
    }


}