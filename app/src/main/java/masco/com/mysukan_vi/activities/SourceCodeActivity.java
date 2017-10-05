package masco.com.mysukan_vi.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import masco.com.mysukan_vi.R;

/**
 * Info of the developers. YOU!
 * Created by Akarin on 9/30/2017.
 */

public class SourceCodeActivity extends BaseActivity {

    private ImageButton githubIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_code);

        getSupportActionBar().setTitle("Source code");

        ((TextView) findViewById(R.id.activity_developer_info_text_github)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.github.com/hanif-ariffin/mysukan-vi");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
