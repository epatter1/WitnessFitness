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

public class ExerciseActivity extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private ImageView picture;

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


        // String retreiveStringName = i.getStringExtra(ExerciseListActivity.nameKey);

        name.setText(i.getStringExtra(ExerciseListActivity.nameKey));
        description.setText(i.getStringExtra(ExerciseListActivity.descriptionKey));
        picture.setImageResource(i.getIntExtra(ExerciseListActivity.pictureKey, -1));
        Log.i("Exercise Activity", "Here");

        //Next and previous buttons

//        Button nextButton = (Button) findViewById(R.id.nextButton);
//        nextButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                startActivity(new Intent(ExerciseActivity.this, AboutScreen.class));
//                //update current exercise and load this exercise
//            }
//        });

    }
}
