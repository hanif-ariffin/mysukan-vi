package tremblay412.com.mysukan.fragments.adminarea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import tremblay412.com.mysukan.fragments.BaseFragment;
import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.helper.SportManager;

public class UpdateScoreFragment extends BaseFragment {

    private DatabaseReference databaseSport;
    private ListView dataListView;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_update_score, container, false);
        setMenuVisibility(true);
        setHasOptionsMenu(true);
        dataListView = (ListView) view.findViewById(R.id.listview00);

//        newScore = (FloatingActionButton) view.findViewById(R.id.BTN_newscore);
//        newScore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newScore.setVisibility(View.INVISIBLE);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                android.support.v4.app.Fragment fr = new NewScoreFragment();
//                fragmentTransaction.replace(R.id.activity_admin, fr, fr.toString());
//                fragmentTransaction.commit();
//            }
//        });


        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, SportManager.getGames());
        dataListView.setAdapter(lArrayAdapter);

        //Listener for listview

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  dataListView.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                bundle = new Bundle();
                bundle.putString("sport_name", dataListView.getItemAtPosition(i).toString());
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fr = new EditScoreFragment();
                fragmentTransaction.addToBackStack(null);
                fr.setArguments(bundle);
                fragmentTransaction.replace(R.id.activity_admin, fr, fr.toString());
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}