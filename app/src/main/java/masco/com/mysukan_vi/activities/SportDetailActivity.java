package masco.com.mysukan_vi.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.List;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.helper.MatchAdapter;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.helper.SportManager;
import masco.com.mysukan_vi.models.SingleScoreMatch;
import masco.com.mysukan_vi.models.TripleScoreMatch;

/**
 * Activity that will show the selected sport's matches. It consists of two parts: Enlarged match detail and minimized match detail.
 * Enlarged match detail is a more detailed status of the match, it will show the scores, emblems, custom names and the name of the university.
 * Minimized match detail is a ListView that will only display the names of the two university participating in a match.
 * Created by Akarin on 9/9/2017.
 */

public class SportDetailActivity extends BaseActivity {

    private static final String TAG = "SportDetailActivity";
    private static final Long LONG_NULL = -1L;

    // Enlarged match detail UI hooks
    private TextView textViewTeamOneName, textViewTeamTwoName, textViewCustomTeamOneName, textViewCustomTeamTwoName, matchScoreOne, matchScoreTwo, matchScoreThree, noMatchFound;
    private ImageView imageViewTeamOneImage, imageViewTeamTwoImage;

    /**
     * Asynchronous task for the loading screen.
     */
    private InitTask iniTask;

    /**
     * Bundle that will contain information from the caller of this Activity
     */
    private Bundle bundle;

    /**
     * Connection to the database
     */
    private DatabaseReference databaseReference;
    private ValueEventListener databaseReferenceListener;

    /**
     * Variables required for ListView
     */
    private List<TripleScoreMatch> matchList;
    private ListView matchesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        showProgressDialog("Querying database...");

        // Get the XML object
        textViewTeamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_1);
        textViewTeamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_team_2);
        textViewCustomTeamOneName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_custom_name_1);
        textViewCustomTeamTwoName = (TextView) findViewById(R.id.include_item_enlarged_match_detail_text_custom_name_2);
        imageViewTeamOneImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_1);
        imageViewTeamTwoImage = (ImageView) findViewById(R.id.include_item_enlarged_match_detail_image_team_2);
        matchScoreOne = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_1);
        matchScoreTwo = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_2);
        matchScoreThree = (TextView) findViewById(R.id.include_item_enlarged_match_detail_score_3);
        noMatchFound = (TextView) findViewById(R.id.activity_sport_matches_no_match_found);

        // Bundle received from the Activity creating this Activity
        bundle = getIntent().getExtras();

        if (bundle == null) {
            Toast.makeText(this, "Bundle is null. This is an unexpected behavior, report to the developer.", Toast.LENGTH_LONG).show();
        } else {

            final String sportName = bundle.getString("sport_name");

            getSupportActionBar().setTitle(sportName);

            matchesListView = (ListView) findViewById(R.id.activity_sport_matches_listview);
            databaseReference = FirebaseDatabase.getInstance().getReference("games").child(NameManager.UserToDatabase(sportName));

            /**
             * We are going to use the same adapter for both SingleScoreMatch and TripleScoreMatch types of matches for easier and overall a lot more readable code.
             * This however requires us to convert from SingleScoreMatch => TripleScoreMatch. Specifically, the conversion is as follows:
             *
             *  SingleScoreMatch => TripleScoreMatch
             *  match_date => match_date
             *  id => id
             *  team_1_name         => team_1_name
             *  team_2_name         => team_2_name
             *  team_1_custom_name  => team_1_custom_name
             *  team_2_custom_name  => team_2_custom_namem
             *  team_1_score_1      => team_1_score_1
             *  team_2_score_1      => team_2_score_1
             *  LONG_NULL           => team_1_score_2
             *  LONG_NULL           => team_2_score_2
             *  LONG_NULL           => team_1_score_3
             *  LONG_NULL           => team_2_score_3
             */
            matchList = new ArrayList<>();
            final MatchAdapter scoreAdapter = new MatchAdapter(SportDetailActivity.this, R.layout.include_item_minimized_match_detail, matchList);
            matchesListView.setAdapter(scoreAdapter);

            /**
             * Add listener to the realtime database
             */
            databaseReferenceListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    matchList.clear();
                    for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                        TripleScoreMatch match;

                        /**
                         * Check if the input is actually a SingleScoreMatch or TripleScoreMatch
                         */
                        if (SportManager.isSingleScore(sportName)) {
                            SingleScoreMatch tmp = Snapshot.getValue(SingleScoreMatch.class);
                            /**
                             * The conversion from SingleScoreMatch => TripleScoreMatch
                             */
                            match = new TripleScoreMatch(
                                    tmp.match_date,
                                    tmp.id,
                                    tmp.team_1_name, tmp.team_2_name, //String team_1_name, String team_2_name,
                                    tmp.custom_name_1, tmp.custom_name_2, //String custom_name_1, String custom_name_2,
                                    tmp.team_1_score_1, tmp.team_2_score_1, //Long team_1_score_1, Long team_2_score_1,
                                    LONG_NULL, LONG_NULL, //Long team_1_score_2, Long team_2_score_2,
                                    LONG_NULL, LONG_NULL//Long team_1_score_3, Long team_2_score_3
                            );
                        } else {
                            match = Snapshot.getValue(TripleScoreMatch.class);
                        }
                        matchList.add(match);

                        /**
                         * Only update the enlarged match detail when it is the first item received from the database.
                         */
                        if (matchList.size() == 1) {
                            updateEnlargedMatchDetail(
                                    matchList.get(0).team_1_name, matchList.get(0).team_2_name,
                                    matchList.get(0).custom_name_1, matchList.get(0).custom_name_2,
                                    new Long[]{
                                            matchList.get(0).team_1_score_1,
                                            matchList.get(0).team_1_score_2,
                                            matchList.get(0).team_1_score_3,
                                    },
                                    new Long[]{
                                            matchList.get(0).team_2_score_1,
                                            matchList.get(0).team_2_score_2,
                                            matchList.get(0).team_2_score_3,
                                    });
                        }
                        hideProgressDialog();
                    }
                    scoreAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Query to database is cancelled.", Toast.LENGTH_SHORT).show();
                }
            };
            databaseReference.addValueEventListener(databaseReferenceListener);


            matchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    updateEnlargedMatchDetail(
                            matchList.get(i).team_1_name, matchList.get(i).team_2_name,
                            matchList.get(i).custom_name_1, matchList.get(i).custom_name_2,
                            new Long[]{
                                    matchList.get(i).team_1_score_1,
                                    matchList.get(i).team_1_score_2,
                                    matchList.get(i).team_1_score_3
                            },
                            new Long[]{
                                    matchList.get(i).team_2_score_1,
                                    matchList.get(i).team_2_score_2,
                                    matchList.get(i).team_2_score_3
                            });
                }
            });

            iniTask = new InitTask();
            iniTask.execute(this);
        }
    }

    /**
     * Update the enlarged match detail (The display above the ListView of matches in this activity.
     *
     * @param teamOneName       -- university name of team one
     * @param teamTwoName       -- university name of team two
     * @param customNameTeamOne -- custom name of team one
     * @param customNameTeamTwo -- custom name of team two
     * @param teamOneScore      -- Long array of team one's score
     * @param teamTwoScore      -- Long array of team two's score
     */
    public void updateEnlargedMatchDetail(String teamOneName, String teamTwoName, String customNameTeamOne, String customNameTeamTwo, Long[] teamOneScore, Long[] teamTwoScore) {

        /**
         * Set the texts for teams name, custom names and images which are properties common between SingleScoreMatch and TripleScoreMatch.
         */
        textViewTeamOneName.setText(teamOneName);
        imageViewTeamOneImage.setImageResource(NameManager.getImageId(teamOneName));
        textViewCustomTeamOneName.setText(customNameTeamOne);
        textViewCustomTeamTwoName.setText(customNameTeamTwo);
        textViewTeamTwoName.setText(teamTwoName);
        imageViewTeamTwoImage.setImageResource(NameManager.getImageId(teamTwoName));

        /**
         * If the first score is LONG_NULL ==> Its a match with a single scoring (SingleScoreMatch)
         * Else ==> Its a match with three score (TripleScoreMatch)
         */
        if (teamOneScore[1].equals(LONG_NULL) && teamTwoScore[1].equals(LONG_NULL)) {
            matchScoreTwo.setText(teamOneScore[0] + " - " + teamTwoScore[0]);
            matchScoreTwo.setTextSize(30L);

            matchScoreOne.setText("");
            matchScoreThree.setText("");
        } else {
            matchScoreOne.setText(teamOneScore[0] + " - " + teamTwoScore[0]);
            matchScoreTwo.setText(teamOneScore[1] + " - " + teamTwoScore[1]);
            matchScoreThree.setText(teamOneScore[2] + " - " + teamTwoScore[2]);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
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

    /**
     * Notify user of the failure to connect to the database.
     */
    private void notifyUserOfDatabaseFail() {
        Toast.makeText(this, "Unable to query database! Perhaps no match is recorded yet?", Toast.LENGTH_SHORT).show();
    }

    /**
     * Notify user of the success to connect to the database.
     */
    private void notifyUserOfDatabaseSuccess() {
        Toast.makeText(this, "Query to database successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.winner).setVisible(true);
            menu.findItem(R.id.menu_item_sponsors).setVisible(false);
            menu.findItem(R.id.menu_item_developer_awesome).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.winner) {
            Intent intent = new Intent(SportDetailActivity.this, WinnerActivity.class);
            intent.putExtras(bundle); //insert the bundle that this Activity already received.
            startActivity(intent);
            return true;
        } else if (i == R.id.menu_item_sponsors) {
            Intent intent = new Intent(SportDetailActivity.this, SponsorsActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
