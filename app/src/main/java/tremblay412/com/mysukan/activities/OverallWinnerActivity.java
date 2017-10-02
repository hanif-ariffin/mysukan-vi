package tremblay412.com.mysukan.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private static final String TAG = "OverrallWinnerActivity";
    private HashMap<String, Tuple<String, Integer>> universityScoreMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overrall_winner);

        getSupportActionBar().setTitle("Overrall Winner");

        // Initialized Firebase reference
        databaseReference = FirebaseDatabase.getInstance();


        /**
         overralWinnerListView = (ListView) findViewById(R.id.activity_overrall_winner_listview);
         overralWinnerListView.setFastScrollEnabled(true);
         overrallWinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, );



         **/
        universityScoreMap = new HashMap<>();
        ArrayList<DatabaseReference> ref = new ArrayList<>();
        String[] sports = NameManager.SportTypeSafeNames.getSportTypeSafeNames();
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
                                updatePriorityQueue(names);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Database query have been cancelled.", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private void updatePriorityQueue(String names) {
        if (universityScoreMap.containsKey(names)) {
            universityScoreMap.get(names).second++;
            Log.i(TAG, "Incremented item in map:" + universityScoreMap.get(names).toString());
        } else {
            universityScoreMap.put(names, new Tuple<String, Integer>(names, 0));
            Log.i(TAG, "Inserted a new item:" + names + " into the map");
        }
    }

}
