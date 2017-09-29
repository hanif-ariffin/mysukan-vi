package tremblay412.com.mysukan.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.activities.SportDetailActivity;
import tremblay412.com.mysukan.helper.ImageAdapter;
import tremblay412.com.mysukan.helper.SportManager;

/**
 * Created by r3xas on 9/25/2017.
 */

public class SportListFragmentWithGrid extends BaseFragment {
    private static final String TAG = "SportListFragment";

    private FirebaseRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;
    ArrayAdapter<String> sportArrayAdapter;
    private ListView sportListView;
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.main, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView1);

        gridView.setAdapter(new ImageAdapter(getActivity(), (String[]) SportManager.getGames().toArray()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(),((TextView) v.findViewById(R.id.grid_item_label)).getText(), Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}
