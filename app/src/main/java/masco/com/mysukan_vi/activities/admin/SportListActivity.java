package masco.com.mysukan_vi.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.main.SportDetailActivity;
import masco.com.mysukan_vi.activities.other.BaseActivity;
import masco.com.mysukan_vi.fragments.BaseFragment;
import masco.com.mysukan_vi.helper.NameManager;

/**
 * SportListFragment is the fragment for display the Scoreboard.
 * The design on the UI is NOT dynamic.
 * If you need to add something you have to manually add them here.
 * Considering that there are only a predefined amount of sports, this was argued as the better design than having a ListView.
 * Created by r3xas on 9/25/2017.
 */

public class SportListActivity extends BaseActivity {
    private static final String TAG = "SportListFragment";

    private Button basketballButton;
    private Button soccerMaleButton;
    private Button badmintonMenDoublesButton;
    private Button badmintonWomenDoublesButton;
    private Button badmintonMixedDoublesButton;
    private Button squashMenSinglesButton;
    private Button frisbeeButton;
    private Button squashWomenSinglesButton;
    private Button netballButton;
    private Button dodgeballButton;
    private Button volleyballButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sportlist);

        soccerMaleButton = (Button) findViewById(R.id.button_soccer);

        soccerMaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.SOCCER); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        badmintonMenDoublesButton = (Button) findViewById(R.id.button_badminton_men_doubles);

        badmintonMenDoublesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BADMINTON_MEN_DOUBLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        badmintonWomenDoublesButton = (Button) findViewById(R.id.button_badminton_women_doubles);

        badmintonWomenDoublesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BADMINTON_WOMEN_DOUBLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });


        badmintonMixedDoublesButton = (Button) findViewById(R.id.button_badminton_mixed_doubles);

        badmintonMixedDoublesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BADMINTON_MIXED_DOUBLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        squashMenSinglesButton = (Button) findViewById(R.id.button_squash_men_singles);

        squashMenSinglesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.SQUASH_MEN_SINGLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        squashWomenSinglesButton = (Button) findViewById(R.id.button_squash_women_singles);

        squashWomenSinglesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.SQUASH_WOMEN_SINGLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        frisbeeButton = (Button) findViewById(R.id.button_frisbee);

        frisbeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.FRISBEE); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        dodgeballButton = (Button) findViewById(R.id.button_dodgeball);

        dodgeballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.DODGEBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        netballButton = (Button) findViewById(R.id.button_netball);

        netballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.NETBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        basketballButton = (Button) findViewById(R.id.button_basketball);

        basketballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BASKETBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        volleyballButton = (Button) findViewById(R.id.button_volleyball);

        volleyballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.VOLLEYBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });
    }

}
