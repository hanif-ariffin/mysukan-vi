package tremblay412.com.mysukan.activities;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;


import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.fragments.LoginFragment;
import tremblay412.com.mysukan.fragments.MapFragment;
import tremblay412.com.mysukan.fragments.SportFragment;

/**
 * A login screen that offers login via email/password.
 */
public class MainPageActivity extends AppCompatActivity  {

    // UI references.

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_with_fragment);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    new SportFragment(),
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
        for( int i = 0; i< getIconTab().length;i++){
            tabLayout.getTabAt(i).setIcon(getIconTab()[i]);
        }
    }

    private int[] getIconTab(){
        int[] iconTab = new int[]{
                R.drawable.list_32,
                R.drawable.map_32,
                R.drawable.account_32};

        return iconTab;
    }


}

