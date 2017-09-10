package tremblay412.com.mysukan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class NewScoreFragment extends BaseFragment {


    // UI references.
    private Button btnProceed;
    private Spinner sportMenu, typeMenu;
    ArrayAdapter<CharSequence> sportAdapter, typeAdapter;
    Bundle args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_new_score_fragment, container, false);


        //Spinner menu for sport

        sportMenu = (Spinner)rootView.findViewById(R.id.sport_spinner);
        typeMenu = (Spinner)rootView.findViewById(R.id.type_spinner);

        sportAdapter = ArrayAdapter.createFromResource(getContext(),R.array.sport_menu,android.R.layout.simple_spinner_item);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportMenu.setAdapter(sportAdapter);
        sportMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),adapterView.getItemAtPosition(i)+" selected",Toast.LENGTH_LONG).show();
                showType(sportMenu.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Button proceed

        btnProceed = (Button)rootView.findViewById(R.id.BTN_Proceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args = new Bundle();
                args.putString("sport_type",sportMenu.getSelectedItem().toString());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fr = new SubmitScore();
                fr.setArguments(args);
                fragmentTransaction.replace(R.id.new_score_fragment,fr,fr.toString());
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }



    public void showType(String string){

        int id = 0;

        switch(string){
            case "Volleyball":
                id = R.array.Volleyball;
                break;
            case "Dodgeball":
                id = R.array.Dodgeball;
                break;
            case "Badminton":
                id = R.array.Badminton;
                break;
            case "Squash":
                id = R.array.Squash;
                break;
            case "Netball":
                id = R.array.Netball;
                break;
            case "Basketball":
                id = R.array.Basketball;
                break;
            case "Sepak Takraw":
                id = R.array.Sepak_Takraw;
                break;
            case "Telematch":
                id = R.array.Telematch;
                break;
            case "Football":
                id = R.array.Football;
                break;
            case "Fifa":
                id = R.array.Fifa;
                break;
            case "Rocket League":
                id = R.array.Rocket_League;
                break;
        }

        typeAdapter = ArrayAdapter.createFromResource(getContext(),id,android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeMenu.setAdapter(typeAdapter);


    }
}
