package tremblay412.com.mysukan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
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
import tremblay412.com.mysukan.models.TripleScoreMatch;

/**
 * Created by Akarin on 9/9/2017.
 */

public class SportDetailActivity extends AppCompatActivity {

    private static final String TAG = "SportDetailActivity";

    /**
     * Variables to connect to Firebase
     */
    private FirebaseRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;
    private RecyclerView sportNameRecyclerView;
    private DatabaseReference sportNameReference;

    // Enlarged match detail UI hooks
    private TextView textViewteamOneName, textViewteamTwoName, matchScoreOne, matchScoreTwo, matchScoreThree;
    private ImageView imageViewteamOneImage, imageViewteamTwoImage;


    // Current sport
    String sportName;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        // Get the XML object
        textViewteamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_1);
        textViewteamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_2);
        imageViewteamOneImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_1);
        imageViewteamTwoImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_2);
        matchScoreOne = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_1);
        matchScoreTwo = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_2);
        matchScoreThree = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_3);

        // Initialized Firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Bundle received from the Activity creating this Activity
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            Log.wtf(TAG, "BUNDLE IS NULL, THE ACTIVITY CALLING THIS INTENT DOES NOT PROVIDE THE SPORT NAME REQUIRED");
        } else {
            sportName = bundle.getString("sport_name");
            sportNameRecyclerView = (RecyclerView) findViewById(R.id.activity_sport_matches_recyclerview_match);
            getSupportActionBar().setTitle(sportName);

            sportNameReference = FirebaseDatabase.getInstance().getReference();

            mManager = new LinearLayoutManager(this);
            //mManager.setReverseLayout(true);
            //mManager.setStackFromEnd(true);
            sportNameRecyclerView.setLayoutManager(mManager);

            Query queryResult = sportNameReference.child("games").child(NameManager.UserToDatabase(sportName)).orderByChild("match_date");

            if (SportManager.isSingleScore(sportName)) {
                mAdapter = new FirebaseRecyclerAdapter<SingleScoreMatch, MatchDetailViewHolder>(SingleScoreMatch.class, R.layout.include_item_minimized_match_detail,
                        MatchDetailViewHolder.class, queryResult) {
                    @Override
                    protected void populateViewHolder(final MatchDetailViewHolder viewHolder, final SingleScoreMatch model, final int position) {
                        final DatabaseReference postRef = getRef(position);
                        String time = "";

                        if (model.match_date != null) {
                            time = new SimpleDateFormat("HH:mm a").format(new Date(model.match_date* 1000L));
                        }
                        viewHolder.match_time.setText(time);
                        viewHolder.team_1.setText(model.team_1_name);
                        viewHolder.team_2.setText(model.team_2_name);

                        if (position == 0){
                            updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1}, new Long[]{model.team_2_score_1});
                        }

                        viewHolder.team_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1}, new Long[]{model.team_2_score_1});
                            }
                        });
                        viewHolder.team_2.setOnClickListener(new View.OnClickListener()

                        {
                            @Override
                            public void onClick(View v) {
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1}, new Long[]{model.team_2_score_1});
                            }
                        });
                        viewHolder.match_time.setOnClickListener(new View.OnClickListener()

                        {
                            @Override
                            public void onClick(View v) {
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1}, new Long[]{model.team_2_score_1});
                            }
                        });
                    }
                };
            } else {
                mAdapter = new FirebaseRecyclerAdapter<TripleScoreMatch, MatchDetailViewHolder>(TripleScoreMatch.class, R.layout.include_item_minimized_match_detail,
                        MatchDetailViewHolder.class, queryResult) {
                    @Override
                    protected void populateViewHolder(final MatchDetailViewHolder viewHolder, final TripleScoreMatch model, final int position) {
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

                        if(position == 0){
                            updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1, model.team_1_score_2, model.team_1_score_3}, new Long[]{model.team_2_score_1, model.team_2_score_2, model.team_2_score_3});
                        }


                        viewHolder.team_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "User clicked at a match with id:" + model.id);
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1, model.team_1_score_2, model.team_1_score_3}, new Long[]{model.team_2_score_1, model.team_2_score_2, model.team_2_score_3});
                            }
                        });
                        viewHolder.team_2.setOnClickListener(new View.OnClickListener()

                        {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "User clicked at a match with id:" + model.id);
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1, model.team_1_score_2, model.team_1_score_3}, new Long[]{model.team_2_score_1, model.team_2_score_2, model.team_2_score_3});
                            }
                        });
                        viewHolder.match_time.setOnClickListener(new View.OnClickListener()

                        {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "User clicked at a match with id:" + model.id);
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1, model.team_1_score_2, model.team_1_score_3}, new Long[]{model.team_2_score_1, model.team_2_score_2, model.team_2_score_3});
                            }
                        });
                    }
                };
            }
            sportNameRecyclerView.setAdapter(mAdapter);
        }
    }

    public void updateEnlargedMatchDetail(String teamOneName, String teamTwoName, Long[] teamOneScore, Long[] teamTwoScore) {
        textViewteamOneName.setText(teamOneName);
        imageViewteamOneImage.setImageResource(NameManager.getImageId(teamOneName));
        textViewteamTwoName.setText(teamTwoName);
        imageViewteamTwoImage.setImageResource(NameManager.getImageId(teamTwoName));

        if (teamOneScore.length > 1 && teamTwoScore.length > 1) {
            matchScoreOne.setText(teamOneScore[0] + " - " + teamTwoScore[0]);

            if (teamOneScore[1] != null && teamTwoScore[1] != null) {
                matchScoreTwo.setText(teamOneScore[1] + " - " + teamTwoScore[2]);
            }

            if (teamOneScore[2] != null && teamTwoScore[2] != null) {
                matchScoreThree.setText(teamOneScore[2] + " - " + teamTwoScore[2]);
            }
        } else {
            matchScoreOne.setText("");
            matchScoreTwo.setText(teamOneScore[0] + " - " + teamTwoScore[0]);
            matchScoreTwo.setTextSize(30);
            matchScoreThree.setText("");
        }
        if (firebaseAuth.getCurrentUser() != null) {
            Log.d(TAG, "User logged in with email:" + firebaseAuth.getCurrentUser().getEmail());
        } else {
            Log.d(TAG, "User is not logged in");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.winner).setVisible(true);
            menu.findItem(R.id.menu_developers_info).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.winner) {
            Intent intent = new Intent(SportDetailActivity.this, WinnerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("sport_name", sportName); //Your id
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
