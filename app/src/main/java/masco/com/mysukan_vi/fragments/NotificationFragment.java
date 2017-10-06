package masco.com.mysukan_vi.fragments;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import masco.com.mysukan_vi.helper.AnnouncementAdapter;
import masco.com.mysukan_vi.models.Announcement;

/**
 * Created by Haziq on 2017-10-03.
 */

public class NotificationFragment extends BaseFragment {

    private DatabaseReference database;
    private List<Announcement> data;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_announcement, container, false);

        data = new ArrayList<>();
        listView = (ListView) rootView.findViewById(R.id.listAnnoucement);
        final AnnouncementAdapter lArrayAdapter = new AnnouncementAdapter(getContext(), R.layout.include_item_annoucement, data);
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

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setCancelable(true);
                builder1.setMessage("Message : " + data.get(i).getMessage());
                AlertDialog alert11 = builder1.create();
                alert11.setTitle("Subject : " + data.get(i).getSubject());
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                alert11.show();
            }
        });


        return rootView;
    }
}