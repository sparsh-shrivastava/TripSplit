package in.projects.sparsh.tripsplit;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.DBPackage.*;
import in.projects.sparsh.tripsplit.customAdapters.peoplesCustomAdapter;
import in.projects.sparsh.tripsplit.customAdapters.peoplesListClass;

public class peoples_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peoples_activity);

        Intent here = getIntent();
        String tripName = here.getStringExtra("trip name");
        int count = here.getIntExtra("count", 1);
        String tripDBName = new_trip_two_activity.adjustTripName(tripName)+ContractClass.DBNAME_TRIP;
        String peopleNames[] = new String[count];

        final ArrayList<peoplesListClass> peopleList = new ArrayList<>();
        specificTripDBHelper dbHelper = new specificTripDBHelper(this,tripDBName);
        Cursor cursor=dbHelper.getAllMembers();
        if(cursor.moveToFirst()){
            int i =0;
            do {
                String name= cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_MEMBERS_NAME));
                peopleNames[i] = name;
                i++;

            }while (cursor.moveToNext());
        }
        cursor.close();
        int amountSpent,amountExpense;
        for (int i=0;i<count;i++){
            amountExpense=dbHelper.getPersonExpense(peopleNames[i]);
            amountSpent=dbHelper.getPersonSpent(peopleNames[i]);
            peopleList.add(new peoplesListClass(peopleNames[i],amountExpense,amountSpent));
        }

        peoplesCustomAdapter itemsAdapter = new peoplesCustomAdapter(this,peopleList);

        ListView listView = (ListView) findViewById(R.id.peoples_activity_list_layout);

        listView.setAdapter(itemsAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.peoples_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
