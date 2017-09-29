package tremblay412.com.mysukan.helper;

/**
 * Created by Haziq on 2017-09-29.
 */
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import tremblay412.com.mysukan.R;

public class ListAdapter extends ArrayAdapter<String> {

    List<String> arrayList = new ArrayList<>();

    public ListAdapter(Context context, int textViewResourceId, List<String> objects) {
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
        v = inflater.inflate(R.layout.listview_component, null);
        TextView textView = (TextView) v.findViewById(R.id.text1);
        textView.setText(arrayList.get(position));
        return v;

    }

}