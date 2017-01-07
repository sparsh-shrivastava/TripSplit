package in.projects.sparsh.tripsplit;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.DBPackage.ContractClass;
import in.projects.sparsh.tripsplit.DBPackage.specificTripDBHelper;
import in.projects.sparsh.tripsplit.customAdapters.summaryCustomAdapter;
import in.projects.sparsh.tripsplit.customAdapters.summaryListClass;
import android.support.v4.app.Fragment;
/**
 * A simple {@link Fragment} subclass.
 */
public class summaryFragment extends Fragment {
    String tripName,tripDBName;
    int count;
    String[] names;
    specificTripDBHelper dbHelper;


    public summaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_summary_activity,container,false);
        Intent here = getActivity().getIntent();
        tripName=here.getStringExtra("trip name");
        count=here.getIntExtra("count", 1);
        tripDBName=new_trip_two_activity.adjustTripName(tripName)+ ContractClass.DBNAME_TRIP;
        dbHelper=new specificTripDBHelper(getActivity(),tripDBName);
        names = new String[count];

        final ArrayList<summaryListClass> summaryList = new ArrayList<>();


        Cursor cursor=dbHelper.getAllMembers();
        if(cursor.moveToFirst()){
            int i =0;
            do {
                String name= cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_MEMBERS_NAME));
                names[i] = name;
                i++;

            }while (cursor.moveToNext());
        }
        cursor.close();
        int a;
        for(int i=0;i<count-1;i++){
            for (int j=i+1;j<count;j++){
                a=dbHelper.getTwoPeopleAmount(names[i],names[j]);
                if(a>0)
                    summaryList.add(new summaryListClass(names[j],names[i],a));
                else if (a<0){
                    a=a*(-1);
                    summaryList.add(new summaryListClass(names[i], names[j], a));

                }

            }
        }

        summaryCustomAdapter adapter = new summaryCustomAdapter(getActivity(),summaryList);

        ListView listView = (ListView) rootView.findViewById(R.id.summary_activity_layout);
        listView.setAdapter(adapter);

        return rootView;
    }


}
