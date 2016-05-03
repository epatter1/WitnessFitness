package edu.ggc.epatter1.witnessfitness;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import edu.ggc.epatter1.witnessfitness.Model.Exercise;
import edu.ggc.epatter1.witnessfitness.Model.ExerciseSequence;

public class EditExerciseActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private EditText numReps;
    private ImageView picture;
    private CameraController cameraController;
    private String TAG = "EditExerciseActivity";
    
    private Exercise mExercise;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.editNameTextView);
        description = (EditText) findViewById(R.id.editDescriptionTextView);


        mExercise = ExerciseSequence.getInstance(this).getCurrentExercise();

        name.setText(mExercise.getName());
        description.setText(mExercise.getDescription());

        //TODO allow user to add take picture/video and upload picture/video here
        cameraController = new CameraController(EditExerciseActivity.this);

        initEditFields();
        initTakePhotoButton();

    }

    private void initEditFields() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mExercise.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This space intentionally left blank
            }
        });
        
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mExercise.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This space intentionally left blank
            }
        });
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
        } else {
            //TODO toast no camera or grey out the button
            Log.d(TAG, "initTakePhotoButton: need to grey out takePhotoButton.");
        }

    }


}