package in.projects.sparsh.tripsplit;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import in.projects.sparsh.tripsplit.DBPackage.*;
import in.projects.sparsh.tripsplit.customAdapters.all_trips_list_class;
import in.projects.sparsh.tripsplit.customAdapters.all_trips_list_customadapter_class;

public class all_trips_list_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips_list);

        FloatingActionButton floatAddTripButton = (FloatingActionButton) findViewById(R.id.all_trips_list_activity_new_trip_button);
        assert floatAddTripButton!=null;
        floatAddTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(all_trips_list_activity.this,new_trip_one_activity.class));
            }
        });

        final ArrayList<all_trips_list_class> tripsList = new ArrayList<>();
        allTripsDBHelper db = new allTripsDBHelper(this);
        Cursor cursor = db.getData();
        if(cursor.moveToFirst()){
            do {
                String name= cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_ALLTRIPS_NAME));
                int countEntry = cursor.getInt(cursor.getColumnIndex(ContractClass.COLUMN_ALLTRIPS_MEMBERS));
                String dateString = cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_ALLTRIPS_DATE));
                tripsList.add(new all_trips_list_class(name,countEntry,dateString));
            }while (cursor.moveToNext());
        }
        cursor.close();

        all_trips_list_customadapter_class itemsAdapter = new all_trips_list_customadapter_class
                (this,tripsList);

        ListView listView = (ListView) findViewById(R.id.all_trips_list_activity_layout);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                all_trips_list_class tripClass = tripsList.get(position);

                Intent intent = new Intent(all_trips_list_activity.this,comboActivity.class);
                intent.putExtra("trip name",tripClass.getName());
                intent.putExtra("count",tripClass.getCount());
                startActivity(intent);

            }
        });


    }
}
