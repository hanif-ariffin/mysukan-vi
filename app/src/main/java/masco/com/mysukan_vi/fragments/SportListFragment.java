package masco.com.mysukan_vi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.SportDetailActivity;
import masco.com.mysukan_vi.helper.SportManager;

/**
 * Created by akarin on 07/09/17.
 */

public class SportListFragment extends BaseFragment {

    private static final String TAG = "SportListFragment";

    private FirebaseRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;
    ArrayAdapter<String> sportArrayAdapter;
    private ListView sportListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_sports, container, false);

        sportListView = (ListView) rootView.findViewById(R.id.fragment_sport_listview_sport_list);

        sportArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, SportManager.getGames());
        sportListView.setAdapter(sportArrayAdapter);

        sportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", sportArrayAdapter.getItem(position).toString()); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        return rootView;
    }
}
