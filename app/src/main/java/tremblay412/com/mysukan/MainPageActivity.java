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
                    new LoginFragment(),
                    new AdminFragment()
            };
            private final String[] mFragmentNames = new String[]{
                    "Login",
                    "Admin"
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        /**
         *
         * OLD ACTIVITY
         super.onCreate(savedInstanceState);
         setContentView(R.layout.main_page);

         // Open Registration Page
         RegisterButton = (FloatingActionButton) findViewById(R.id.RegisterButton);
         RegisterButton.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        Intent myIntent = new Intent(MainPageActivity.this, LoginFragment.class);
        startActivity(myIntent);
        }
        });


         //mMainPage = findViewById(R.layout.main_page);
         iMainPage = (ViewGroup) findViewById(R.id.main_page);

         iListGames = (ListView) findViewById(R.id.listview1);

         ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getGames());
         iListGames.setAdapter(lArrayAdapter);
         **/
    }

    private List<String> getGames() {
        List<String> lGames = new ArrayList<>();
        lGames.add("Volleyball");
        lGames.add("Telematch (F)");
        lGames.add("Dodgeball");
        lGames.add("Frisbee");
        lGames.add("Badminton");
        lGames.add("Squash");
        lGames.add("Volleyball (F)");
        lGames.add("Netball");
        lGames.add("Basketball");
        lGames.add("Football");
        lGames.add("Fifa (E-Games)");
        lGames.add("Rocket League (E-games)");
        return lGames;
    }
}

