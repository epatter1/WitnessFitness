package edu.ggc.epatter1.witnessfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {



    ListView exerciseListView;

    ArrayList<Exercise> exercises;
    ArrayList<String> exercisesNames = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exerciseListView = (ListView) findViewById(R.id.exerciseListView);

        exercises.add(new Exercise("Exercise","This is descripiton of exercise1"));
        exercises.add(new Exercise("Exercise","This is descripiton of exercise1"));
        exercises.add(new Exercise("Exercise1","This is descripiton of exercise1"));
        exercises.add(new Exercise("Exercise1","This is descripiton of exercise1"));
        exercises.add(new Exercise("Exercise1","This is descripiton of exercise1"));
        exercises.add(new Exercise("Exercise1","This is descripiton of exercise1"));


        exercisesNames = new ArrayList();

        createDummyExercises();

        getNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exercisesNames);

        exerciseListView.setAdapter(adapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ExerciseActivity.class);
                i.putExtra("position", position);


                String name = exercises.get(position).getName();
                i.putExtra("name", name);

                startActivity(i);

            }
        });

    }

    public void createDummyExercises() {

        for(int i = 0; i < 100; i++) {
            exercises.add(new Exercise("Exercise" + String.valueOf(i), "Description" + String.valueOf(i)));
        }

    }

    public void getNames() {

        for (Exercise e : exercises) {
            exercisesNames.add(e.getName());
        }

    }

//    public void createExerciseImage() {
//        for (Exercise e: exercisePics) {
//            //add exercise pics to AL
//        }
//    }


}