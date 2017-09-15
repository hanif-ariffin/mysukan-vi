package tremblay412.com.mysukan.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import tremblay412.com.mysukan.fragments.adminarea.EditScoreFragment;
import tremblay412.com.mysukan.fragments.adminarea.NewScoreFragment;
import tremblay412.com.mysukan.R;

public class AdminFragment extends BaseFragment {
    Button newScore;
    private DatabaseReference databaseSport;
    private ListView dataListView;
    private Bundle args;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_admin,container,false);
        setMenuVisibility(true);
        setHasOptionsMenu(true);
        dataListView = (ListView) view.findViewById(R.id.listview00);

        newScore = (Button)view.findViewById(R.id.BTN_newscore);
        newScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newScore.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                android.support.v4.app.Fragment fr = new NewScoreFragment();
                fragmentTransaction.replace(R.id.activity_admin,fr,fr.toString());
                fragmentTransaction.commit();
            }
        });





        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getGames());
        dataListView.setAdapter(lArrayAdapter);

        //Listener for listview

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                newScore.setVisibility(View.INVISIBLE);
                dataListView.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                args = new Bundle();
                args.putString("sport_name",dataListView.getItemAtPosition(i).toString());
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fr = new EditScoreFragment();
                fr.setArguments(args);
                fragmentTransaction.replace(R.id.activity_admin,fr,fr.toString());
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private List<String> getGames() {
        List<String> lGames = new ArrayList<>();
        lGames.add("Volleyball");
        lGames.add("SingleScoreMatch");
        lGames.add("Badminton Men Doubles");
        lGames.add("Badminton Women Doubles");
        lGames.add("Badminton Mixed Doubles");
        lGames.add("Squash Men Singles");
        lGames.add("Squash Women Singles");
        lGames.add("Frisbee");
        lGames.add("Dodgeball");
        lGames.add("Netball");
        lGames.add("Basketball");
        lGames.add("Sepak Takraw");
        lGames.add("Fifa");
        lGames.add("Rocket League");
        return lGames;
    }
}
