package masco.com.mysukan_vi.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import masco.com.mysukan_vi.R;

/**
 * Created by akarin on 11/09/17.
 */

public class MatchDetailViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MatchDetailViewHolder";
    public TextView vs;
    public TextView match_time;
    public TextView team_1;
    public TextView team_2;

    public MatchDetailViewHolder(View itemView) {
        super(itemView);

        match_time = (TextView) itemView.findViewById(R.id.text_minimized_time);
        team_1 = (TextView) itemView.findViewById(R.id.text_minimized_team_1);
        team_2 = (TextView) itemView.findViewById(R.id.text_minimized_team_2);
        vs = (TextView) itemView.findViewById(R.id.text_minimized_vs);
    }
}