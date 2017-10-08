package masco.com.mysukan_vi.fragments.adminarea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.admin.SetWinnerActivity;
import masco.com.mysukan_vi.fragments.BaseFragment;
import masco.com.mysukan_vi.helper.ListAdapter;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.models.SingleScoreMatch;
import masco.com.mysukan_vi.models.TripleScoreMatch;

public class EditScoreFragment extends BaseFragment {

    private Bundle args;
    public String id;
    private static String sport_name;
    private TextView headerText;
    private DatabaseReference database;

    public List<TripleScoreMatch> sportSet;
    public List<SingleScoreMatch> sportNorm;
    private List<String> data1 = new ArrayList<>();
    private List<String> checker, arrayId;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView currentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_adminpage_matches_list, container, false);

        setHasOptionsMenu(true);
        checker = Arrays.asList("badminton_men_doubles", "badminton_women_doubles", "badminton_mixed_doubles", "squash_men_singles", "squash_women_singles");
        arrayId = new ArrayList<>();

        sportSet = new ArrayList<>();
        sportNorm = new ArrayList<>();

        args = getArguments();
        sport_name = args.getString(NameManager.SPORT_NAME);

        headerText = (TextView) view.findViewById(R.id.textView5);
        headerText.setText(sport_name);

        //switch name
        sport_name = NameManager.UserToDatabase(sport_name);


        final ListAdapter lArrayAdapter = new ListAdapter(getActivity(), R.layout.listview_component, data1);
        //retrieve data from database
        database = FirebaseDatabase.getInstance().getReference("games").child(sport_name);
        currentList = (ListView) view.findViewById(R.id.listView11);
        currentList.setAdapter(lArrayAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sportSet.clear();
                sportNorm.clear();
                data1.clear();
                for (DataSnapshot sportSnapshot : dataSnapshot.getChildren()) {
                    if (checker.contains(sport_name)) {
                        TripleScoreMatch sport = sportSnapshot.getValue(TripleScoreMatch.class);
                        sportSet.add(sport);
                        arrayId.add(sport.id);
                        if (sport.custom_name_1 != null) {
                            if (!sport.custom_name_2.isEmpty()) {
                                data1.add(sport.team_1_name + " : " + sport.custom_name_1 + " vs " + sport.team_2_name + " : " + sport.custom_name_2);
                            } else {
                                data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                            }
                        } else {
                            data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                        }
                        lArrayAdapter.notifyDataSetChanged();
                    } else {
                        SingleScoreMatch sport = sportSnapshot.getValue(SingleScoreMatch.class);
                        sportNorm.add(sport);
                        id = sport.id;
                        if (sport.custom_name_1 != null) {
                            if (!sport.custom_name_2.isEmpty()) {
                                data1.add(sport.team_1_name + " : " + sport.custom_name_1 + " vs " + sport.team_2_name + " : " + sport.custom_name_2);
                            } else {
                                data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                            }
                        } else {
                            data1.add(sport.team_1_name + " vs " + sport.team_2_name);
                        }
                        arrayId.add(sport.id);
                        lArrayAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        currentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                args = new Bundle();

                if (checker.contains(sport_name)) {
                    args.putString("teamOne", sportSet.get(i).team_1_name);
                    args.putString("customNameTeamOne", sportSet.get(i).custom_name_1);
                    args.putString("teamTwo", sportSet.get(i).team_2_name);
                    args.putString("customNameTeamTwo", sportSet.get(i).custom_name_2);
                    args.putString("id", sportSet.get(i).id);
                    args.putLong("match_date", sportSet.get(i).match_date);
                } else {
                    args.putString("teamOne", sportNorm.get(i).team_1_name);
                    args.putString("customNameTeamOne", sportNorm.get(i).custom_name_1);
                    args.putString("teamTwo", sportNorm.get(i).team_2_name);
                    args.putString("customNameTeamTwo", sportNorm.get(i).custom_name_2);
                    args.putString("id", sportNorm.get(i).id);
                    args.putLong("match_date", sportNorm.get(i).match_date);
                }

                args.putString(NameManager.SPORT_NAME, sport_name);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.winner).setVisible(true);
            menu.findItem(R.id.menu_item_sponsors).setVisible(false);
            menu.findItem(R.id.menu_item_developer_awesome).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.winner) {
            Intent intent = new Intent(getActivity(), SetWinnerActivity.class);
            args = new Bundle();
            args.putString(NameManager.SPORT_NAME, sport_name);
            intent.putExtras(args); //insert the bundle that this Activity already received.
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}