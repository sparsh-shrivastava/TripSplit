package in.projects.sparsh.tripsplit.customAdapters;

/**
 * Created by sparsh on 29/11/16.
 */
public class summaryListClass {
    private String giver;
    private String reciever;
    private int amount;

    public summaryListClass(String giver, String reciever, int amount) {
        this.giver = giver;
        this.reciever = reciever;
        this.amount = amount;
    }

    public String getGiver() {
        return giver;
    }

    public String getReciever() {
        return reciever;
    }

    public int getAmount() {
        return amount;
    }
}
