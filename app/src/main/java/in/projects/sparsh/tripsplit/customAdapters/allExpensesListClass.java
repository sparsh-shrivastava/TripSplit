package in.projects.sparsh.tripsplit.customAdapters;

/**
 * Created by sparsh on 28/11/16.
 */
public class allExpensesListClass {
    private String paidBy;
    private String paidFor;
    private String reason;
    private String date;
    private int amount;

    public allExpensesListClass(String paidBy, String paidFor, String reason, String date, int amount) {
        this.paidBy = paidBy;
        this.paidFor = paidFor;
        this.reason = reason;
        this.date = date;
        this.amount = amount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public String getPaidFor() {
        return paidFor;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
