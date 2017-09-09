package tremblay412.com.mysukan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by akarin on 07/09/17.
 */

public class SportFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    // UI references.
    private ListView iListGames;
    private FloatingActionButton registerButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.main_page, container, false);

        /**
         // Open Registration Page
         registerButton = (FloatingActionButton) rootView.findViewById(R.id.RegisterButton);
         registerButton.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        Intent myIntent = new Intent(MainPageActivity.this, LoginFragment.class);
        startActivity(myIntent);
        }
        });
         **/
        iListGames = (ListView) rootView.findViewById(R.id.listview1);

        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, FirebaseConnector.getInstance().getMatches());
        iListGames.setAdapter(lArrayAdapter);
        iListGames.setOnItemClickListener(this);
        return rootView;
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Toast.makeText(getActivity(), "You clicked Item: " + id + " at position:" + position, Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getActivity(), BaseSportDetailActivity.class);
        startActivity(myIntent);
    }
}
