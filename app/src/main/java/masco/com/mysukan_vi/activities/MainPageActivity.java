package masco.com.mysukan_vi.activities;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;

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
import masco.com.mysukan_vi.annoucement.AnnouncementActivity;
import masco.com.mysukan_vi.fragments.LoginFragment;
import masco.com.mysukan_vi.fragments.MapFragment;
import masco.com.mysukan_vi.fragments.NotificationFragment;
import masco.com.mysukan_vi.fragments.SportListFragmentGrid;

/**
 * A login screen that offers login via email/password.
 */

public class MainPageActivity extends BaseActivity {

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private DatabaseReference database;
    private int counter = 1;
    private List<Announcement> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_with_fragment);


        database = FirebaseDatabase.getInstance().getReference("announcement");
        data = new ArrayList<>();
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
                if (counter ==0){
                    showNotification(data);
                }

                if(counter != 0){
                    counter--;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    new NotificationFragment(),
                    new SportListFragmentGrid(),
                    new MapFragment(),
                    new LoginFragment()
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < getIconTab().length; i++) {
            tabLayout.getTabAt(i).setIcon(getIconTab()[i]);
        }

        //set the scoreboard first page when user open the app
        getSupportActionBar().setTitle("Scoreboard");
        mViewPager.setCurrentItem(1);

        mViewPager
                .addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                    String[] tabsTitles = new String[]{"Announcement","Scoreboard", "Venue", "Admin Login"};

                    @Override
                    public void onPageSelected(int position) {
                        // TODO Auto-generated method stub
                        getSupportActionBar().setTitle(tabsTitles[position]);
                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onPageScrollStateChanged(int pos) {
                        // TODO Auto-generated method stub
                    }
                });


    }

    private int[] getIconTab() {
        int[] iconTab = new int[]{
                R.drawable.notification,
                R.drawable.list_32,
                R.drawable.map_32,
                R.drawable.account_32
                };

        return iconTab;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(List<Announcement> data){
        Intent intent = new Intent(MainPageActivity.this,AnnouncementActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainPageActivity.this,0,intent,0);
        Notification.Builder builder = new Notification.Builder(MainPageActivity.this);

        builder.setContentTitle(data.get(0).getSubject())
                .setContentText(data.get(0).getMessage())
                .setSmallIcon(R.drawable.logo_mysukan)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo_mysukan))
                .setContentIntent(pi)
                .setVibrate(new long[] {Notification.DEFAULT_VIBRATE})
                .setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}