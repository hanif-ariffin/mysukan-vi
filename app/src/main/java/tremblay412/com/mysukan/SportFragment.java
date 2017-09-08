package tremblay412.com.mysukan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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

        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getGames());
        iListGames.setAdapter(lArrayAdapter);

        return rootView;
    }


    private List<String> getGames() {
        List<String> lGames = new ArrayList<>();
        lGames.add("Volleyball");
        lGames.add("Telematch (F)");
        lGames.add("Dodgeball");
        lGames.add("Frisbee");
        lGames.add("Badminton");
        lGames.add("Squash");
        lGames.add("Volleyball (F)");
        lGames.add("Netball");
        lGames.add("Basketball");
        lGames.add("Football");
        lGames.add("Fifa (E-Games)");
        lGames.add("Rocket League (E-games)");
        return lGames;
    }
}
