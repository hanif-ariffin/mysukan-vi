package tremblay412.com.mysukan.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.helper.NameManager;
import tremblay412.com.mysukan.models.Tuple;

/**
 * Created by Akarin on 10/1/2017.
 */

public class OverallWinnerActivity extends BaseActivity {


    private FirebaseDatabase databaseReference;
    private HashMap<String, Tuple<String, Integer>> universityScoreMap;
    PriorityQueue<String> universityRanking;
    private static final String TAG = "OverrallWinnerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_matches);

        getSupportActionBar().setTitle("Overrall Winner");

        // Initialized Firebase reference
        databaseReference = FirebaseDatabase.getInstance();

        universityRanking = new PriorityQueue<>();
        universityScoreMap = new HashMap<>();

        String[] sports = NameManager.SportTypeSafeNames.getSportTypeSafeNames();
        ArrayList<DatabaseReference> ref = new ArrayList<>();
        for (int a = 0; a < sports.length; a++) {
            ref.add(databaseReference.getReference());
            ref.get(a).
                    child("ranking").
                    child(sports[a]).
                    addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot sportSnapshot : dataSnapshot.getChildren()) {
                                String names = sportSnapshot.getValue(String.class);
                                universityScoreMap.get(names).second++;
                                updateListView(names);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Database query have been cancelled.", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private void updateListView(String names) {
        Log.i(TAG, universityScoreMap.get(names).toString());
    }

}
