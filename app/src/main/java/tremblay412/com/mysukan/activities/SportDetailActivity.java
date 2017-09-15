package tremblay412.com.mysukan.activities;

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

import tremblay412.com.mysukan.helper.MatchDetailViewHolder;
import tremblay412.com.mysukan.helper.NameManager;
import tremblay412.com.mysukan.models.SingleScoreMatch;
import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.helper.SportManager;
import tremblay412.com.mysukan.models.TripleScoreMatch;

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

            NameManager nameManager = new NameManager();
            Query queryResult = sportNameReference.child("games").child(nameManager.UserToDatabase(sportName)).limitToFirst(100);
            Log.d(TAG, "Received sportName:" + sportName + " isSingleScore:" + SportManager.isSingleScore(sportName));

            if (SportManager.isSingleScore(sportName)) {
                mAdapter = new FirebaseRecyclerAdapter<SingleScoreMatch, MatchDetailViewHolder>(SingleScoreMatch.class, R.layout.include_item_minimized_match_detail,
                        MatchDetailViewHolder.class, queryResult) {
                    @Override
                    protected void populateViewHolder(final MatchDetailViewHolder viewHolder, final SingleScoreMatch model, final int position) {
                        final DatabaseReference postRef = getRef(position);
                        Log.d(TAG, "postRef with position:" + position + " contains:" + postRef.toString());
                        Log.d(TAG, "Model obtained with values id:" + model.id + " match_date:" + model.match_date + " team_1_name:" + model.team_1_name + " team_2_name:" + model.team_2_name);

                        viewHolder.match_time.setText("" + model.match_date);
                        viewHolder.team_1.setText(model.team_1_name);
                        viewHolder.team_2.setText(model.team_2_name);
                    }
                };
            } else {
                mAdapter = new FirebaseRecyclerAdapter<TripleScoreMatch, MatchDetailViewHolder>(TripleScoreMatch.class, R.layout.include_item_minimized_match_detail,
                        MatchDetailViewHolder.class, queryResult) {
                    @Override
                    protected void populateViewHolder(MatchDetailViewHolder viewHolder, TripleScoreMatch model, final int position) {

                        Log.d(TAG, "Model obtained with values id:" + model.id + " match_date:" + model.match_date + " team_1_name:" + model.team_1_name + " team_2_name:" + model.team_2_name);

                        viewHolder.match_time.setText("" + model.match_date);
                        viewHolder.team_1.setText(model.team_1_name);
                        viewHolder.team_2.setText(model.team_2_name);
                    }
                };
            }
            sportNameRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
