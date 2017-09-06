package tremblay412.com.mysukan;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class MainPageActivity extends AppCompatActivity{

    // UI references.
    private ListView iListGames;
    private ViewGroup iMainPage;
    private FloatingActionButton RegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);


        // Open Registration Page
        RegisterButton = (FloatingActionButton)findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainPageActivity.this,RegisterActivity.class);
                startActivity(myIntent);
            }
        });


        //mMainPage = findViewById(R.layout.main_page);
        iMainPage = (ViewGroup) findViewById(R.id.main_page);

        iListGames = (ListView) findViewById(R.id.listview1);

        ArrayAdapter<String> lArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getGames());
        iListGames.setAdapter(lArrayAdapter);

    }

    private List<String> getGames(){
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

