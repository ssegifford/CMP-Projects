package android.example.scorecounter2;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {
private TextView winningMessage;
private EditText mapSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        // receiving intent
        Intent intent = getIntent();
        String winnerReceived = intent.getStringExtra(MainActivity.keyMessage);
        winningMessage = findViewById(R.id.winner);
        winningMessage.setText(winnerReceived);
        mapSearch       = findViewById(R.id.editText_maps);
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

    public void playAgain(View view){       //this will return to main activity
        finish();
    }


    //methods creating different activities using implicit intents without using permissions
    public void searchMap(View view) {
        String loc = mapSearch.getText().toString();
        Uri geoloc = Uri.parse("geo:0,0?q="+loc);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoloc);
        startActivity(mapIntent);
        /* ask about this, only works if check if "== null" instead of "!= null"
        if(mapIntent.resolveActivity(getPackageManager()) == null)        {
            startActivity(mapIntent);
        } */
    }

    public void shareText(View view) {
       String winner = winningMessage.getText().toString();
       String mimeType = "text/plain";

       ShareCompat.IntentBuilder
               .from(this)                          //method chaining by not using ";"
               .setType(mimeType)                   //sets data type
               .setChooserTitle("Choose an app")    //sets title that you see when choosing app
               .setText(winner)                     //this sets the message that will be sent
               .startChooser();                     // this starts the chooser
    }

    public void phoneCall(View view) {
        Intent call = new Intent(Intent.ACTION_DIAL);       //doesn't need manifest permissions
        startActivity(call);
    }
}