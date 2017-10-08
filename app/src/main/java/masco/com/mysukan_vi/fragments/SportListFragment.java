package masco.com.mysukan_vi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.main.SportDetailActivity;
import masco.com.mysukan_vi.helper.NameManager;

/**
 * SportListFragment is the fragment for display the Scoreboard.
 * The design on the UI is NOT dynamic.
 * If you need to add something you have to manually add them here.
 * Considering that there are only a predefined amount of sports, this was argued as the better design than having a ListView.
 * Created by r3xas on 9/25/2017.
 */

public class SportListFragment extends BaseFragment {
    private static final String TAG = "SportListFragment";

    private Button button_basketball;
    private Button button_soccer_male;
    private Button button_badminton_men_doubles;
    private Button button_badminton_women_doubles;
    private Button button_badminton_mixed_doubles;
    private Button button_squash_men_singles;
    private Button button_frisbee;
    private Button button_squash_women_singles;
    private Button button_netball;
    private Button button_dodgeball;
    private Button button_volleyball;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_sportlist, container, false);


        button_soccer_male = (Button) rootView.findViewById(R.id.button_soccer);

        button_soccer_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.SOCCER); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_badminton_men_doubles = (Button) rootView.findViewById(R.id.button_badminton_men_doubles);

        button_badminton_men_doubles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BADMINTON_MEN_DOUBLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_badminton_women_doubles = (Button) rootView.findViewById(R.id.button_badminton_women_doubles);

        button_badminton_women_doubles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BADMINTON_WOMEN_DOUBLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });


        button_badminton_mixed_doubles = (Button) rootView.findViewById(R.id.button_badminton_mixed_doubles);

        button_badminton_mixed_doubles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BADMINTON_MIXED_DOUBLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_squash_men_singles = (Button) rootView.findViewById(R.id.button_squash_men_singles);

        button_squash_men_singles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.SQUASH_MEN_SINGLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_squash_women_singles = (Button) rootView.findViewById(R.id.button_squash_women_singles);

        button_squash_women_singles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.SQUASH_WOMEN_SINGLES); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_frisbee = (Button) rootView.findViewById(R.id.button_frisbee);

        button_frisbee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.FRISBEE); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_dodgeball = (Button) rootView.findViewById(R.id.button_dodgeball);

        button_dodgeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.DODGEBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_netball = (Button) rootView.findViewById(R.id.button_netball);

        button_netball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.NETBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_basketball = (Button) rootView.findViewById(R.id.button_basketball);

        button_basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.BASKETBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_volleyball = (Button) rootView.findViewById(R.id.button_volleyball);

        button_volleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", NameManager.SportCasualNames.VOLLEYBALL); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        return rootView;
    }

}
