package in.projects.sparsh.tripsplit.customAdapters;

/**
 * Created by sparsh on 30/11/16.
 */
public class peoplesListClass {
    private String name;
    private int totalExpense;
    private int totalSpent;

    public peoplesListClass(String name, int totalExpense, int totalSpent) {
        this.name = name;
        this.totalExpense = totalExpense;
        this.totalSpent = totalSpent;
    }

    public String getName() {
        return name;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public int getTotalSpent() {
        return totalSpent;
    }
}
