package tremblay412.com.mysukan.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.SportManager;

/**
 * Created by akarin on 07/09/17.
 */

public class SportFragment extends BaseFragment {

    // UI references.
    private ListView iListGames;
    private ViewGroup iMainPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.main_page, container, false);

        //mMainPage = findViewById(R.layout.main_page);
        iMainPage = (ViewGroup) rootView.findViewById(R.id.main_page);

        iListGames = (ListView) rootView.findViewById(R.id.listview1);

        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, SportManager.getGames());
        iListGames.setAdapter(lArrayAdapter);

        return rootView;
    }
}