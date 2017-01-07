package in.projects.sparsh.tripsplit.DBPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sparsh on 27/11/16.
 */
public class specificTripDBHelper extends SQLiteOpenHelper {

    public specificTripDBHelper(Context context, String name) {
        super(context, name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryForMembers = "CREATE TABLE "+ContractClass.TABLE_NAME_MEMBERS+"( "+
                ContractClass.COLUMN_MEMBERS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ContractClass.COLUMN_MEMBERS_NAME+" TEXT NOT NULL "+
                ");";
        db.execSQL(queryForMembers);

        String queryForExpenses= "CREATE TABLE "+ContractClass.TABLE_NAME_ALL_EXPENSES+"( "+
                ContractClass.COLUMN_EXPENSE_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ContractClass.COLUMN_EXPENSE_REASON+" TEXT,"+
                ContractClass.COLUMN_EXPENSE_AMOUNT+" INTEGER, "+
                ContractClass.COLUMN_EXPENSE_PAID_BY+" TEXT, "+
                ContractClass.COLUMN_EXPENSE_PAID_FOR+" TEXT, "+
                ContractClass.COLUMN_EXPENSE_DATE+" TEXT "+
                ");";
        db.execSQL(queryForExpenses);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ContractClass.TABLE_NAME_ALL_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS "+ContractClass.TABLE_NAME_MEMBERS);
        onCreate(db);
    }
    public void insertNewMember(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContractClass.COLUMN_MEMBERS_NAME, name);
        db.insert(ContractClass.TABLE_NAME_MEMBERS, null, values);
    }

    public Cursor getAllMembers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor answer = db.rawQuery("SELECT * FROM "+ContractClass.TABLE_NAME_MEMBERS+";",null);

        return answer;
    }

    public void insertExpense(String giver, String reciever, String reason,int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ContractClass.COLUMN_EXPENSE_PAID_BY,giver);
        values.put(ContractClass.COLUMN_EXPENSE_PAID_FOR,reciever);
        values.put(ContractClass.COLUMN_EXPENSE_REASON,reason);
        values.put(ContractClass.COLUMN_EXPENSE_AMOUNT,amount);



        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        values.put(ContractClass.COLUMN_EXPENSE_DATE, df.format(c.getTime()));
        Log.v("name: ", giver);
        Log.v("name: ",reason);
        Log.v("name: ",reciever);
        Log.v("name: ",Integer.toString(amount));

        db.insert(ContractClass.TABLE_NAME_ALL_EXPENSES, null, values);

    }

    public Cursor getAllExpenses(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor answer = db.rawQuery("SELECT * FROM "+ContractClass.TABLE_NAME_ALL_EXPENSES+" ORDER BY "
                +ContractClass.COLUMN_EXPENSE_id+" DESC;",null);

        return answer;
    }

    public int getTwoPeopleAmount(String firstPerson,String secondPerson){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryA = "SELECT SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+") FROM "
                +ContractClass.TABLE_NAME_ALL_EXPENSES+" WHERE "+ContractClass.COLUMN_EXPENSE_PAID_BY+
                " = '"+firstPerson+"' AND "+ContractClass.COLUMN_EXPENSE_PAID_FOR+" = '"+
                secondPerson+"';";
        String queryB = "SELECT SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+") FROM "
                +ContractClass.TABLE_NAME_ALL_EXPENSES+" WHERE "+ContractClass.COLUMN_EXPENSE_PAID_BY+
                " = '"+secondPerson+"' AND "+ContractClass.COLUMN_EXPENSE_PAID_FOR+" = '"+
                firstPerson+"';";

        int amtA = 0,amtB=0;
        Cursor cursor;

        cursor = db.rawQuery(queryA,null);
        if(cursor.moveToFirst()){
            String column = "SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+")";
            do {
                amtA= cursor.getInt(cursor.getColumnIndex(column));
                }while (cursor.moveToNext());
        }

        cursor = db.rawQuery(queryB,null);
        if(cursor.moveToFirst()){
            String column = "SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+")";
            do {
                amtB= cursor.getInt(cursor.getColumnIndex(column));
            }while (cursor.moveToNext());
        }

        return (amtA-amtB);
    }

    public int getPersonExpense(String person){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+") FROM "+
                ContractClass.TABLE_NAME_ALL_EXPENSES+" WHERE "
                +ContractClass.COLUMN_EXPENSE_PAID_FOR+" = '"+person+"';";
        int amt=0;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                amt=cursor.getInt(cursor.getColumnIndex("SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+")"));

            }while (cursor.moveToNext());

        }
        return amt;
    }

    public int getPersonSpent(String person){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+") FROM "+
                ContractClass.TABLE_NAME_ALL_EXPENSES+" WHERE "
                +ContractClass.COLUMN_EXPENSE_PAID_BY+" = '"+person+"';";
        int amt=0;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                amt=cursor.getInt(cursor.getColumnIndex("SUM("+ContractClass.COLUMN_EXPENSE_AMOUNT+")"));

            }while (cursor.moveToNext());

        }
        return amt;
    }




}
