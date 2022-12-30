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


public class LegsTabFragment extends Fragment {
    private TextView legs1, legs2;
    private ImageView image1, image2;
    private CardView cardView1, cardView2;
    private String description, title;

    public LegsTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_legs_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("LegsTab", "inside OnViewCreated");

        // connect to views
        legs1 = getActivity().findViewById(R.id.goblet_text);
        legs2 = getActivity().findViewById(R.id.hip_march);

        image1 = getActivity().findViewById(R.id.image_goblet);
        image2 = getActivity().findViewById(R.id.image_hip_raise);

        cardView1 = getActivity().findViewById(R.id.leg_card1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gobletWorkout(v);
            }
        });

        cardView2 = getActivity().findViewById(R.id.leg_card2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marchingBridges(v);
            }
        }); Log.d("LegsTab", "end of OnViewCreated");
    }

    private void gobletWorkout(View v) {
        Log.d("LegsTab", "inside gobletWorkout method");
        title       = "GOBLET SQUATS";
        description = "Use both hands to hold the dumbbell underneath the top of the weight. Make sure to hold the dumbbell directly against your" +
                " chest and keep it in contact with your chest throughout the entire motion.\nInhale and get into a deep squat position with your " +
                "torso upright. Throughout this process keep your core.\nExhale as you stand up straight.\nMake sure to keep your torso upright and a firm " +
                "position with your legs";

        Intent intent = new Intent(getContext(), WorkoutDescription.class);
        intent.putExtra("explaination", description);
        intent.putExtra("title", title);
        startActivity(intent);
        Log.d("LegsTab", "end of gobletWorkout method");
    }

    private void marchingBridges(View v) {
        Log.d("LegsTab", "inside marchingBridges method");
        title       = "MARCHING BRIDGES";
        description = "To do marching bridges you want to first lay down on your back on the floor or mat.\nYour knees should then be bent with your" +
                "feet flat on the ground.\nWhen you're ready lift your feet off the floor and if you want to increase the difficulty you can place" +
                "a dumbbell on your hips, nothing too heavy. Straighten out from your hips to your knees.\nRaise one of your legs off the ground as if" +
                "to make a marching move.\nLower that leg and and raise the same marching motion with the other leg simultaneously.";

        Intent intent = new Intent(getContext(), WorkoutDescription.class);
        intent.putExtra("explaination", description);
        intent.putExtra("title", title);
        startActivity(intent);
        Log.d("LegsTab", "end of marchingBridges method");
    }




}