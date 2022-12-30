package android.example.exercising;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class WorkoutDescription extends AppCompatActivity {
    private VideoView videoView;
    private TextView titleView, descriptionView;

    String exerciseVid = "https://youtu.be/D2W7j-2FwyQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_description);

        Log.d("WorkoutDescription", "inside onCreate");

        // connect to views
        titleView       = findViewById(R.id.description_pg_title);
        descriptionView = findViewById(R.id.workout_description);
        videoView       = findViewById(R.id.video_view);

        // Uri object to refer to url for video, then set the resource to the videoView
        Uri uri = Uri.parse(exerciseVid);
        videoView.setVideoURI(uri);
//videoView.setVideoPath("https://youtu.be/G3yktCpkvK0");
        // Using MediaController object to link with with video
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        mediaController.setMediaPlayer(videoView);
//        videoView.setMediaController(mediaController);
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                Log.d("WorkoutDescription", ""+videoView.getDuration());
////                videoView.seekTo(1);
////                videoView.start();
//            }
         Log.d("WorkoutDescription", ""+videoView.toString());
        videoView.start();

        // retrieve data sent from other class
        titleView.setText(getIntent().getStringExtra("title"));
        descriptionView.setText(getIntent().getStringExtra("explaination"));

        Log.d("WorkoutDescription", "end of onCreate");

    }

}