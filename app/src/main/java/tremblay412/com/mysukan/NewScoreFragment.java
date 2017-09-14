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

import java.util.ArrayList;
import java.util.List;

public class NewScoreFragment extends BaseFragment {


    // UI references.
    private Button btnProceed;
    ArrayAdapter<CharSequence> sportAdapter, typeAdapter;
    private ListView iListGames;
    private ViewGroup iMainPage;
    Bundle args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_new_score_fragment, container, false);


        //Listview for sport
        iListGames = (ListView) rootView.findViewById(R.id.sport_listview);
        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getGames());
        iListGames.setAdapter(lArrayAdapter);

        //Listener for listview

        iListGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                args = new Bundle();
                args.putString("sport_name",iListGames.getItemAtPosition(i).toString());
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

    private List<String> getGames() {
        List<String> lGames = new ArrayList<>();
        lGames.add("Soccer");
        lGames.add("Badminton Men Doubles");
        lGames.add("Badminton Women Doubles");
        lGames.add("Badminton Mixed Doubles");
        lGames.add("Squash Men Singles");
        lGames.add("Squash Women Singles");
        lGames.add("Frisbee");
        lGames.add("Dodgeball");
        lGames.add("Netball");
        lGames.add("Basketball");
        lGames.add("Sepak Takraw");
        lGames.add("Volleyball");
        lGames.add("Fifa");
        lGames.add("Rocket League");

        return lGames;
    }
}
