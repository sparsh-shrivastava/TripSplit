package in.projects.sparsh.tripsplit.customAdapters;

/**
 * Created by sparsh on 24/11/16.
 */
public class all_trips_list_class {
    private String name;
    private int count;
    private String date;


    public all_trips_list_class(String name, int count,String date) {
        this.name = name;
        this.count = count;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }
}
