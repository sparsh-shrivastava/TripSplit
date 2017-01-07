package in.projects.sparsh.tripsplit.DBPackage;

/**
 * Created by sparsh on 25/11/16.
 */
public final class ContractClass  {

    private ContractClass(){}
    //database names
    public static final String DBNAME_ALL = "All_trips.db";
    public static final String DBNAME_TRIP="_TRIP_DATABASE.db";

    //table names
    public static final String TABLE_NAME_ALL_TRIPS= "all_trips";
    public static  String TABLE_NAME_ALL_EXPENSES = "expenses";
    public static String TABLE_NAME_MEMBERS = "members";

    //columns for expense
    public static final String COLUMN_EXPENSE_id = "id";
    public static final String COLUMN_EXPENSE_REASON="reason";
    public static final String COLUMN_EXPENSE_DATE="date";
    public static final String COLUMN_EXPENSE_PAID_BY="paid_by";
    public static final String COLUMN_EXPENSE_PAID_FOR="paid_for";
    public static final String COLUMN_EXPENSE_AMOUNT="amount";

    //columns for members
    public static final String COLUMN_MEMBERS_NAME="name";
    public static final String COLUMN_MEMBERS_ID="id";

    //columns for all trips
    public static final String COLUMN_ALLTRIPS_MEMBERS="members_count";
    public static final String COLUMN_ALLTRIPS_NAME="name";
    public static final String COLUMN_ALLTRIPS_ID="id";
    public static final String COLUMN_ALLTRIPS_DATE="date_created";



}
