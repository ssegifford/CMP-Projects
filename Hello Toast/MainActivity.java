package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //create view(s) to use with xml views
    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount =  (TextView) findViewById(R.id.show_count);
   /*  R.id calls to id tags for a View resource so you have to cast the specific view type
   *   The findViewById call takes the ID of a view as its parameter and returns the View
   *   onCreate is used to set the content view of the screen to the XML layout. You can also use it to
   *   get references to other UI elements in the layout.    */
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
        /*
        To show a string resource we make an instance of Toast and we equate that to the string resource for
        the message we want to use, in this case toast_message.
        .show() shows the View for the specified duration, only SHORT or LONG.  R.string calls to string resources
         */
    }

    public void countUp(View view) {
    mCount++;

 /* mShowCount becomes int value mCount converted to a string since it's a TextView */
    if(mShowCount != null)  mShowCount.setText(Integer.toString(mCount));
    }
}