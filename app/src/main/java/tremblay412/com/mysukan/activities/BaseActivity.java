package tremblay412.com.mysukan.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import tremblay412.com.mysukan.R;

/**
 * Created by akarin on 12/09/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private static String currentMessage = "";
    private final static String TAG = "BaseActivity";

    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            currentMessage = message;
            mProgressDialog.setMessage(message);
        } else {
            Log.d(TAG, "ProcessDialog is not null with message:" + currentMessage);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_developers_info) {
            FirebaseAuth.getInstance().signOut();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
