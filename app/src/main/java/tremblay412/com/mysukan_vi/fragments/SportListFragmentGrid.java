package tremblay412.com.mysukan.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.activities.SportDetailActivity;

/**
 * Created by r3xas on 9/25/2017.
 */

public class SportListFragmentGrid extends BaseFragment {
    private static final String TAG = "SportListFragmentGrid";

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.sportlist_fragment_grid, container, false);

        button = (Button) rootView.findViewById(R.id.soccer_male);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                    Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("sport_name", "Soccer"); //Your id
                    intent.putExtras(bundle); //Put your id to your next Intent
                    startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.badminton_men_doubles);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Badminton Men Doubles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.badminton_women_doubles);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Badminton Women Doubles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });


       button = (Button) rootView.findViewById(R.id.badminton_mixed_doubles);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Badminton Mixed Doubles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.squash_men_singles);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Squash Men Singles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.squash_women_singles);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Squash Women Singles"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.frisbee_male);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Frisbee"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.dodgeball_female);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Dodgeball"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.netball_female);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
                Intent intent = new Intent(getActivity(), SportDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sport_name", "Netball"); //Your id
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        button = (Button) rootView.findViewById(R.id.basketball_male);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User clicked on " + v.toString());
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
