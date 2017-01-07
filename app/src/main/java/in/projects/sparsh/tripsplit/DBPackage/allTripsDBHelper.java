package in.projects.sparsh.tripsplit.DBPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class allTripsDBHelper extends SQLiteOpenHelper {

    public allTripsDBHelper(Context context) {
        super(context, ContractClass.DBNAME_ALL, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ContractClass.TABLE_NAME_ALL_TRIPS+"( "+
                ContractClass.COLUMN_ALLTRIPS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ContractClass.COLUMN_ALLTRIPS_NAME+" TEXT NOT NULL, "+
                ContractClass.COLUMN_ALLTRIPS_DATE+" TEXT NOT NULL, "+
                ContractClass.COLUMN_ALLTRIPS_MEMBERS+" INTEGER NOT NULL "+
                ");";
        Log.v("query: ",query);

        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ContractClass.TABLE_NAME_ALL_TRIPS);
        onCreate(db);
    }

    public void insertNewTrip(String name, int members){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ContractClass.COLUMN_ALLTRIPS_NAME,name);
        values.put(ContractClass.COLUMN_ALLTRIPS_MEMBERS,members);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        values.put(ContractClass.COLUMN_ALLTRIPS_DATE, df.format(c.getTime()));

        db.insert(ContractClass.TABLE_NAME_ALL_TRIPS, null, values);

        Log.v("All trips","new trip added");

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor answer = db.rawQuery("SELECT * FROM "+ContractClass.TABLE_NAME_ALL_TRIPS+";",null);

        return answer;
    }


}
