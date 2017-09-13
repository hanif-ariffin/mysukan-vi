package tremblay412.com.mysukan.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import tremblay412.com.mysukan.Activities.CreateSportActivity;
import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.MatchDetailViewHolder;

/**
 * Created by akarin on 07/09/17.
 */

public class RealtimeSportListFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "SportListFragment";

    private FirebaseRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;
    private RecyclerView sportNameRecyclerView;
    private DatabaseReference sportNameReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, System.lineSeparator() + "ONCREATEVIEW" + System.lineSeparator());

        View rootView = inflater.inflate(R.layout.realtime_fragment_sport_list, container, false);

        sportNameRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_sport_listview_sport_list);

        sportNameReference = FirebaseDatabase.getInstance().getReference();

        // Button launches NewPostActivity
        rootView.findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateSportActivity.class));
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        sportNameRecyclerView.setLayoutManager(mManager);

        Query recentPostsQuery = sportNameReference.child("sport_list").limitToFirst(100);
        mAdapter = new FirebaseRecyclerAdapter<String, MatchDetailViewHolder>(String.class, R.layout.include_item_sport,
                MatchDetailViewHolder.class, recentPostsQuery) {
            @Override
            protected void populateViewHolder(MatchDetailViewHolder viewHolder, String model, int position) {
                final DatabaseReference postRef = getRef(position);
                Log.d(TAG, "RecyclerView position:" + position + " value:" + postRef.getKey());
                viewHolder.match_time.setText(model);
            }
        };

        sportNameRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.d(TAG, "User clicked at View with id:" + id);
    }
}
