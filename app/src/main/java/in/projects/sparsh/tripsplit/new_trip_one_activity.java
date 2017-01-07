package in.projects.sparsh.tripsplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import in.projects.sparsh.tripsplit.DBPackage.*;
public class new_trip_one_activity extends AppCompatActivity {
    Button plus,minus;
    EditText tripNameEditText;
    TextView peopleCount;
    int count=0;
    String tripName;
    allTripsDBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip_one);
        connectViews();


        peopleCount.setText(Integer.toString(count));
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                peopleCount.setText(Integer.toString(count));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=0){
                    count--;
                    peopleCount.setText(Integer.toString(count));
                }
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_trip_one_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_trip_one_done_button:
                tripName=tripNameEditText.getText().toString();
                if(tripName.length() <=0 || count==0){
                    Toast temp = Toast.makeText(new_trip_one_activity.this,"Invalid Input",Toast.LENGTH_SHORT);
                    temp.show();
                }
                else{
                    db.insertNewTrip(tripName, count);
                    db.close();

                    Intent intent = new Intent(new_trip_one_activity.this,new_trip_two_activity.class);
                    intent.putExtra("trip name", tripName);
                    intent.putExtra("count", count);
                    startActivity(intent);

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void connectViews(){
        plus = (Button)findViewById(R.id.new_trip_one_plus_button);
        minus=(Button) findViewById(R.id.new_trip_one_minus_button);
        tripNameEditText = (EditText) findViewById(R.id.new_trip_one_trip_name);
        peopleCount = (TextView) findViewById(R.id.new_trip_one_people_count);
        db = new allTripsDBHelper(this);

    }
}
