package android.example.foodiemp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
//this class displays the food image with the details
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //connect to view
        TextView foodTitle = findViewById(R.id.titleDetail);
        ImageView foodImage = findViewById(R.id.foodImageDetail);
        TextView foodDetail = findViewById(R.id.subTitleDetail);


        //Get the title and details from the incoming Intent and set it to the TextViews
        foodTitle.setText(getIntent().getStringExtra("title"));
        foodDetail.setText(getIntent().getStringExtra("details"));

        //Use Glide to load the image into the ImageView
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(foodImage);
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