package masco.com.mysukan_vi.activities;

/**
 * Created by User on 2017-09-20.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.fragments.adminarea.NewScoreFragment;
import masco.com.mysukan_vi.fragments.adminarea.UpdateScoreFragment;

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
                .addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.menu_item_developers_info).setVisible(false);
            menu.findItem(R.id.menu_item_sponsors).setVisible(false);
            menu.findItem(R.id.menu_item_overall_score).setVisible(false);
        }
        return true;
    }

}