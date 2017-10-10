package masco.com.mysukan_vi.helper;

/**
 * Created by r3xas on 9/25/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import masco.com.mysukan_vi.R;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final List<String> mobileValues;

    public ImageAdapter(Context context, List<String> mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from include_sport_image_buttone_sport_image_button.xml
            gridView = inflater.inflate(R.layout.include_item_sport_image_button, null);

            // set value into TextView
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues.get(position));

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues.get(position);

            if (mobile.equals("Soccer")) {
                imageView.setImageResource(R.drawable.soccer);
            } else if (mobile.equals("Badminton")) {
                imageView.setImageResource(R.drawable.badminton);
            } else if (mobile.equals("Basketball")) {
                imageView.setImageResource(R.drawable.basketball);
            } else {
                imageView.setImageResource(R.drawable.dodgeball);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}