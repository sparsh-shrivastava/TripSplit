package in.projects.sparsh.tripsplit.customAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.R;


public class peoplesCustomAdapter extends ArrayAdapter<peoplesListClass> {

    private static final String LOG_TAG = peoplesCustomAdapter.class.getSimpleName();


    public peoplesCustomAdapter(Activity context, ArrayList<peoplesListClass> summaryList) {
        super(context, 0, summaryList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.peoples_list_item_layout, parent, false);
        }

        peoplesListClass current = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.peoples_list_item_name);
        name.setText(current.getName());

        TextView totalExpense = (TextView) listItemView.findViewById(R.id.peoples_list_item_total_expense);
        totalExpense.setText("Total Expense: "+Integer.toString(current.getTotalExpense()));

        TextView amountSpent = (TextView) listItemView.findViewById(R.id.peoples_list_item_amount_spent);
        amountSpent.setText("Amount Spent "+Integer.toString(current.getTotalSpent()));


        return listItemView;
    }

}
