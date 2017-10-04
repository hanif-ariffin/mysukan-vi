package masco.com.mysukan_vi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import masco.com.mysukan_vi.R;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_item_developers_info) {
            Intent intent = new Intent(BaseActivity.this, InfoActivity.class);
            startActivity(intent);
            return true;
        } else if (i == R.id.menu_item_sponsors) {
            Intent intent = new Intent(BaseActivity.this, SponsorsActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
