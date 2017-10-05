package masco.com.mysukan_vi.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    private static int index;

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
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AnnouncementActivity.this);
                builder1.setCancelable(true);
                builder1.setMessage(data.get(i).getMessage());
                //AlertDialog alert11 = builder1.create();
                //alert11.setTitle("Subject : " +data.get(i).getSubject());
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference("announcement").child(data.get(index).getId()).removeValue();
                    }
                });
                builder1.show();

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
            menu.findItem(R.id.menu_item_developer_awesome).setVisible(false);
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
