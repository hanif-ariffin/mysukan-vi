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
import masco.com.mysukan_vi.annoucement.Announcement;

/**
 * Created by Haziq on 2017-10-02.
 */

public class AnnoucementAdapter extends ArrayAdapter<Announcement> {

    private String OUTPUT_DATE_FORMATE = "hh:mm a";
    List<Announcement> arrayList = new ArrayList<>();

    public AnnoucementAdapter(Context context, int textViewResourceId, List<Announcement> objects) {
        super(context, textViewResourceId, objects);
        arrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.listview_annoucement, null);
        TextView textView1 = (TextView) v.findViewById(R.id.textView10);
        TextView textView2 = (TextView) v.findViewById(R.id.textView11);
        TextView textView3 = (TextView) v.findViewById(R.id.textView55);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Avenir-Book.ttf");
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);
        textView1.setText(arrayList.get(position).getSubject());
        textView2.setText(arrayList.get(position).getMessage());

        int gmtOffset = TimeZone.getDefault().getRawOffset();

        textView3.setText(getDateFromUTCTimestamp(arrayList.get(position).getTime(), OUTPUT_DATE_FORMATE));
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

