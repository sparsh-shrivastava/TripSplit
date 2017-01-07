package in.projects.sparsh.tripsplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.projects.sparsh.tripsplit.DBPackage.ContractClass;
import in.projects.sparsh.tripsplit.DBPackage.specificTripDBHelper;
public class new_trip_two_activity extends AppCompatActivity {

    TextView[] textViewArray;
    EditText[] editTextArray;
    int count;
    String tripName;
    String DBName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip_two);

        Intent here = this.getIntent();
        tripName=here.getStringExtra("trip name");
        count = here.getIntExtra("count",1);
        setTitle(tripName);

        DBName=adjustTripName(tripName)+ ContractClass.DBNAME_TRIP;

        LinearLayout activityLayout = (LinearLayout) findViewById(R.id.new_trip_two_linear_layout_one);

        textViewArray = new TextView[count];
        editTextArray = new EditText[count];

        assert activityLayout!=null;

        for(int i = 0;i<count;i++){
            textViewArray[i] = new TextView(this);
            textViewArray[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textViewArray[i].setText("Name " + (i + 1));
            activityLayout.addView(textViewArray[i]);

            editTextArray[i]=new EditText(this);
            editTextArray[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            editTextArray[i].setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            activityLayout.addView(editTextArray[i]);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_trip_two_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_trip_two_done_all:
                submitClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void submitClicked(){
        specificTripDBHelper dbHelper = new specificTripDBHelper(new_trip_two_activity.this,DBName);
        String memberName;
        for (int i=0;i<count;i++){
            memberName=editTextArray[i].getText().toString();
            if(memberName.length()==0){
                Toast temp = Toast.makeText(new_trip_two_activity.this,"Invalid Input",Toast.LENGTH_SHORT);
                temp.show();
                return;
            }
            else{
                dbHelper.insertNewMember(memberName);
            }
        }
        dbHelper.close();
        Intent intent = new Intent(new_trip_two_activity.this,comboActivity.class);
        intent.putExtra("trip name", tripName);
        intent.putExtra("count",count);
        startActivity(intent);

    }

    public static String adjustTripName(String name){
        name=name.replace(' ', '_');
        name=name.toUpperCase();
        return name;
    }
}
