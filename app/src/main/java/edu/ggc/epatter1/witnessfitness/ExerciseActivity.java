package edu.ggc.epatter1.witnessfitness;

import android.content.Intent;
import android.os.Bundle;
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

import edu.ggc.epatter1.witnessfitness.Model.Exercise;

public class ExerciseActivity extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private ImageView picture;
    private TextView numReps;


    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        name = (TextView) findViewById(R.id.exerciseName);
        description = (TextView) findViewById(R.id.exerciseDescription);
        picture = (ImageView) findViewById(R.id.exerciseImage);


        Intent i = getIntent();
        currentPosition = i.getIntExtra("position", -1);



        // String retreiveStringName = i.getStringExtra(ExerciseListActivity.nameKey);

        name.setText(i.getStringExtra(ExerciseListActivity.nameKey));
        description.setText(i.getStringExtra(ExerciseListActivity.descriptionKey));
        picture.setImageResource(i.getIntExtra(ExerciseListActivity.pictureKey, -1));
        Log.i("Exercise Activity", "Here");

        //Next and previous buttons

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (currentPosition >= ExerciseListActivity.mExercises.size()) {
                    Toast.makeText(ExerciseActivity.this, "Congrats! You are done!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ExerciseListActivity.class);
                    startActivity(i);
                }

                int nextExercisePosition = currentPosition + 1;


                Exercise nextExercise = ExerciseListActivity.mExercises.get(nextExercisePosition);

                name.setText(nextExercise.getName());
                description.setText(nextExercise.getName());
                picture.setImageResource(nextExercise.getPicture());

                currentPosition = nextExercisePosition;

            }

        });





    }

}
