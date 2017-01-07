package in.projects.sparsh.tripsplit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import in.projects.sparsh.tripsplit.customAdapters.tripViewPagerClass;

public class comboActivity extends AppCompatActivity {
    String nameOfTrip;
    int numberOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        Intent here = getIntent();
        nameOfTrip = here.getStringExtra("trip name");
        numberOfPeople = here.getIntExtra("count", 1);

        FloatingActionButton floatAddExpenseButton = (FloatingActionButton) findViewById(R.id.combo_activity_new_expense);
        assert floatAddExpenseButton!=null;
        floatAddExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(comboActivity.this,new_expense_activity.class);
                intent.putExtra("trip name",nameOfTrip);
                intent.putExtra("count", numberOfPeople);
                startActivity(intent);


            }
        });


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        tripViewPagerClass adapter = new tripViewPagerClass(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.combo_activity_tabs);

        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.combo_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.combo_activity_goto_people:
                Intent intent = new Intent(comboActivity.this,peoples_activity.class);
                intent.putExtra("trip name",nameOfTrip);
                intent.putExtra("count", numberOfPeople);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
