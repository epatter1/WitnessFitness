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
import java.util.List;

import edu.ggc.epatter1.witnessfitness.Model.Exercise;
import edu.ggc.epatter1.witnessfitness.Model.ExerciseSequence;

import static edu.ggc.epatter1.witnessfitness.R.layout.activity_list;

public class ExerciseListActivity extends AppCompatActivity {


    ListView exerciseListView;

    public static List<Exercise> mExercises;
    private List<String> exercisesNames;
    public static final String nameKey = "name";
    public static final String descriptionKey = "description";
    public static final String pictureKey = "picture";
    public static final String videoKey = "video";
//    public static final String repsKey = "reps";
//    private static final String notesKey = "notes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_list);
        exerciseListView = (ListView) findViewById(R.id.exerciseListView);

        ExerciseSequence sequence = ExerciseSequence.getInstance();
        mExercises = ExerciseSequence.getInstance().getExercises();

        ArrayList<String> exerciseNames = new ArrayList<>();

        for (int i = 0; i < sequence.getExercises().size(); i++) {
            exerciseNames.add(mExercises.get(i).getName());
        }


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
                //int picVal = mExercises.get(position).getPicture();
                //i.putExtra(pictureKey, picVal);
                // int vidVal = mExercises.get(position).getVideo();
                // i.putExtra(videoKey, vidVal);
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