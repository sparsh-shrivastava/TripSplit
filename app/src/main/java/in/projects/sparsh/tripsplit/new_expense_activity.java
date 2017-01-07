package in.projects.sparsh.tripsplit;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import in.projects.sparsh.tripsplit.DBPackage.*;

public class new_expense_activity extends AppCompatActivity {

    RadioGroup radioGroup;
    LinearLayout checkBoxLayout;
    RadioButton[] paidBy;
    CheckBox[] paidFor;
    EditText reason,amountPaid;
    String tripName,tripDBName;
    int count,amount,numberOfPeopleSharing,indexOfPaidBy;
    specificTripDBHelper dbHelper;
    String [] namesOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense_activity);
        Intent here = getIntent();
        tripName = here.getStringExtra("trip name");
        count = here.getIntExtra("count", 1);
        setTitle("New Expense");
        Log.v("Count",Integer.toString(count));
        doConnections();
        getNamesOfPeople();
        adjustLayout();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_expense_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_expense_menu_done:
                getNumberSharedBy();
                doDatabaseEntryOfExpense();
                Intent intent = new Intent(new_expense_activity.this,comboActivity.class);
                intent.putExtra("trip name",tripName);
                intent.putExtra("count",count);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    void doConnections(){

        radioGroup = (RadioGroup) findViewById(R.id.new_expense_radio_group_paid_by);
        checkBoxLayout = (LinearLayout) findViewById(R.id.new_expense_linear_layout_paid_by);
        reason=(EditText) findViewById(R.id.new_expense_reason);
        amountPaid=(EditText) findViewById(R.id.new_expense_amount);
        paidBy = new RadioButton[count];
        paidFor = new CheckBox[count];
        tripDBName = new_trip_two_activity.adjustTripName(tripName) + ContractClass.DBNAME_TRIP;
        dbHelper=new specificTripDBHelper(new_expense_activity.this,tripDBName);
        namesOfPeople = new String[count];
    }

    void getNamesOfPeople(){
        Cursor cursor = dbHelper.getAllMembers();
        int i=0;
        if(cursor.moveToFirst()){
            do {
                String name= cursor.getString(cursor.getColumnIndex(ContractClass.COLUMN_MEMBERS_NAME));
                namesOfPeople[i] = name;
                i++;
                Log.v("name in db",name);
            }while (cursor.moveToNext());
        }
        cursor.close();


    }
    void adjustLayout(){
        for(int i=0;i<count;i++){
                paidFor[i] = new CheckBox(this);
                paidFor[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                paidFor[i].setText(namesOfPeople[i]);

                paidBy[i] = new RadioButton(this);
                paidBy[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                paidBy[i].setText(namesOfPeople[i]);

                radioGroup.addView(paidBy[i]);
                checkBoxLayout.addView(paidFor[i]);


            }
        }



    void getNumberSharedBy(){
        numberOfPeopleSharing=0;
        for (int i=0;i<count;i++){
            if(paidFor[i].isChecked()) numberOfPeopleSharing++;
            if (paidBy[i].isChecked()) indexOfPaidBy=i;
        }
        Log.v("shared by",Integer.toString(numberOfPeopleSharing));
    }
    void doDatabaseEntryOfExpense(){
        amount=Integer.parseInt(amountPaid.getText().toString());
        int perPerson = amount/numberOfPeopleSharing;
        String reasonForExpense =  reason.getText().toString();

        for (int i=0;i<count;i++){
            if(paidFor[i].isChecked()){
                dbHelper.insertExpense(namesOfPeople[indexOfPaidBy],namesOfPeople[i],reasonForExpense,perPerson);
            }
        }


    }
}
