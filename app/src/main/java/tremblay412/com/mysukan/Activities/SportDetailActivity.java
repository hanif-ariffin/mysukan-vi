package tremblay412.com.mysukan.Activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import tremblay412.com.mysukan.MatchDetailViewHolder;
import tremblay412.com.mysukan.Models.Soccer;
import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.SportManager;

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
            sportName = bundle.getString("sport_name");
            sportNameRecyclerView = (RecyclerView) findViewById(R.id.activity_sport_matches_recyclerview_match);

            sportNameReference = FirebaseDatabase.getInstance().getReference();

            mManager = new LinearLayoutManager(this);
            mManager.setReverseLayout(true);
            mManager.setStackFromEnd(true);
            sportNameRecyclerView.setLayoutManager(mManager);

            Query recentPostsQuery = sportNameReference.child("games").child(SportManager.convertToDb(sportName));
            Log.d(TAG, recentPostsQuery.toString());
            mAdapter = new FirebaseRecyclerAdapter<Soccer, MatchDetailViewHolder>(Soccer.class, R.layout.include_item_minimized_match_detail,
                    MatchDetailViewHolder.class, recentPostsQuery) {
                @Override
                protected void populateViewHolder(final MatchDetailViewHolder viewHolder, final Soccer model, final int position) {
                    final DatabaseReference postRef = getRef(position);

                    Log.d(TAG, "Model obtained with values id:" + model.id + " match_date:" + model.match_date + " team_1_name:" + model.team_1_name + " team_2_name:" + model.team_2_name);
                    Log.d(TAG, "" + viewHolder.sportName + " " + viewHolder.match_time + " " + viewHolder.team_1 + " " + viewHolder.team_2);

                    String matchDate = "" + model.match_date;
                    String teamOne = "" + model.team_1_name;
                    String teamTwo = "" + model.team_2_name;

                    viewHolder.match_time.setText(matchDate);
                    viewHolder.team_1.setText(teamOne);
                    viewHolder.team_2.setText(teamTwo);
                }
            };

            sportNameRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
