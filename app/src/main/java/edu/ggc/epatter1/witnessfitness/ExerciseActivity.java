package edu.ggc.epatter1.witnessfitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ExerciseActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    ListActivity listActivity = new ListActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        name = (TextView) findViewById(R.id.name);


        Intent i = getIntent();

        int position = i.getIntExtra("position", -1);

        name.setText(listActivity.exercises.get(position).getName());



    }
}