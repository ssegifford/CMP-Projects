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


public class AbsTabFragment extends Fragment {

    private TextView legs1, legs2;
    private ImageView image1, image2;
    private CardView cardView1, cardView2;
    private String description, title;

    public AbsTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abs_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("AbsTab", "inside OnViewCreated");

        // connect to views
        legs1 = getActivity().findViewById(R.id.cross_knee_text);
        legs2 = getActivity().findViewById(R.id.vertical_leg_crunch);

        image1 = getActivity().findViewById(R.id.image_cross_knee);
        image2 = getActivity().findViewById(R.id.image_vertical_leg_crunch);

        cardView1 = getActivity().findViewById(R.id.abs_card1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {crossKneeWorkout(v);}
        });

        cardView2 = getActivity().findViewById(R.id.abs_card2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verticalCrunchWorkout(v);
            }
        }); Log.d("AbsTab", "end of OnViewCreated");
    }


    private void crossKneeWorkout(View v) {
        Log.d("AbsTab", "inside crossKneeWorkout method");
        String videoUrl = "https://youtu.be/8fN356WKqPo";
        title       = "CROSS KNEE PLANK";
        description = "Begin in a plank position with your elbows on the floor and your shoulders align with your elbows. Your feet should be" +
                "together, and your body in a straight line.\nPull your left knee under your belly to touch your right elbow, then release back " +
                "to start position.\nNow pull your right knee under your belly to touch your left elbow, then release back to start position.\n" +
                "Repeat this until you've reached your desired number of reps. Make sure to keep your abs tight and maintain body control.";

        Intent intent = new Intent(getContext(), WorkoutDescription.class);
        intent.putExtra("explaination", description);
        intent.putExtra("title", title);
        startActivity(intent);
        Log.d("AbsTab", "end of crossKneeWorkout method");
    }

    private void verticalCrunchWorkout(View v) {
        Log.d("AbsTab", "inside verticalCrunchWorkout method");
        String videoUrl = "https://youtu.be/79mRoHMwBaM";
        title       = "VERTICAL LEGS CRUNCH";
        description = "Begin by laying down on your back and raising your feet to a 90 degree angle, or perpendicular to the floor.\nPlace your " +
                "hands behind your neck or point them straight forward. Then you want to slowly raise your upper body lifting your shoulder blades" +
                " off the floor, but do not use your hands to raise your upper body, use your core.\nExhale on the upward motion.Make sure to keep" +
                " your legs raised and pointed straight to the sky. Maintain control of your legs, do not allow them to swing side to side";

        Intent intent = new Intent(getContext(), WorkoutDescription.class);
        intent.putExtra("explaination", description);
        intent.putExtra("title", title);
        startActivity(intent);
        Log.d("AbsTab", "end of verticalCrunchWorkout method");
    }

}