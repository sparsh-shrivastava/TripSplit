package in.projects.sparsh.tripsplit.customAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.R;


public class all_trips_list_customadapter_class extends ArrayAdapter<all_trips_list_class> {

    private static final String LOG_TAG = all_trips_list_customadapter_class.class.getSimpleName();


    public all_trips_list_customadapter_class(Activity context, ArrayList<all_trips_list_class> tripList) {
        super(context, 0, tripList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.all_trips_list_item_layout, parent, false);
        }

        all_trips_list_class current = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.all_trips_layout_item_name);
        name.setText(current.getName());

        TextView count = (TextView) listItemView.findViewById(R.id.all_trips_layout_item_count);
        count.setText(Integer.toString(current.getCount())+" People");

        TextView dateText = (TextView) listItemView.findViewById(R.id.all_trips_layout_item_date);
        dateText.setText("Date Created: "+current.getDate());

        return listItemView;
    }

}
