package masco.com.mysukan_vi.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.models.Announcement;

/**
 * Created by Haziq on 2017-10-02.
 */

public class AnnouncementAdapter extends ArrayAdapter<Announcement> {

    private String OUTPUT_DATE_FORMAT = "hh:mm a";
    List<Announcement> announcementsList = new ArrayList<>();

    public AnnouncementAdapter(Context context, int textViewResourceId, List<Announcement> list) {
        super(context, textViewResourceId, list);
        announcementsList = list;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.include_item_annoucement, null);

        /**
         * Initialize the hooks
         */
        TextView announcementTimeTextView = (TextView) convertView.findViewById(R.id.include_item_announcement_time_text);
        TextView announcementTitleTextView = (TextView) convertView.findViewById(R.id.include_item_announcement_title_text);
        TextView announcementMessageTextView = (TextView) convertView.findViewById(R.id.include_item_announcement_message_text);

        /**
         * Set fonts of the TextViews
         */
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Avenir-Book.ttf");
        announcementTitleTextView.setTypeface(typeface);
        announcementMessageTextView.setTypeface(typeface);
        announcementTimeTextView.setTypeface(typeface);

        announcementTitleTextView.setText(announcementsList.get(position).getSubject());
        announcementMessageTextView.setText(announcementsList.get(position).getMessage());

        announcementTimeTextView.setText(getDateFromUTCTimestamp(announcementsList.get(position).getTime(), OUTPUT_DATE_FORMAT));
        return convertView;

    }

    public String getDateFromUTCTimestamp(long mTimestamp, String mDateFormate) {
        String date = null;
        try {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            calendar.setTimeInMillis(mTimestamp * 1000L);
            date = DateFormat.format(mDateFormate, calendar.getTimeInMillis()).toString();

            SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate);
            dateFormatter.setTimeZone(TimeZone.getDefault());
            date = dateFormatter.format(date);
            return date;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return date;
    }
}

