package edu.ggc.epatter1.witnessfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static edu.ggc.epatter1.witnessfitness.R.layout.activity_list;

public class ExerciseListActivity extends AppCompatActivity {



    ListView exerciseListView;

    public ArrayList<Exercise> exercises;
    public ArrayList<String> exercisesNames;
    public static final String nameKey = "name";
    public static final String descriptionKey = "description";
    public static final String pictureKey = "picture";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_list);
        exerciseListView = (ListView) findViewById(R.id.exerciseListView);

        exercises = new ArrayList();
        exercisesNames = new ArrayList();

      createExercises();

      getNames();



        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exercisesNames);

        exerciseListView.setAdapter(adapter);



        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ExerciseActivity.class);
                i.putExtra("position", position);


                String nameVal = exercises.get(position).getName();
                i.putExtra(nameKey, nameVal);
                String descVal = exercises.get(position).getDescription();
                i.putExtra(descriptionKey, descVal);
                int picVal = exercises.get(position).getPicture();
                i.putExtra(pictureKey, picVal);
                startActivity(i);

            }
        });

    }
    public void createExercises() {
    //TODO hardcode exercises here

        exercises.add(new Exercise("Back!", "Move your back", R.drawable.back_exercise));
        exercises.add(new Exercise("Biceps!", "Flex your biceps!", R.drawable.bicep_exercise));
        exercises.add(new Exercise("Boxing!", "Deliver a blow!", R.drawable.boxing_exercise));
        exercises.add(new Exercise("High Knees!", "Run in place keeping your knees above your waist.", R.drawable.high_knees_exercise));
        exercises.add(new Exercise("Jumping Jacks!", "Jump in the air and spread your legs and arms while landing with your legs and arms together.", R.drawable.jumping_jack_exercise));
        exercises.add(new Exercise("Knees!", "Bend and straighten your knee.", R.drawable.knee_exercise));
        exercises.add(new Exercise("Pull-ups!", "Pull your self up until your head is above the bar.", R.drawable.pull_ups_exercise));
        exercises.add(new Exercise("Push-ups!", "Position your shoulders and heels parallel, bend your elbows, and go down and back up.", R.drawable.push_up_exercise));
        exercises.add(new Exercise("Quads!", "Flex your quadriceps.", R.drawable.quad_exercise));
        exercises.add(new Exercise("Sit-ups", "Lay on your back with your knees bent and flex your abs.", R.drawable.sit_up_exercise));


//        for(int i = 0; i < 20; i++) {
//            exercises.add(new Exercise("Exercise" + String.valueOf(i), "Description" + String.valueOf(i)));
//        }

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