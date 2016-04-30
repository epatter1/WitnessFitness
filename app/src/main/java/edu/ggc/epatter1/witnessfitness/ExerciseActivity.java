package edu.ggc.epatter1.witnessfitness;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import edu.ggc.epatter1.witnessfitness.Model.Exercise;

public class ExerciseActivity extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private ImageView picture;
    private VideoView video;
    private TextView numReps;
    private ViewFlipper viewFlipper;


    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getting values in the exercise fields
        name = (TextView) findViewById(R.id.exerciseName);
        description = (TextView) findViewById(R.id.exerciseDescription);
        picture = (ImageView) findViewById(R.id.exerciseImage);
        video = (VideoView) findViewById(R.id.exerciseVideo);


        Intent i = getIntent();
        currentPosition = i.getIntExtra("position", -1);



        // String retreiveStringName = i.getStringExtra(ExerciseListActivity.nameKey);

        name.setText(i.getStringExtra(ExerciseListActivity.nameKey));
        description.setText(i.getStringExtra(ExerciseListActivity.descriptionKey));
        picture.setImageResource(i.getIntExtra(ExerciseListActivity.pictureKey, -1));
        //TODO find how to set videos dynamically
        video.setVideoPath(String.valueOf(i.getIntExtra(ExerciseListActivity.videoKey, -1)));
//        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.biceps_video);


        //setting toggle on ViewFlipper

        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
        final Button flipper = (Button) findViewById(R.id.toggleButton);
        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewFlipper.getDisplayedChild() == 0) {
                    viewFlipper.setDisplayedChild(1);
                    flipper.setText("Exercise image");
                } else {
                    viewFlipper.setDisplayedChild(0);
                    flipper.setText("Exercise video");
                }
            }
        });

        //TODO add reps and timer with text-to-speech here


        //Next and previous buttons
        //TODO Fix index out of bounds exception

        final Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (currentPosition == ExerciseListActivity.mExercises.size()-1) {
                    nextButton.setEnabled(false);
                    Toast.makeText(ExerciseActivity.this, "Congrats! You are done!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ExerciseListActivity.class);
                    startActivity(i);
                }

                int nextExercisePosition = currentPosition + 1;


                Exercise nextExercise = ExerciseListActivity.mExercises.get(nextExercisePosition);
                //getting names of the prev exercise and setting them to the next one
                name.setText(nextExercise.getName());
                description.setText(nextExercise.getDescription());
                picture.setImageResource(nextExercise.getPicture());
                //TODO add next video
                //video.setVideoPath(nextExercise.getVideo());

                currentPosition = nextExercisePosition;

            }

        });

        final Button previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (currentPosition == 0) {
                    previousButton.setEnabled(false);
                    Toast.makeText(ExerciseActivity.this, "You can't go back! You're at the start silly! :)", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ExerciseListActivity.class);
                    startActivity(i);
                }

                int prevExercisePosition = currentPosition - 1;


                Exercise previousExercise = ExerciseListActivity.mExercises.get(prevExercisePosition);
                //getting names of the current exercise and setting them to the previous one

                name.setText(previousExercise.getName());
                description.setText(previousExercise.getName());
                picture.setImageResource(previousExercise.getPicture());
                //TODO add prev video
                currentPosition = prevExercisePosition;



            }

        });


        //TODO add notes button listener







    }

}
