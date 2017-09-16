package tremblay412.com.mysukan.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.helper.MatchDetailViewHolder;
import tremblay412.com.mysukan.helper.NameManager;
import tremblay412.com.mysukan.helper.SportManager;
import tremblay412.com.mysukan.models.SingleScoreMatch;

/**
 * Created by Akarin on 9/9/2017.
 */

public class SportDetailActivity extends BaseActivity {

    private static final String TAG = "SportDetailActivity";

    /**
     * Variables to connect to Firebase
     */
    private FirebaseRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;
    private RecyclerView sportNameRecyclerView;
    private DatabaseReference sportNameReference;

    /**
     * SportDetail's top enlarged UI
     */
    private TextView teamOneName, teamTwoName;
    private ImageView teamOneImage, teamTwoImage;
    String sportName; // or other values


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        // Get the XML object
        teamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_one);
        teamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_one);
        teamOneImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_one);
        teamTwoImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_two);


        // Bundle received from the Activity creating this Activity
        Bundle bundle = getIntent().getExtras();

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

            final NameManager nameManager = new NameManager();
            Query queryResult = sportNameReference.child("games").child(nameManager.UserToDatabase(sportName));

            Log.d(TAG, "Received sportName:" + sportName + " isSingleScore:" + SportManager.isSingleScore(sportName));
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

                    viewHolder.team_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "User clicked at match with id:" + model.id);
                            teamOneName.setText(model.team_1_name);
                            teamOneImage.setImageResource(nameManager.getImageId(model.team_1_name));
                            teamTwoName.setText(model.team_2_name);
                            teamTwoImage.setImageResource(nameManager.getImageId(model.team_2_name));
                        }
                    });
                    viewHolder.team_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "User clicked at match with id:" + model.id);
                            teamOneName.setText(model.team_1_name);
                            teamOneImage.setImageResource(nameManager.getImageId(model.team_1_name));
                            teamTwoName.setText(model.team_2_name);
                            teamTwoImage.setImageResource(nameManager.getImageId(model.team_2_name));
                        }
                    });
                    viewHolder.match_time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "User clicked at match with id:" + model.id);
                            teamOneName.setText(model.team_1_name);
                            teamOneImage.setImageResource(nameManager.getImageId(model.team_1_name));
                            teamTwoName.setText(model.team_2_name);
                            teamTwoImage.setImageResource(nameManager.getImageId(model.team_2_name));
                        }
                    });
                }
            };
            sportNameRecyclerView.setAdapter(mAdapter);
        }
    }
}
