package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.annoucement.Announcement;

public class AddAnnouncementActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText subject, message;
    private Button addAnnoucement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        database = FirebaseDatabase.getInstance().getReference("announcement");
        subject = (EditText) findViewById(R.id.subjectText);
        message = (EditText) findViewById(R.id.messageText);


        final Long tsLong = System.currentTimeMillis() / 1000;

        addAnnoucement = (Button) findViewById(R.id.addAnnoucement);

        addAnnoucement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = database.push().getKey();

                Announcement mAnnouncement = new Announcement(id, subject.getText().toString(), message.getText().toString(), tsLong);
                database.child(id).setValue(mAnnouncement);
                Toast.makeText(AddAnnouncementActivity.this, "Subject : " + subject.getText().toString() + " announced !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}