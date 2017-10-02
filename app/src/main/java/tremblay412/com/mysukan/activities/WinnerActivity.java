package tremblay412.com.mysukan.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.helper.NameManager;

/**
 * Created by User on 2017-09-28.
 */

public class WinnerActivity extends BaseActivity {

    private ImageView imageViewFirstPlace, imageViewSecondPlace, imageViewThirdPlace;
    private TextView textViewFirstPlace, textViewSecondPlace, textViewThirdPlace;
    // Current sport
    String sportName;
    private DatabaseReference database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        imageViewFirstPlace = (ImageView) findViewById(R.id.firstplace);
        imageViewSecondPlace = (ImageView) findViewById(R.id.secondplace);
        imageViewThirdPlace = (ImageView) findViewById(R.id.thirdplace);
        textViewFirstPlace = (TextView) findViewById(R.id.textfirstplace);
        textViewSecondPlace = (TextView) findViewById(R.id.textsecondplace);
        textViewThirdPlace = (TextView) findViewById(R.id.textthirdplace);

        // Bundle received from the Activity creating this Activity
        Bundle bundle = getIntent().getExtras();
        sportName = bundle.getString("sport_name");

        getSupportActionBar().setTitle(sportName + " Champions");

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
                } else {
                    Toast.makeText(getApplicationContext(), "Not all 3 winners are announced yet", Toast.LENGTH_LONG);
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