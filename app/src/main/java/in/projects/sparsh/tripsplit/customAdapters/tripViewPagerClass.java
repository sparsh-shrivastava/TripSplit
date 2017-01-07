package in.projects.sparsh.tripsplit.customAdapters;

/**
 * Created by sparsh on 30/11/16.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import in.projects.sparsh.tripsplit.summaryFragment;
import in.projects.sparsh.tripsplit.allExpensesFragment;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class tripViewPagerClass extends FragmentPagerAdapter {

    public tripViewPagerClass(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new allExpensesFragment();
        } else {
            return new summaryFragment();
        }
    }

    @Override
    public int getCount() {

        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "All Expenses";
        }
        else{
            return "Summary";
        }
    }
}