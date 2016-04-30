package edu.ggc.epatter1.witnessfitness;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.ggc.epatter1.witnessfitness.Model.Exercise;

public class EditExerciseActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private Editable numReps;
    private ImageView picture;
    private CameraController cameraController;
    private String TAG = "EditExerciseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.editNameTextView);
        description = (EditText) findViewById(R.id.editDescriptionTextView);

        Intent i = getIntent();

        int retrievePosition = i.getIntExtra("position", -1);
        final Exercise exercise = ExerciseListActivity.mExercises.get(retrievePosition);

        name.setText(exercise.getName());
        description.setText(exercise.getDescription());

        //TODO remove saveButton, but uncommenting for now
//        Button saveButton = (Button) findViewById(R.id.saveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String currentName = name.getText().toString();
//                String currentDescription = description.getText().toString();
//
//                exercise.setName(currentName);
//                exercise.setDescription(currentDescription);
//                Toast.makeText(getApplicationContext(), exercise.getName(), Toast.LENGTH_LONG).show();
//
//                Intent i = new Intent(getApplicationContext(), ExerciseListActivity.class);
//                startActivity(i);
//
//            }
//        });

        //TODO allow user to add take picture/video and upload picture/video here
        cameraController = new CameraController(EditExerciseActivity.this);

        initTakePhotoButton();


    }


    private void initTakePhotoButton() {

        Button takePhotoButton = (Button) findViewById(R.id.takePicButton);
        if (cameraController.hasCamera() && takePhotoButton != null) {
            takePhotoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cameraController.dispatchTakePictureIntent();
                }
            });
        }
        else {
            //TODO toast no camera or grey out the button
            Log.d(TAG, "initTakePhotoButton: need to grey out takePhotoButton.");
        }

    }
}