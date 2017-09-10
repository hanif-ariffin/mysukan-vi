package tremblay412.com.mysukan;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdminFragment extends BaseFragment{
    Button newScore, updateScore;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.activity_admin,container,false);
        newScore = (Button)view.findViewById(R.id.BTN_newscore);
        updateScore = (Button)view.findViewById(R.id.BTN_updatescore);
        newScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newScore.setVisibility(View.INVISIBLE);
                updateScore.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                android.support.v4.app.Fragment fr = new NewScoreFragment();
                fragmentTransaction.replace(R.id.activity_admin,fr,fr.toString());
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
