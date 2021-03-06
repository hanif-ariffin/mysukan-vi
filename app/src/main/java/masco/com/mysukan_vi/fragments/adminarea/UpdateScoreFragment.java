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
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.helper.SportManager;

public class UpdateScoreFragment extends BaseFragment {

    private ListView dataListView;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_update_score, container, false);
        setMenuVisibility(true);
        setHasOptionsMenu(true);
        dataListView = (ListView) view.findViewById(R.id.listview00);


        ListAdapter lArrayAdapter = new ListAdapter(getActivity(), R.layout.listview_component, SportManager.getGames());
        dataListView.setAdapter(lArrayAdapter);

        //Listener for listview

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //  dataListView.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                bundle = new Bundle();
                bundle.putString(NameManager.SPORT_NAME, dataListView.getItemAtPosition(i).toString());
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
