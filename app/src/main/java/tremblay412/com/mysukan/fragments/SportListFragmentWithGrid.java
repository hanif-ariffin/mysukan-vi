package tremblay412.com.mysukan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.helper.ImageAdapter;
import tremblay412.com.mysukan.helper.SportManager;

/**
 * Created by r3xas on 9/25/2017.
 */

public class SportListFragmentWithGrid extends BaseFragment {
    private static final String TAG = "SportListFragmentWithGrid";

    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_sports_with_grids, container, false);

        gridView = (GridView) rootView.findViewById(R.id.fragment_sports_with_grids_gridview);

        gridView.setAdapter(new ImageAdapter(getActivity(), SportManager.getGames()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(), ((TextView) v.findViewById(R.id.grid_item_label)).getText(), Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}
