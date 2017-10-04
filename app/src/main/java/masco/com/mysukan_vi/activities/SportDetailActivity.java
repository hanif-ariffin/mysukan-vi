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
import masco.com.mysukan_vi.helper.SportManager;
import masco.com.mysukan_vi.helper.TripleScoreMatchAdapter;
import masco.com.mysukan_vi.models.SingleScoreMatch;
import masco.com.mysukan_vi.models.TripleScoreMatch;

/**
 * Created by Akarin on 9/9/2017.
 */

public class SportDetailActivity extends BaseActivity {

    private static final String TAG = "SportDetailActivity";
    private static final Long LONG_NULL = -1L;
    // Enlarged match detail UI hooks
    private TextView textViewteamOneName, textViewteamTwoName, textViewCustomTeamOneName, textViewCustomTeamTwoName, matchScoreOne, matchScoreTwo, matchScoreThree, noMatchFound;
    private ImageView imageViewteamOneImage, imageViewteamTwoImage;


    // Current sport
    String sportName;
    protected InitTask iniTask;

    private DatabaseReference databaseReference;
    private List<TripleScoreMatch> tripleScoreList;
    private ListView matchesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        showProgressDialog("Querying databaseReference...");

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

            matchesListView = (ListView) findViewById(R.id.activity_sport_matches_listview);
            databaseReference = FirebaseDatabase.getInstance().getReference("games").child(NameManager.UserToDatabase(sportName));

            if (SportManager.isSingleScore(sportName)) {
                tripleScoreList = new ArrayList<>();
                final TripleScoreMatchAdapter scoreAdapter = new TripleScoreMatchAdapter(SportDetailActivity.this, R.layout.include_item_minimized_match_detail, tripleScoreList);
                matchesListView.setAdapter(scoreAdapter);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tripleScoreList.clear();
                        for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                            SingleScoreMatch match = Snapshot.getValue(SingleScoreMatch.class);
                            tripleScoreList.add(new TripleScoreMatch(
                                    match.match_date,
                                    match.id,
                                    match.team_1_name, match.team_2_name, //String team_1_name, String team_2_name,
                                    match.custom_name_1, match.custom_name_2, //String custom_name_1, String custom_name_2,
                                    match.team_1_score_1, match.team_2_score_1, //Long team_1_score_1, Long team_2_score_1,
                                    LONG_NULL, LONG_NULL, //Long team_1_score_2, Long team_2_score_2,
                                    LONG_NULL, LONG_NULL//Long team_1_score_3, Long team_2_score_3
                            ));
                            updateEnlargedMatchDetail(
                                    tripleScoreList.get(0).team_1_name, tripleScoreList.get(0).team_2_name,
                                    tripleScoreList.get(0).custom_name_1, tripleScoreList.get(0).custom_name_2,
                                    new Long[]{
                                            tripleScoreList.get(0).team_1_score_1,
                                            LONG_NULL,
                                            LONG_NULL
                                    },
                                    new Long[]{
                                            tripleScoreList.get(0).team_2_score_1,
                                            LONG_NULL,
                                            LONG_NULL
                                    });
                            hideProgressDialog();
                        }
                        scoreAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                matchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        updateEnlargedMatchDetail(
                                tripleScoreList.get(i).team_1_name, tripleScoreList.get(i).team_2_name,
                                tripleScoreList.get(i).custom_name_1, tripleScoreList.get(i).custom_name_2,
                                new Long[]{
                                        tripleScoreList.get(i).team_1_score_1,
                                        LONG_NULL,
                                        LONG_NULL
                                },
                                new Long[]{
                                        tripleScoreList.get(i).team_1_score_1,
                                        LONG_NULL,
                                        LONG_NULL
                                });
                    }
                });
            } else {
                tripleScoreList = new ArrayList<>();
                final TripleScoreMatchAdapter scoreAdapter = new TripleScoreMatchAdapter(SportDetailActivity.this, R.layout.include_item_minimized_match_detail, tripleScoreList);
                matchesListView.setAdapter(scoreAdapter);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tripleScoreList.clear();
                        for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                            TripleScoreMatch match = Snapshot.getValue(TripleScoreMatch.class);
                            tripleScoreList.add(match);
                            updateEnlargedMatchDetail(
                                    tripleScoreList.get(0).team_1_name, tripleScoreList.get(0).team_2_name,
                                    tripleScoreList.get(0).custom_name_1, tripleScoreList.get(0).custom_name_2,
                                    new Long[]{
                                            tripleScoreList.get(0).team_1_score_1,
                                            tripleScoreList.get(0).team_1_score_2,
                                            tripleScoreList.get(0).team_1_score_3
                                    },
                                    new Long[]{
                                            tripleScoreList.get(0).team_2_score_1,
                                            tripleScoreList.get(0).team_2_score_2,
                                            tripleScoreList.get(0).team_2_score_3
                                    });
                            hideProgressDialog();
                        }
                        scoreAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                matchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        updateEnlargedMatchDetail(
                                tripleScoreList.get(i).team_1_name, tripleScoreList.get(i).team_2_name,
                                tripleScoreList.get(i).custom_name_1, tripleScoreList.get(i).custom_name_2,
                                new Long[]{
                                        tripleScoreList.get(i).team_1_score_1,
                                        tripleScoreList.get(i).team_1_score_2,
                                        tripleScoreList.get(i).team_1_score_3
                                },
                                new Long[]{
                                        tripleScoreList.get(i).team_2_score_1,
                                        tripleScoreList.get(i).team_2_score_2,
                                        tripleScoreList.get(i).team_2_score_3
                                });
                    }
                });
            }
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

        /**
         * If the first score is LONG_NULL ==> Its a match with a single scoring
         * Else ==> Its a match with three score
         */
        if (teamOneScore[0].equals(LONG_NULL) && teamTwoScore[0].equals(LONG_NULL)) {
            matchScoreTwo.setText(teamOneScore[1] + " - " + teamTwoScore[1]);
            matchScoreTwo.setTextSize(30L);

            matchScoreOne.setText("");
            matchScoreThree.setText("");
        } else {
            matchScoreOne.setText(teamOneScore[0] + " - " + teamTwoScore[0]);
            matchScoreTwo.setText(teamOneScore[1] + " - " + teamTwoScore[1]);
            matchScoreThree.setText(teamOneScore[2] + " - " + teamTwoScore[2]);
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
        Toast.makeText(this, "Unable to query databaseReference! Perhaps no match is recorded yet?", Toast.LENGTH_SHORT).show();
    }

    private void notifyUserOfDatabaseSuccess() {
        Toast.makeText(this, "Query to databaseReference successful!", Toast.LENGTH_SHORT).show();
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
