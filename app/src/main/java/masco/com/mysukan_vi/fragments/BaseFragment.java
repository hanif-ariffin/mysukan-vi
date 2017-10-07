package masco.com.mysukan_vi.fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

/**
 * This is the Activity that provide the methods to show and hide ProcessDialog.
 * This is just a convenient.
 * Created by akarin on 07/09/17.
 */

public class BaseFragment extends Fragment {
    private ProgressDialog mProgressDialog;

    public void showProgressDialog(String stringToShow) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(stringToShow);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
