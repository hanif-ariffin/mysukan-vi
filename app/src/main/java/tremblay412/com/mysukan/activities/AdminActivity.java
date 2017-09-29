package tremblay412.com.mysukan.activities;

/**
 * Created by User on 2017-09-20.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.fragments.adminarea.NewScoreFragment;
import tremblay412.com.mysukan.fragments.adminarea.UpdateScoreFragment;

/**
 * A login screen that offers login via email/password.
 */

public class AdminActivity extends BaseActivity {

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_page_fragment);

        getSupportActionBar().setTitle("New Score");


        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    new NewScoreFragment(),
                    new UpdateScoreFragment()
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
        tabLayout.getTabAt(0).setText("New Score");
        tabLayout.getTabAt(1).setText("Update Score");

        mViewPager
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                    String[] tabsTitles = new String[]{"New Score", "Update Score"};

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
}