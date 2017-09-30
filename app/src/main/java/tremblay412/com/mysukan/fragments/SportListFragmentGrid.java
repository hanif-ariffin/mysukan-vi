package tremblay412.com.mysukan.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import tremblay412.com.mysukan.R;

/**
 * Created by r3xas on 9/25/2017.
 */

public class SportListFragmentGrid extends BaseFragment {
    private static final String TAG = "SportListFragmentGrid";

    private ImageButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_sports_with_grids, container, false);

        button = (ImageButton) rootView.findViewById(R.id.fragment_sports_image_soccer);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
            }
        });
        return rootView;
    }
}
