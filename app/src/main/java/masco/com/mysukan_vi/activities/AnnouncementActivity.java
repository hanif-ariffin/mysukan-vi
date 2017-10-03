package masco.com.mysukan_vi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.annoucement.Announcement;
import masco.com.mysukan_vi.helper.AnnouncementAdapter;

public class AnnouncementActivity extends AppCompatActivity {

    private DatabaseReference database;
    private List<Announcement> data;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        data = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listAnnoucement);
        final AnnouncementAdapter lArrayAdapter = new AnnouncementAdapter(AnnouncementActivity.this, R.layout.include_item_annoucement, data);
        listView.setAdapter(lArrayAdapter);
        database = FirebaseDatabase.getInstance().getReference("announcement");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    Announcement announcement = Snapshot.getValue(Announcement.class);
                    data.add(announcement);

                }
                Collections.reverse(data);
                lArrayAdapter.notifyDataSetChanged();

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
            menu.findItem(R.id.menu_item_developers_info).setVisible(false);
            menu.findItem(R.id.menu_item_sponsors).setVisible(false);
            menu.findItem(R.id.menu_item_overrall_score).setVisible(false);
            menu.findItem(R.id.add_button).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.add_button) {
            Intent intent = new Intent(AnnouncementActivity.this, AddAnnouncementActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
