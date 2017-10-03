package masco.com.mysukan_vi.helper;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.models.SingleScoreMatch;

/**
 * Created by Haziq on 2017-10-02.
 */

public class SingleScoreMatchAdapter extends ArrayAdapter<SingleScoreMatch> {

    List<SingleScoreMatch> matchesList = new ArrayList<>();

    public SingleScoreMatchAdapter(Context context, int textViewResourceId, List<SingleScoreMatch> objects) {
        super(context, textViewResourceId, objects);
        matchesList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.include_item_minimized_match_detail, null);

        String time = "";
        if (matchesList.get(position).match_date != null) {
            time = new SimpleDateFormat("HH:mm a").format(new Date(matchesList.get(position).match_date * 1000L));
        }
        ((TextView) v.findViewById(R.id.text_minimized_time)).setText(time);
        if (matchesList.get(position).custom_name_1 != null) {
            if (!matchesList.get(position).custom_name_1.isEmpty()) {
                ((TextView) v.findViewById(R.id.text_minimized_team_1)).setText(matchesList.get(position).custom_name_1);
            } else {
                ((TextView) v.findViewById(R.id.text_minimized_team_1)).setText(matchesList.get(position).team_1_name);
            }

        } else {
            ((TextView) v.findViewById(R.id.text_minimized_team_1)).setText(matchesList.get(position).team_1_name);
        }

        if (matchesList.get(position).custom_name_2 != null) {
            if (!matchesList.get(position).custom_name_2.isEmpty()) {
                ((TextView) v.findViewById(R.id.text_minimized_team_2)).setText(matchesList.get(position).custom_name_2);
            } else {
                ((TextView) v.findViewById(R.id.text_minimized_team_2)).setText(matchesList.get(position).team_2_name);
            }

        } else {
            ((TextView) v.findViewById(R.id.text_minimized_team_2)).setText(matchesList.get(position).team_2_name);
        }
        return v;

    }

    public String getDateFromUTCTimestamp(long mTimestamp, String mDateFormate) {
        String date = null;
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            cal.setTimeInMillis(mTimestamp * 1000L);
            date = DateFormat.format(mDateFormate, cal.getTimeInMillis()).toString();

            SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate);
            dateFormatter.setTimeZone(TimeZone.getDefault());
            date = dateFormatter.format(date);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}

