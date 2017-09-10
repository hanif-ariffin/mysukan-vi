package tremblay412.com.mysukan.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import tremblay412.com.mysukan.R;

/**
 * Created by akarin on 07/09/17.
 */

public class SportFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "SportFragment";

    private DatabaseReference sportNameReference;
    private RecyclerView sportNameRecycler;
    private SportNameAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, System.lineSeparator() + "ONCREATEVIEW" + System.lineSeparator());
        View rootView = inflater.inflate(R.layout.fragment_sport_list, container, false);

        /**
         // Open Registration Page
         registerButton = (FloatingActionButton) rootView.findViewById(R.id.RegisterButton);
         registerButton.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        Intent myIntent = new Intent(MainPageActivity.this, LoginFragment.class);
        startActivity(myIntent);
        }
        });
         **/
        // Initialize Database
        sportNameReference = FirebaseDatabase.getInstance().getReference()
                .child("games");


        // Initialize
        sportNameRecycler = (RecyclerView) rootView.findViewById(R.id.recyclerview_sport_list);
        sportNameRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    private static class SportViewHolder extends RecyclerView.ViewHolder {

        public TextView sportName;

        public SportViewHolder(View itemView) {
            super(itemView);

            sportName = (TextView) itemView.findViewById(R.id.include_item_sport_name);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Listen for sport name
        mAdapter = new SportNameAdapter(getActivity(), sportNameReference);
        sportNameRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();


        // Clean up comments listener
        mAdapter.cleanupListener();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.d(TAG, "User clicked at View with id:" + id);
    }

    private static class SportNameAdapter extends RecyclerView.Adapter<SportViewHolder> {

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
        public SportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.include_item_sport, parent, false);
            return new SportViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SportViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder to position:" + position);
            String newSportName = listOfSportNames.get(position);
            holder.sportName.setText(newSportName);
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
}
