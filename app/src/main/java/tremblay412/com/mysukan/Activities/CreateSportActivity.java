package tremblay412.com.mysukan.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tremblay412.com.mysukan.R;

/**
 * Created by akarin on 11/09/17.
 */

public class CreateSportActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "CreateSportActivity";

    private EditText sportName;
    private Button createButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sport);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Get layout contents
        sportName = (EditText) findViewById(R.id.activity_create_sport_sport_name);
        createButton = (Button) findViewById(R.id.activity_create_support_create_button);

        // Listeners
        createButton.setOnClickListener(this);

    }

    private void CreateSport() {
        showProgressDialog("Creating sport ...");

        //Disable multiple creation of the same sport
        setEditingEnabled(false);
        String sportName = this.sportName.getText().toString();
        if (TextUtils.isEmpty(sportName)) {
            Toast.makeText(this, "Please enter a string", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "Creating a new sport with name:" + sportName);
            mDatabase.child("sport_list").child(sportName).push();
        }

        finish();
        hideProgressDialog();
    }

    private void setEditingEnabled(boolean enabled) {
        createButton.setEnabled(enabled);
        if (enabled) {
            createButton.setVisibility(View.VISIBLE);
        } else {
            createButton.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_create_support_create_button) {
            CreateSport();
        }
    }
}
