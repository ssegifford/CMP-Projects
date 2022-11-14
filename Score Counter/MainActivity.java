package android.example.scorecounter2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int      count1 = 0, count2 = 0;
    private TextView team1Score, team2Score;
    private TextView Spartans, Vikings;
    protected static String keyMessage;
    private static final String KEY_count1 = "Score1_KEY";
    private static final String KEY_count2 = "Score2_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
        count1 = savedInstanceState.getInt(KEY_count1, count1);
        count2 = savedInstanceState.getInt(KEY_count2, count2);
        }

        team1Score = (TextView) findViewById(R.id.team1_score);
        team1Score.setText(Integer.toString(count1));
        team2Score = (TextView) findViewById(R.id.team2_score);
        team2Score.setText(Integer.toString(count2));
        Spartans   = findViewById(R.id.team1View);
        Vikings    = findViewById(R.id.team2View);
    }

    //rotation method, to save state for rotations
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_count1, count1);
        outState.putInt(KEY_count2, count2);
    }


    public void incrementSpartans(View view){       //buttons are views so View must be a parameter since this is a method for when button is clicked
        count1++;
        /* mShowCount becomes int value mCount converted to a string since it's a TextView */
        if(team1Score != null)  team1Score.setText(Integer.toString(count1));
        if(count1 == 5) winnerActivity(Spartans);
    }

    public void incrementVikings(View view){
        count2++;
        /* mShowCount becomes int value mCount converted to a string since it's a TextView */
        if(team2Score != null)  team2Score.setText(Integer.toString(count2));
        if(count2 == 5) winnerActivity(Vikings);
    }

    //creating a new activity (screen) using explicit intent
    public void winnerActivity(View view){
        String winner = "";
        int difference = Math.abs(count1 - count2);

        if(count1 > count2)     winner = "SPARTANS WON BY " + difference + "! \n\"THIS IS SPARTAAAAA !!\"";
        else                    winner = "VIKINGS WON BY " + difference + "! \n\"YOU SEE, I GUIDED MY FATE!!\"";

        //creating explicit intent and directly calling the class and then starting activity
        Intent intent  = new Intent(this, WinnerActivity.class);
        intent.putExtra(keyMessage, winner);
        // startActivity(intent); if i don't need a result, otherwise I need a result so I use startActivityForResult instead
        startActivityForResult(intent,count1);
    }

    // this method is for returned result
    public void onActivityResult(int resultCode, int requestCode, @Nullable Intent data){
        count1 = 0; count2 = 0;
        team1Score.setText(Integer.toString(0));
        team2Score.setText(Integer.toString(0));
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