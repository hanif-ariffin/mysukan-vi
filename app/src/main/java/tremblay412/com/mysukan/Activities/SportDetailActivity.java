package tremblay412.com.mysukan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import tremblay412.com.mysukan.Models.Soccer;
import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.SportManager;
import tremblay412.com.mysukan.SportViewHolder;

/**
 * Created by Akarin on 9/9/2017.
 */

public class SportDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "SportDetailActivity";

    private FirebaseRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;
    private RecyclerView sportNameRecyclerView;
    private DatabaseReference sportNameReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        Bundle bundle = getIntent().getExtras();
        String sportName; // or other values
        if (bundle == null) {
            Log.wtf(TAG, "BUNDLE IS NULL, THE ACTIVITY CALLING THIS INTENT DOES NOT PROVIDE THE SPORT NAME REQUIRED");
        } else {
            sportName = bundle.getString("sportName");
            sportNameRecyclerView = (RecyclerView) findViewById(R.id.activity_sport_matches_recyclerview_match);

            sportNameReference = FirebaseDatabase.getInstance().getReference();

            mManager = new LinearLayoutManager(this);
            mManager.setReverseLayout(true);
            mManager.setStackFromEnd(true);
            sportNameRecyclerView.setLayoutManager(mManager);

            Query recentPostsQuery = sportNameReference.child("games").child(SportManager.convertToDb(sportName));
            Log.d(TAG, recentPostsQuery.toString());
            mAdapter = new FirebaseRecyclerAdapter<Soccer, SportViewHolder>(Soccer.class, R.layout.include_item_sport,
                    SportViewHolder.class, recentPostsQuery) {
                @Override
                protected void populateViewHolder(SportViewHolder viewHolder, Soccer model, int position) {
                    final DatabaseReference postRef = getRef(position);
                    Log.d(TAG, "sportNameRecyclerView is clicked at position:" + position + " with value:" + postRef.getKey());
                    viewHolder.sportName.setText(postRef.child(postRef.getKey()).toString());
                }
            };

            sportNameRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
