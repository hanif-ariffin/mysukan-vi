package masco.com.mysukan_vi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.SportDetailActivity;

/**
 * Created by r3xas on 9/25/2017.
 */

public class SportListFragmentGrid extends BaseFragment {
    private static final String TAG = "SportListFragmentGrid";

    private Button button;
    private Button button_basketball_male;
    private Button button_soccer_male;
    private Button button_badminton_men_doubles;
    private Button button_badminton_women_doubles;
    private Button button_badminton_mixed_doubles;
    private Button button_squash_men_singles;
    private Button button_frisbee_male;
    private Button button_squash_women_singles;
    private Button button_netball_female;
    private Button button_dodgeball_female;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.sportlist_fragment_grid, container, false);

        button_soccer_male = (Button) rootView.findViewById(R.id.soccer_male);

        button_soccer_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Soccer"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_badminton_men_doubles = (Button) rootView.findViewById(R.id.badminton_men_doubles);

        button_badminton_men_doubles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Badminton Men Doubles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_badminton_women_doubles = (Button) rootView.findViewById(R.id.badminton_women_doubles);

        button_badminton_women_doubles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Badminton Women Doubles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });


        button_badminton_mixed_doubles = (Button) rootView.findViewById(R.id.badminton_mixed_doubles);

        button_badminton_mixed_doubles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Badminton Mixed Doubles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_squash_men_singles = (Button) rootView.findViewById(R.id.squash_men_singles);

        button_squash_men_singles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Squash Men Singles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_squash_women_singles = (Button) rootView.findViewById(R.id.squash_women_singles);

        button_squash_women_singles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Squash Women Singles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_frisbee_male = (Button) rootView.findViewById(R.id.frisbee_male);

        button_frisbee_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Frisbee"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_dodgeball_female = (Button) rootView.findViewById(R.id.dodgeball_female);

        button_dodgeball_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Dodgeball"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_netball_female = (Button) rootView.findViewById(R.id.netball_female);

        button_netball_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Netball"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button_basketball_male = (Button) rootView.findViewById(R.id.basketball_male);

        button_basketball_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Basketball"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        return rootView;
    }
}
