package tremblay412.com.mysukan;

import android.app.Fragment;
import android.content.Intent;
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
        newScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(),NewScoreFragment.class);
                startActivity(myIntent);
            }
        });

        return view;
    }
}
