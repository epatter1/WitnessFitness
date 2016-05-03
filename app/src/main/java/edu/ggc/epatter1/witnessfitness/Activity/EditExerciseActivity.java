package edu.ggc.epatter1.witnessfitness.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import edu.ggc.epatter1.witnessfitness.Support.CameraController;
import edu.ggc.epatter1.witnessfitness.Model.Exercise;
import edu.ggc.epatter1.witnessfitness.Model.ExerciseSequence;
import edu.ggc.epatter1.witnessfitness.R;

public class EditExerciseActivity extends AppCompatActivity {

    private static final String TAG = "EditExerciseActivity";

    /* Model via Dependency Injection */
    private Exercise mExercise;


    /* View Layout widgets */
    private EditText name;
    private EditText description;
    private EditText repetition;
    private CheckBox isTimed;
    private EditText duration;
    private EditText note;

    private ImageView picture;
    private VideoView video;


    /* Class for holding Camera Intent configuration and control */
    private CameraController cameraController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //TODO allow user to add take picture/video and upload picture/video here
        cameraController = new CameraController(EditExerciseActivity.this);

        rFind();
        initEditFields();
        initTakePhotoButton();

    }

    private void rFind() {
        name = (EditText) findViewById(R.id.editNameTextView);
        description = (EditText) findViewById(R.id.editDescriptionTextView);
        repetition = (EditText) findViewById(R.id.editRepsTextView);
        isTimed = (CheckBox) findViewById(R.id.edit_istimed_checkbox);
        duration = (EditText) findViewById(R.id.editDurationTextView);
        note = (EditText) findViewById(R.id.editNotesTextView);
    }

    private void initEditFields() {

        mExercise = ExerciseSequence.getInstance(this).getCurrentExercise();

        name.setText(mExercise.getName());
        description.setText(mExercise.getDescription());


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

        repetition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mExercise.setNumReps(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This space intentionally left blank
            }
        });

        isTimed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mExercise.setIsTimed(isChecked);
            }
        });

        duration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mExercise.setDuration(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This space intentionally left blank
            }
        });

        note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mExercise.setNotes(s.toString());
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