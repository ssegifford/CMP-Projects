package android.example.exercising;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardioTabFragment extends Fragment {
    private TextView cardio1, cardio2;
    private ImageView image1, image2;
    private CardView cardView1, cardView2;
    private String description, title;

    public CardioTabFragment() {/* Required empty public constructor*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cardio_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("CardioTab", "inside OnViewCreated");

        // connect to views
        cardio1 = getActivity().findViewById(R.id.burpee_text);
        cardio2 = getActivity().findViewById(R.id.stair_text);

        image1 = getActivity().findViewById(R.id.image_burpee);
        image2 = getActivity().findViewById(R.id.image_stair_climber);

        cardView1 = getActivity().findViewById(R.id.cardView);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                burpeeWorkout(v);
            }
        });

        cardView2 = getActivity().findViewById(R.id.cab_card2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stairClimberWorkout(v);
            }
        }); Log.d("CardioTab", "end of OnViewCreated");
    }


    public void burpeeWorkout(View view) {
        Log.d("CardioTab", "inside burpeeWorkout method");
        title       = "BURPEE VARIATIONS";
        description = "Burpees can be done in a wide variation. For the most basic type, you place the palm of your hands on the floor angling "+
                "your body to 180 degrees.\nLuckily for you we won't be taking it so easy here. The more intense the workout, the better the results" +
                "so here are a few toughter variations of burpees that you'll be doing:\nBURPEE KICK THRU\nBURPEE MOUNTAIN CLIMBER\nBURPEE PUSH UP" +
                " ALT ARM & LEG RAISE\nSIDE TO SIDE MED BALL BURPEES\nBURPEE LUNGES\nDB DOUBLE SNATCH BURPEES\nSIDE SHUFFLE BURPEES\nCLOSE TO WIDE PUSH UP";

        Intent intent = new Intent(getContext(), WorkoutDescription.class);
        intent.putExtra("explaination", description);
        intent.putExtra("title", title);

        // ask how I would send video

        startActivity(intent);
        Log.d("CardioTab", "end of burpeeWorkout method");
    }


    public void stairClimberWorkout(View view) {
        Log.d("CardioTab", "inside stairClimberWorkout");
        title       = "Stair Climber";
        description = "Stair climbers can be used in a multitude of ways but ultimately following the same concept.\nThe best part is you can do a" +
                "workout at home/outside, or you can use an actual stair climber machine at the gym.\nThe speed you use on the machine will gauge the " +
                "intensity of your workout.\nIf you're using stairs then you begin at the bottom of the steps and begin to run up the steps at your own speed." +
                "\nIf you're using a stair climber in the gym then you set the speed you desire on the machine and begin your incline.\nWhether you decide to" +
                "use steps or a machine in the gym, this in an effective workout.\n";

        Intent intent = new Intent(getContext(), WorkoutDescription.class);
        intent.putExtra("title", title);
        intent.putExtra("explaination",description);
        startActivity(intent);
        Log.d("CardioTab", "end of stairClimberWorkout");

    }


}