package tremblay412.com.mysukan.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

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
}
