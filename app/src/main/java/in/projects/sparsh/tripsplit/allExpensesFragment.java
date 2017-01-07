package in.projects.sparsh.tripsplit;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.DBPackage.ContractClass;
import in.projects.sparsh.tripsplit.DBPackage.specificTripDBHelper;
import in.projects.sparsh.tripsplit.customAdapters.allExpensesCustomAdapter;
import in.projects.sparsh.tripsplit.customAdapters.allExpensesListClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class allExpensesFragment extends Fragment {
    String tripName,DBTripName;
    int count;


    public allExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_all_expenses_activity,container,false);

        Intent here = getActivity().getIntent();
        tripName = here.getStringExtra("trip name");
        count = here.getIntExtra("count", 1);

        DBTripName = new_trip_two_activity.adjustTripName(tripName) + ContractClass.DBNAME_TRIP;


        final ArrayList<allExpensesListClass> expenseList = new ArrayList<>();
        specificTripDBHelper db = new specificTripDBHelper(getActivity(),DBTripName);
        Cursor cursor = db.getAllExpenses();
        if(cursor.moveToFirst()){
            do {
                String paidBy= cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_EXPENSE_PAID_BY));
                int amount = cursor.getInt(cursor.getColumnIndex(ContractClass.COLUMN_EXPENSE_AMOUNT));
                String paidFor = cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_EXPENSE_PAID_FOR));
                String reason = cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_EXPENSE_REASON));
                String date = cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_EXPENSE_DATE));
                if(!paidBy.equals(paidFor))
                    expenseList.add(new allExpensesListClass(paidBy,paidFor,reason,date,amount));
            }while (cursor.moveToNext());
        }
        cursor.close();

        allExpensesCustomAdapter itemsAdapter = new allExpensesCustomAdapter(getActivity(),expenseList);

        ListView listView = (ListView) rootView.findViewById(R.id.all_expenses_activity_layout);

        listView.setAdapter(itemsAdapter);

        return rootView;
    }


}
