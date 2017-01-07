package in.projects.sparsh.tripsplit.customAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.R;


public class allExpensesCustomAdapter extends ArrayAdapter<allExpensesListClass> {

    private static final String LOG_TAG = allExpensesCustomAdapter.class.getSimpleName();


    public allExpensesCustomAdapter(Activity context, ArrayList<allExpensesListClass> expenseList) {
        super(context, 0, expenseList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.all_expenses_list_item_layout, parent, false);
        }

        allExpensesListClass current = getItem(position);
        String line;
        line=current.getPaidFor()+" owes "+current.getPaidBy()+" Rs. "+Integer.toString(current.getAmount())
                +" for "+current.getReason();

        TextView name = (TextView) listItemView.findViewById(R.id.all_expense_item_text);
        name.setText(line);

        TextView count = (TextView) listItemView.findViewById(R.id.all_expense_item_date);
        count.setText(current.getDate());


        return listItemView;
    }

}
