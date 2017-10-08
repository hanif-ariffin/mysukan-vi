package masco.com.mysukan_vi.activities.other;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.activities.main.SportDetailActivity;
import masco.com.mysukan_vi.helper.NameManager;
import masco.com.mysukan_vi.models.Sport;

/**
 * This is the base Activity that is common among many Activities. This activity will provide the default Menu (showing developer's info, overall winner and sponsors selection).
 * It will also provide the basic ProcessDialog functionality (Showing a text and hiding it).
 * Created by akarin on 12/09/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private final static String TAG = "BaseActivity";

    /**
     * Enable the ProcessDialog given a message to display.
     *
     * @param message -- the message to be displayed.
     */
    public void showProgressDialog(String message) {
        /**
         * First check if there are already ProcessDialog running.
         */
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    /**
     * If there are ProcessDialog showing, then disable it.
     */
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Check if there is a ProcessDialog showing.
     *
     * @return -- boolean whether or not there are ProcessDialog showing.
     */
    public boolean isProcessDialogShowing() {
        if (progressDialog != null) {
            return progressDialog.isShowing();
        }
        return false;
    }

    public ArrayList<Sport> getSportListActivityButtons() {

        ArrayList<Sport> sports = new ArrayList<Sport>();

        sports.add(new Sport(NameManager.SportTypeSafeNames.SOCCER, (Button) findViewById(R.id.button_soccer)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.BADMINTON_MEN_DOUBLES, (Button) findViewById(R.id.button_badminton_men_doubles)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.BADMINTON_WOMEN_DOUBLES, (Button) findViewById(R.id.button_badminton_women_doubles)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.BADMINTON_MIXED_DOUBLES, (Button) findViewById(R.id.button_badminton_mixed_doubles)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.SQUASH_MEN_SINGLES, (Button) findViewById(R.id.button_squash_men_singles)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.SQUASH_WOMEN_SINGLES, (Button) findViewById(R.id.button_squash_women_singles)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.FRISBEE, (Button) findViewById(R.id.button_frisbee)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.DODGEBALL, (Button) findViewById(R.id.button_dodgeball)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.NETBALL, (Button) findViewById(R.id.button_netball)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.BASKETBALL, (Button) findViewById(R.id.button_basketball)));
        sports.add(new Sport(NameManager.SportTypeSafeNames.VOLLEYBALL, (Button) findViewById(R.id.button_volleyball)));

        return sports;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu != null) {
            menu.findItem(R.id.menu_item_theme_song_lyric).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_item_sponsors) {
            Intent intent = new Intent(BaseActivity.this, SponsorsActivity.class);
            startActivity(intent);
            return true;
        } else if (i == R.id.menu_item_developer_awesome) {
            Intent intent = new Intent(BaseActivity.this, DeveloperAwesomeActivity.class);
            startActivity(intent);
            return true;
        } else if (i == R.id.menu_item_theme_song_lyric) {
            Intent intent = new Intent(BaseActivity.this, LyricActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
