package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.models.WinnerMatch;

/**
 * Created by User on 2017-09-28.
 */

public class WinnerActivity extends BaseActivity {

    private FirebaseListAdapter<WinnerMatch> mAdapter;
    private ImageView imageViewFirstPlace, imageViewSecondPlace, imageViewThirdPlace;
    private TextView textViewFirstPlace, textViewSecondPlace, textViewThirdPlace;
    // Current sport
    String sportName;
    private DatabaseReference database;
    private static WinnerMatch iWinnerMatch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        imageViewFirstPlace = (ImageView) findViewById(R.id.image_first_place);
        imageViewSecondPlace = (ImageView) findViewById(R.id.image_second_place);
        imageViewThirdPlace = (ImageView) findViewById(R.id.image_third_place);
        textViewFirstPlace = (TextView) findViewById(R.id.text_first_place);
        textViewSecondPlace = (TextView) findViewById(R.id.text_second_place);
        textViewThirdPlace = (TextView) findViewById(R.id.text_third_place);

        // Bundle received from the Activity creating this Activity
        Bundle bundle = getIntent().getExtras();
        sportName = bundle.getString("sport_name");

        getSupportActionBar().setTitle(sportName + " Champion");

        database = FirebaseDatabase.getInstance().getReference("ranking").child(NameManager.UserToDatabase(sportName));

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> lWinner = new ArrayList<>();
                for (DataSnapshot sportSnapshot : dataSnapshot.getChildren()) {
                    String lName = sportSnapshot.getValue(String.class);
                    lWinner.add(lName);
                }

                if (lWinner.size() == 3) {
                    textViewFirstPlace.setText(lWinner.get(0));
                    textViewSecondPlace.setText(lWinner.get(1));
                    textViewThirdPlace.setText(lWinner.get(2));
                    imageViewFirstPlace.setImageResource(NameManager.getImageId(lWinner.get(0)));
                    imageViewSecondPlace.setImageResource(NameManager.getImageId(lWinner.get(1)));
                    imageViewThirdPlace.setImageResource(NameManager.getImageId(lWinner.get(2)));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.winner).setVisible(false);
            menu.findItem(R.id.menu_item_sponsors).setVisible(false);
            menu.findItem(R.id.menu_item_developers_info).setVisible(false);
        }
        return true;
    }
}