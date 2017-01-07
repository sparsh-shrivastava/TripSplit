package in.projects.sparsh.tripsplit.customAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.R;


public class summaryCustomAdapter extends ArrayAdapter<summaryListClass> {

    private static final String LOG_TAG = summaryCustomAdapter.class.getSimpleName();


    public summaryCustomAdapter(Activity context, ArrayList<summaryListClass> summaryList) {
        super(context, 0, summaryList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.summary_list_item_layout, parent, false);
        }

        summaryListClass current = getItem(position);
        String line;
        line=current.getGiver()+" owes "+current.getReciever()+" Rs. "+Integer.toString(current.getAmount());

        TextView text = (TextView) listItemView.findViewById(R.id.summary_item_text);
        text.setText(line);


        return listItemView;
    }

}
