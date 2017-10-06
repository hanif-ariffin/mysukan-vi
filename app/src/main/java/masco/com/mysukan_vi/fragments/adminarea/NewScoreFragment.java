package masco.com.mysukan_vi.fragments.adminarea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.fragments.BaseFragment;
import masco.com.mysukan_vi.helper.ListAdapter;
import masco.com.mysukan_vi.helper.SportManager;

public class NewScoreFragment extends BaseFragment {


    // UI references.
    private ListView iListGames;
    Bundle args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_new_score_fragment, container, false);


        //Listview for sport
        iListGames = (ListView) rootView.findViewById(R.id.sport_listview);
        ListAdapter lArrayAdapter = new ListAdapter(getActivity(), R.layout.listview_component, SportManager.getGames());
        iListGames.setAdapter(lArrayAdapter);

        //Listener for listview

        iListGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                args = new Bundle();
                args.putString("sport_name", iListGames.getItemAtPosition(i).toString());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                Fragment fr = new CreateMatchFragment();
                fr.setArguments(args);
                fragmentTransaction.replace(R.id.new_score_fragment, fr, fr.toString());
                fragmentTransaction.commit();
            }
        });


        return rootView;
    }
}
