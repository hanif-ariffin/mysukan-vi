package masco.com.mysukan_vi.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.helper.SingleScoreMatchAdapter;
import masco.com.mysukan_vi.models.SingleScoreMatch;

/**
 * Created by Akarin on 9/9/2017.
 */

public class SportDetailActivity extends BaseActivity {

    private static final String TAG = "SportDetailActivity";

    // Enlarged match detail UI hooks
    private TextView textViewteamOneName, textViewteamTwoName, textViewCustomTeamOneName, textViewCustomTeamTwoName, matchScoreOne, matchScoreTwo, matchScoreThree, noMatchFound;
    private ImageView imageViewteamOneImage, imageViewteamTwoImage;


    // Current sport
    String sportName;
    protected InitTask iniTask;

    private DatabaseReference NEW_DATABASE;
    private List<SingleScoreMatch> NEW_DATA;
    private ListView NEW_LIST_VIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        showProgressDialog("Querying NEW_DATABASE...");

        // Get the XML object
        textViewteamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_1);
        textViewteamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_2);
        textViewCustomTeamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_custom_name_1);
        textViewCustomTeamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_custom_name_2);
        imageViewteamOneImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_1);
        imageViewteamTwoImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_2);
        matchScoreOne = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_1);
        matchScoreTwo = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_2);
        matchScoreThree = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_3);
        noMatchFound = (TextView) findViewById(R.id.activity_sport_matches_no_match_found);

        // Bundle received from the Activity creating this Activity
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {

        } else {

            sportName = bundle.getString("sport_name");

            NEW_DATA = new ArrayList<>();
            NEW_LIST_VIEW = (ListView) findViewById(R.id.activity_sport_matches_listview);
            final SingleScoreMatchAdapter scoreAdapter = new SingleScoreMatchAdapter(SportDetailActivity.this, R.layout.include_item_minimized_match_detail, NEW_DATA);
            NEW_LIST_VIEW.setAdapter(scoreAdapter);
            NEW_DATABASE = FirebaseDatabase.getInstance().getReference("games").child(NameManager.UserToDatabase(sportName));


            NEW_DATABASE.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    NEW_DATA.clear();
                    for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                        SingleScoreMatch match = Snapshot.getValue(SingleScoreMatch.class);
                        NEW_DATA.add(match);
                        updateEnlargedMatchDetail(NEW_DATA.get(0).team_1_name, NEW_DATA.get(0).team_2_name, NEW_DATA.get(0).custom_name_1, NEW_DATA.get(0).custom_name_2, new Long[]{NEW_DATA.get(0).team_1_score_1}, new Long[]{NEW_DATA.get(0).team_2_score_1});
                        hideProgressDialog();
                    }
                    Collections.reverse(NEW_DATA);
                    scoreAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            NEW_LIST_VIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    updateEnlargedMatchDetail(NEW_DATA.get(i).team_1_name, NEW_DATA.get(i).team_2_name, NEW_DATA.get(i).custom_name_1, NEW_DATA.get(i).custom_name_2, new Long[]{NEW_DATA.get(i).team_1_score_1}, new Long[]{NEW_DATA.get(i).team_2_score_1});
                }
            });
            getSupportActionBar().setTitle(sportName);

            iniTask = new InitTask();
            iniTask.execute(this);
        }
    }

    public void updateEnlargedMatchDetail(String teamOneName, String teamTwoName, String customNameTeamOne, String customNameTeamTwo, Long[] teamOneScore, Long[] teamTwoScore) {
        textViewteamOneName.setText(teamOneName);
        imageViewteamOneImage.setImageResource(NameManager.getImageId(teamOneName));
        textViewCustomTeamOneName.setText(customNameTeamOne);
        textViewCustomTeamTwoName.setText(customNameTeamTwo);

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
            int sleepTime = 50;
            int maxWaitTime = 50;
            while (isProcessDialogShowing()) {
                try {
                    Log.d(TAG, "Sleeping" + waitCounter);
                    Thread.sleep(sleepTime);
                    publishProgress(waitCounter);
                    if (waitCounter > maxWaitTime) {
                        /**
                         * Fail -> false
                         */
                        return false;
                    }
                    waitCounter++;
                } catch (Exception e) {

                }
            }
            /**
             * Success -> true
             */
            return true;
        }

        // -- gets called just before thread begins
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // -- called from the publish progress
        // -- notice that the datatype of the second param gets passed to this method
        @Override
        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate(values);
        }

        // -- called if the cancel button is pressed
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        // -- called as soon as doInBackground method completes
        // -- notice that the third param gets passed to this method
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result) {
                notifyUserOfDatabaseSuccess();
            } else {
                notifyUserOfDatabaseFail();
                hideProgressDialog();
                noMatchFound.setText("No Match Found!");
                noMatchFound.setVisibility(View.VISIBLE);
            }
        }

    }

    private void notifyUserOfDatabaseFail() {
        Toast.makeText(this, "Unable to query NEW_DATABASE! Perhaps no match is recorded yet?", Toast.LENGTH_SHORT).show();
    }

    private void notifyUserOfDatabaseSuccess() {
        Toast.makeText(this, "Query to NEW_DATABASE successful!", Toast.LENGTH_SHORT).show();
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
}
