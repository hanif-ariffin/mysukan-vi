package masco.com.mysukan_vi.activities.admin;

/**
 * Created by User on 2017-09-20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.main.SportDetailActivity;
import masco.com.mysukan_vi.activities.other.BaseActivity;
import masco.com.mysukan_vi.helper.NameManager;

/**
 * A login screen that offers login via email/password.
 */

public class AdminActivity extends BaseActivity {

    private Button newScoreButton, editScoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_adminpage_main);

        getSupportActionBar().setTitle(getString(R.string.final_static_string_admin));

        newScoreButton = (Button) findViewById(R.id.fragment_adminpage_main_button_goto_newscore);
        editScoreButton = (Button) findViewById(R.id.fragment_adminpage_main_button_goto_editscore);

        newScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "GOTO:newScoreActivity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, SportListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ADMINPAGE_REQUEST_TYPE", "ADMINPAGE_REQUEST_TYPE_NEWSCORE");
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        editScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "GOTO:editScoreActivity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, SportListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ADMINPAGE_REQUEST_TYPE", "ADMINPAGE_REQUEST_TYPE_EDITSCORE");
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}