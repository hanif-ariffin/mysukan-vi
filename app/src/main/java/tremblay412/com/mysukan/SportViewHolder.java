package tremblay412.com.mysukan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by akarin on 11/09/17.
 */

public class SportViewHolder extends RecyclerView.ViewHolder {

    public TextView sportName;

    public SportViewHolder(View itemView) {
        super(itemView);

        sportName = (TextView) itemView.findViewById(R.id.include_item_sport_name);
    }
}