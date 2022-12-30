package android.example.exercising;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class Categories extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout.Tab cardioTab, legsTab, armsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Categories", "inside onCreate");
        setContentView(R.layout.activity_categories);

        // connect to views
//        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
//Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.
//        setSupportActionBar(toolbar);

        Log.d("Categories", "inside onCreate, will add tabs");

        // add tabs and set title
        tabLayout.addTab(tabLayout.newTab().setText("CARDIO"));
        tabLayout.addTab(tabLayout.newTab().setText("LEGS"));
        tabLayout.addTab(tabLayout.newTab().setText("ABS"));
        Log.d("Categories", "inside onCreate, added tabs");

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);     //updates viewpager to current tab
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        Log.d("Categories", "end of onCreate");

    }
}