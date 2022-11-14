package android.example.foodiemp5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;

/***
 * Main Activity for the Material Me app, a mock sports news application
 * with poor design choices.
 */
public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Food> mFoodData;
    private FoodAdapter mAdapter;
    private int grid_column_count;          //for recyclerview gridlayout
    private FloatingActionButton fab;
    private Intent intent;
    protected static final String ACTION_CUSTOM_BROADCAST = "com.example.I_AM_HOME";
    private MyReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Broadcast receiver must be registered with intentFilter for receiver to check for actions
        receiver = new MyReceiver();
        registerReceiver(receiver, addIntentFilter());

        // Retrieve value from integer xml
        grid_column_count = getResources().getInteger(R.integer.grid_column_count);

        // Initialize the RecyclerView and FAB.
        mRecyclerView = findViewById(R.id.recyclerView);
        fab           = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddItemActivity();
            }
        });

        // Set the Layout Manager for RecyclerView.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, grid_column_count));

        // Initialize the ArrayList that will contain the data.
        mFoodData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new FoodAdapter(this, mFoodData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();
    }

    // METHOD FOR BROADCAST RECEIVER -   create intent filter and add actions
    private IntentFilter addIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_CUSTOM_BROADCAST);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        return intentFilter;
    }

    public void sendTheBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction(ACTION_CUSTOM_BROADCAST);
        sendBroadcast(intent);
    }


    private void startAddItemActivity() {
        intent = new Intent(this, AddFoodItem.class);
        startActivity(intent);
    }

    /** Initialize the sports data from resources.  */
    @SuppressLint("NotifyDataSetChanged")
    private void initializeData() {
        // Get the resources from the XML file.
        String [] foodList = getResources().getStringArray(R.array.food_titles);
        String [] foodInfo = getResources().getStringArray(R.array.food_info);
/*  When I tried to use an array for the detail it would not work, look into that    */

  /*  A TypedArray allows you to store an array of other XML resources. Using a TypedArray, you can obtain the image resources as well as the sports
      title and information by using indexing in the same loop.  */
        TypedArray foodImageResources = getResources().obtainTypedArray(R.array.food_images);

        // Clear the existing data (to avoid duplication).
        mFoodData.clear();

        // Create the ArrayList of Food objects with titles and information about each sport.
        for(int i=0;i<foodList.length;i++){
            mFoodData.add(new Food(foodList[i],foodInfo[i],
                    foodImageResources.getResourceId(i,0)));
        }

        //Clean up the data in the typed array once you have created the food data ArrayList
        foodImageResources.recycle();

        // Need to notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}