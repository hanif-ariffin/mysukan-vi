package tremblay412.com.mysukan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarin on 11/09/17.
 */

public class SportNameAdapter extends RecyclerView.Adapter<MatchDetailViewHolder> {

    private static String TAG = "SportNameAdapter";

    private Context context;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private List<String> listOfSportNames = new ArrayList<>();

    public SportNameAdapter(final Context context, DatabaseReference databaseReference) {
        this.context = context;
        this.databaseReference = databaseReference;

        // Create child event listener
        // [START child_event_listener_recycler]
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                /**
                 * This is where you add the items retrieved from Firebase into the array
                 */
                Log.d(TAG, "Data retrieved" + dataSnapshot.getValue().toString());

                // [START_EXCLUDE]
                listOfSportNames.add(dataSnapshot.getKey());
                notifyItemInserted(listOfSportNames.size() - 1);
                // [END_EXCLUDE]
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                String movedComment = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
                Toast.makeText(SportNameAdapter.this.context, "Failed to load sport names.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addChildEventListener(childEventListener);
        // [END child_event_listener_recycler]

        // Store reference to listener so it can be removed on app stop
        this.childEventListener = childEventListener;
    }

    @Override
    public MatchDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.include_item_sport, parent, false);
        return new MatchDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchDetailViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder to position:" + position);
        String newSportName = listOfSportNames.get(position);
    }

    @Override
    public int getItemCount() {
        return listOfSportNames.size();
    }

    public void cleanupListener() {
        if (childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
        }
    }

}