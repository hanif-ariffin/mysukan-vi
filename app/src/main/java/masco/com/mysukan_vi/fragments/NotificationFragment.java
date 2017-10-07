package masco.com.mysukan_vi.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

    private DatabaseReference databaseReference;
    private List<Announcement> announcementArrayList;
    private ListView announcementListView;

    public static final String TAG = "NotificationFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_mainpage_announcement, container, false);

        announcementArrayList = new ArrayList<>();
        announcementListView = (ListView) rootView.findViewById(R.id.activity_mainpage_announcement_listview);
        final AnnouncementAdapter announcementListViewAdapter = new AnnouncementAdapter(getContext(), R.layout.include_item_annoucement, announcementArrayList);
        announcementListView.setAdapter(announcementListViewAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("announcement");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                announcementArrayList.clear();
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    Announcement announcement = Snapshot.getValue(Announcement.class);
                    announcementArrayList.add(announcement);

                    Log.wtf(TAG, "key + announcement" + announcement.getId());
                }
                Collections.reverse(announcementArrayList);
                announcementListViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        announcementListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setCancelable(true);
                builder1.setMessage("Message : " + announcementArrayList.get(i).getMessage());
                AlertDialog alert11 = builder1.create();
                alert11.setTitle("Subject : " + announcementArrayList.get(i).getSubject());
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