package tremblay412.com.mysukan.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import tremblay412.com.mysukan.models.TripleScoreMatch;

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
    private TextView textViewteamOneName, textViewteamTwoName, matchScoreOne, matchScoreTwo, matchScoreThree;
    private ImageView imageViewteamOneImage, imageViewteamTwoImage;


    // Current sport
    String sportName;
    protected InitTask _initTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        showProgressDialog("Querying database...");

        // Get the XML object
        textViewteamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_1);
        textViewteamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_2);
        imageViewteamOneImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_1);
        imageViewteamTwoImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_2);
        matchScoreOne = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_1);
        matchScoreTwo = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_2);
        matchScoreThree = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_3);

        // Bundle received from the Activity creating this Activity
        Bundle bundle = getIntent().getExtras();


        if (bundle == null) {
            Log.wtf(TAG, "BUNDLE IS NULL, THE ACTIVITY CALLING THIS INTENT DOES NOT PROVIDE THE SPORT NAME REQUIRED");
        } else {
            Log.i(TAG, "Bundle is not null, proceeding to retrieve information from database");
            sportName = bundle.getString("sport_name");
            sportNameRecyclerView = (RecyclerView) findViewById(R.id.activity_sport_matches_recyclerview_match);
            getSupportActionBar().setTitle(sportName);

            sportNameReference = FirebaseDatabase.getInstance().getReference();

            mManager = new LinearLayoutManager(this);
            sportNameRecyclerView.setLayoutManager(mManager);

            Query queryResult = sportNameReference.child("games").child(NameManager.UserToDatabase(sportName)).orderByChild("match_date");

            if (SportManager.isSingleScore(sportName)) {
                Log.i(TAG, "Bundle is a single score type match");
                mAdapter = new FirebaseRecyclerAdapter<SingleScoreMatch, MatchDetailViewHolder>(SingleScoreMatch.class, R.layout.include_item_minimized_match_detail,
                        MatchDetailViewHolder.class, queryResult) {
                    @Override
                    protected void populateViewHolder(final MatchDetailViewHolder viewHolder, final SingleScoreMatch model, final int position) {
                        final DatabaseReference postRef = getRef(position);
                        String time = "";

                        if (model.match_date != null) {
                            time = new SimpleDateFormat("HH:mm a").format(new Date(model.match_date * 1000L));
                        }
                        viewHolder.match_time.setText(time);
                        viewHolder.team_1.setText(model.team_1_name);
                        viewHolder.team_2.setText(model.team_2_name);

                        if (position == 0) {
                            updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1}, new Long[]{model.team_2_score_1});
                        }

                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1}, new Long[]{model.team_2_score_1});
                            }
                        });
                    }
                };
            } else {
                Log.i(TAG, "Bundle is a triple score type match");
                mAdapter = new FirebaseRecyclerAdapter<TripleScoreMatch, MatchDetailViewHolder>(TripleScoreMatch.class, R.layout.include_item_minimized_match_detail,
                        MatchDetailViewHolder.class, queryResult) {
                    @Override
                    protected void populateViewHolder(final MatchDetailViewHolder viewHolder, final TripleScoreMatch model, final int position) {
                        final DatabaseReference postRef = getRef(position);
                        String time = "";

                        if (model.match_date != null) {
                            time = new SimpleDateFormat("HH:mm a").format(new Date(model.match_date * 1000L));
                        }
                        viewHolder.match_time.setText(time);
                        viewHolder.team_1.setText(model.team_1_name);
                        viewHolder.team_2.setText(model.team_2_name);

                        if (position == 0) {
                            updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1, model.team_1_score_2, model.team_1_score_3}, new Long[]{model.team_2_score_1, model.team_2_score_2, model.team_2_score_3});
                        }

                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                updateEnlargedMatchDetail(model.team_1_name, model.team_2_name, new Long[]{model.team_1_score_1, model.team_1_score_2, model.team_1_score_3}, new Long[]{model.team_2_score_1, model.team_2_score_2, model.team_2_score_3});
                            }
                        });
                    }
                };
            }

            sportNameRecyclerView.setAdapter(mAdapter);
            _initTask = new InitTask();
            _initTask.execute(this);
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
                matchScoreTwo.setTextSize(20);
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

        hideProgressDialog();
    }

    protected class InitTask extends AsyncTask<Context, Integer, Boolean> {

        private static final String TAG = "InitTask";

        // -- run intensive processes here
        // -- notice that the datatype of the first param in the class definition matches the param passed to this
        // method
        // -- and that the datatype of the last param in the class definition matches the return type of this method
        @Override
        protected Boolean doInBackground(Context[] params) {
            // -- on every iteration
            // -- runs a while loop that causes the thread to sleep for 50 milliseconds
            // -- publishes the progress - calls the onProgressUpdate handler defined below
            // -- and increments the counter variable i by one
            int waitCounter = 0;
            int sleepTime = 200;
            int maxWaitTime = sleepTime / 4;
            while (isProcessDialogShowing()) {
                try {
                    Thread.sleep(sleepTime);
                    publishProgress(waitCounter);
                    if (waitCounter > maxWaitTime) {
                        hideProgressDialog();
                    }
                    waitCounter++;
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                }
            }
            return waitCounter > maxWaitTime;
        }

        // -- gets called just before thread begins
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute()");
            super.onPreExecute();
        }

        // -- called from the publish progress
        // -- notice that the datatype of the second param gets passed to this method
        @Override
        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate(): " + String.valueOf(values[0]));

        }

        // -- called if the cancel button is pressed
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG, "onCancelled()");
        }

        // -- called as soon as doInBackground method completes
        // -- notice that the third param gets passed to this method
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Log.i(TAG, "onPostExecute(): " + result);

            if (result) {
                notifyUserOfDatabaseFail();
            } else {
                notifyUserOfDatabaseSuccess();
            }
        }

    }

    private void notifyUserOfDatabaseFail() {
        Toast.makeText(this, "Unable to query database! Perhaps no match is recorded yet?", Toast.LENGTH_LONG).show();
    }

    private void notifyUserOfDatabaseSuccess() {
        Toast.makeText(this, "Query to database successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.winner).setVisible(true);
            menu.findItem(R.id.menu_item_developers_info).setVisible(false);
            menu.findItem(R.id.menu_item_sponsors).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.winner) {
            Intent intent = new Intent(SportDetailActivity.this, WinnerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("sport_name", sportName);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        } else if (i == R.id.menu_item_sponsors) {
            Intent intent = new Intent(SportDetailActivity.this, SponsorsActivity.class);
            startActivity(intent);
            return true;
        } else if (i == R.id.menu_item_developers_info) {
            Intent intent = new Intent(SportDetailActivity.this, InfoActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }
}
