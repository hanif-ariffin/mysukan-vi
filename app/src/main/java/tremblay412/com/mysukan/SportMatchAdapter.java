package tremblay412.com.mysukan;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by Akarin on 9/9/2017.
 */

public class SportMatchAdapter extends ArrayAdapter<SportDetail> {

    public SportMatchAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
