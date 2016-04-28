package edu.ggc.epatter1.witnessfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.ggc.epatter1.witnessfitness.Model.Exercise;

import static edu.ggc.epatter1.witnessfitness.R.layout.activity_list;

public class ExerciseListActivity extends AppCompatActivity {



    ListView exerciseListView;

    public static ArrayList<Exercise> mExercises;
    public ArrayList<String> exercisesNames;
    public static final String nameKey = "name";
    public static final String descriptionKey = "description";
    public static final String pictureKey = "picture";
//    public static final String videoKey = "video";
//    public static final String repsKey = "reps";
//    private static final String notesKey = "notes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_list);
        exerciseListView = (ListView) findViewById(R.id.exerciseListView);

        mExercises = new ArrayList();
        exercisesNames = new ArrayList();



        if (mExercises.size() == 0) {
        createExercises();
        getNames();
        }
//  mExercises.add(new Exercise("Back!", "Move your back", R.drawable.back_exercise));






        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exercisesNames);

        exerciseListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ExerciseActivity.class);
                i.putExtra("position", position);


                String nameVal = mExercises.get(position).getName();
                i.putExtra(nameKey, nameVal);
                String descVal = mExercises.get(position).getDescription();
                i.putExtra(descriptionKey, descVal);
                int picVal = mExercises.get(position).getPicture();
                i.putExtra(pictureKey, picVal);
//                int repsVal = mExercises.get(position).getNumReps();
//                i.putExtra(repsKey, repsVal);
//                String notesVal = mExercises.get(position).getNotes();
//                i.putExtra(notesKey, notesVal);
                startActivity(i);

            }
        });

        exerciseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ExerciseListActivity.this, "on item long click worked!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), EditExerciseActivity.class);
                i.putExtra("position", position);
                startActivity(i);
                return true;
            }
        });



    }
    public void createExercises() {
    // mExercises here

        mExercises.add(new Exercise("Back!", "Move your back", R.drawable.back_exercise));
        mExercises.add(new Exercise("Biceps!", "Flex your biceps!", R.drawable.bicep_exercise));
        mExercises.add(new Exercise("Boxing!", "Deliver a blow!", R.drawable.boxing_exercise));
        mExercises.add(new Exercise("High Knees!", "Run in place keeping your knees above your waist.", R.drawable.high_knees_exercise));
        mExercises.add(new Exercise("Jumping Jacks!", "Jump in the air and spread your legs and arms while landing with your legs and arms together.", R.drawable.jumping_jack_exercise));
        mExercises.add(new Exercise("Knees!", "Bend and straighten your knee.", R.drawable.knee_exercise));
        mExercises.add(new Exercise("Pull-ups!", "Pull your self up until your head is above the bar.", R.drawable.pull_ups_exercise));
        mExercises.add(new Exercise("Push-ups!", "Position your shoulders and heels parallel, bend your elbows, and go down and back up.", R.drawable.push_up_exercise));
        mExercises.add(new Exercise("Quads!", "Flex your quadriceps.", R.drawable.quad_exercise));
        mExercises.add(new Exercise("Sit-ups!", "Lay on your back with your knees bent and flex your abs.", R.drawable.sit_up_exercise));

    }

    public void getNames() {

        for (Exercise e : mExercises) {
            exercisesNames.add(e.getName());
        }

    }

//    public void createExerciseImage() {
//        for (Exercise e: exercisePics) {
//            //add exercise pics to AL
//        }
//    }


}