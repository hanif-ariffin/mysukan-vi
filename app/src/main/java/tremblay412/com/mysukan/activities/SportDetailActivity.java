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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    // Enlarged match detail UI hooks
    private TextView textViewteamOneName, textViewteamTwoName, matchScore;
    private ImageView imageViewteamOneImage, imageViewteamTwoImage;


    // Current sport
    String sportName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        // Get the XML object
        textViewteamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_one);
        textViewteamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_two);
        imageViewteamOneImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_one);
        imageViewteamTwoImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_two);
        matchScore = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score);

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

            Query queryResult = sportNameReference.child("games").child(NameManager.UserToDatabase(sportName));

            Log.d(TAG, "Received sportName:" + sportName + " isSingleScore:" + SportManager.isSingleScore(sportName));
            mAdapter = new FirebaseRecyclerAdapter<SingleScoreMatch, MatchDetailViewHolder>(SingleScoreMatch.class, R.layout.include_item_minimized_match_detail,
                    MatchDetailViewHolder.class, queryResult) {
                @Override
                protected void populateViewHolder(final MatchDetailViewHolder viewHolder, final SingleScoreMatch model, final int position) {
                    final DatabaseReference postRef = getRef(position);
                    Log.d(TAG, "postRef with position:" + position + " contains:" + postRef.toString());
                    Log.d(TAG, "Model obtained with values id:" + model.id + " match_date:" + model.match_date + " team_1_name:" + model.team_1_name + " team_2_name:" + model.team_2_name);
                    String time = "";
                    if (model.match_date != null) {
                        time = new SimpleDateFormat("HH:mm a").format(new Date(model.match_date * 1000L));
                    }
                    viewHolder.match_time.setText(time);
                    viewHolder.team_1.setText(model.team_1_name);
                    viewHolder.team_2.setText(model.team_2_name);

                    viewHolder.team_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "User clicked at a match with id:" + model.id);
                            updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, model.team_1_score_1, model.team_2_score_1);
                        }
                    });
                    viewHolder.team_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "User clicked at a match with id:" + model.id);
                            updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, model.team_1_score_1, model.team_2_score_1);
                        }
                    });
                    viewHolder.match_time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "User clicked at a match with id:" + model.id);
                            updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, model.team_1_score_1, model.team_2_score_1);
                        }
                    });
                }
            };
            sportNameRecyclerView.setAdapter(mAdapter);
        }
    }

    public void updateEnlargedMatchDetail(String teamOneName, String teamTwoName, Long teamOneScore, Long teamTwoScore) {
        textViewteamOneName.setText(teamOneName);
        imageViewteamOneImage.setImageResource(NameManager.getImageId(teamOneName));
        textViewteamTwoName.setText(teamTwoName);
        imageViewteamTwoImage.setImageResource(NameManager.getImageId(teamTwoName));
        matchScore.setText(teamOneScore + " - " + teamTwoScore);
    }
}
