package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import masco.com.mysukan_vi.R;

/**
 * Created by User on 2017-10-04.
 */

public class DeveloperAwesomeActivity extends BaseActivity {

    private EditText feedBack;
    private Button submit;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_awesome);

        getSupportActionBar().setTitle("Developer Awesome");

        database = FirebaseDatabase.getInstance().getReference("feedback_android");
        submit = (Button) findViewById(R.id.submit_feedback);
        feedBack = (EditText) findViewById(R.id.feedback_field);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = database.push().getKey();

                if (feedBack.getText().toString().length() <= 0) {

                    Toast.makeText(DeveloperAwesomeActivity.this, "Please don't spam us with empty message ;)!", Toast.LENGTH_LONG).show();

                } else {

                    String lFeedback = new String(feedBack.getText().toString());
                    database.child(id).setValue(lFeedback);
                    Toast.makeText(DeveloperAwesomeActivity.this, "Thank you for your feedback!", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

}
