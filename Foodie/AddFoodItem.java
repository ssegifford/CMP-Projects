package android.example.foodiemp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddFoodItem extends AppCompatActivity {
    EditText newFoodFinder;
    EditText foodDetailsFinder;
    String newFood, foodDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        // connect to views
        newFoodFinder           = findViewById(R.id.ET_food);
        foodDetailsFinder       = findViewById(R.id.ET_details);


    }

    public void addNewFood(View v){
        newFood     = newFoodFinder.getText().toString();
        foodDetails = foodDetailsFinder.getText().toString();
        Log.d("message", "This is the new food, " +newFood);
        Log.d("message2", "This the details, " +foodDetails);

        finish();
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
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}