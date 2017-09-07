package tremblay412.com.mysukan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TableLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class MainPageActivity extends AppCompatActivity {

    // UI references.
    private ListView iListGames;
    private ViewGroup iMainPage;
    private FloatingActionButton RegisterButton;

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_with_fragment);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    new SportFragment(),
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

        int[] iconTab = new int[]{
                R.drawable.list_32,
                R.drawable.account_32
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        for( int i = 0; i< iconTab.length;i++){
            tabLayout.getTabAt(i).setIcon(iconTab[i]);
        }
    }


}

