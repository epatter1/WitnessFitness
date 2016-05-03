package edu.ggc.epatter1.witnessfitness.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ViewFlipper;
import android.net.Uri;

import edu.ggc.epatter1.witnessfitness.Model.Exercise;
import edu.ggc.epatter1.witnessfitness.Model.ExerciseSequence;
import edu.ggc.epatter1.witnessfitness.R;
import edu.ggc.epatter1.witnessfitness.RecyclerView.SortListActivity;

public class ExerciseActivity extends AppCompatActivity {

    private static final String TAG = "ExerciseActivity";
    
    private TextView name;
    private TextView description;
    private CheckBox mIsTimed;
    private TextView numReps;
    private TextView mDuration;
    private TextView timer;
    private ImageView picture;
    private VideoView video;

    private ViewFlipper viewFlipper;
    private Exercise mExercise;
    private int mediaToDisplay = Exercise.IMAGE;
    private int mediaInteger = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mExercise = ExerciseSequence.getInstance(this).getCurrentExercise();
        // getting values in the exercise fields
        name = (TextView) findViewById(R.id.exerciseName);
        description = (TextView) findViewById(R.id.exerciseDescription);
        mIsTimed = (CheckBox)findViewById(R.id.checkBoxIsTimed);
        numReps = (TextView)findViewById(R.id.exerciseReps);
        mDuration = (TextView)findViewById(R.id.exerciseDuration);
        timer = (TextView) findViewById(R.id.timerTextView);


        picture = (ImageView) findViewById(R.id.exerciseImage);

        //setting video to display
        video = (VideoView) findViewById(R.id.exerciseVideo);
        String vidPath = "android.resource://" + getPackageName() + "/" + R.raw.biceps_video;
        video.setVideoURI(Uri.parse(vidPath));
        video.start();


        initButtons();
        initViewFlipper();
        updateUI();


    }

    private void updateUI() {
        name.setText(mExercise.getName());
        description.setText(mExercise.getDescription());
        mIsTimed.setChecked(false);

        //TODO Conditional based on isTimed

        // Going to allow for default image in the drawable project. If the we are not
        // Using drawable, then the string value will not have in integer. If not an integer, the
        // we will retrieve from internal storage or display nothing.
        Log.d(TAG, "updateUI: getMediaIntegerValue:" + getMediaInteger());
        if (checkMediaNameIsInteger()) {
            if (getMediaInteger() != -1) {
                Log.d(TAG, "updateUI: attempt to restore picture");
                picture.setImageResource(getMediaInteger());

            }
        } else {
            Log.d(TAG, "updateUI: even though it is not working, we are trying to call from internal storage");
            //TODO Get from internal storage
        }
    }

    private void initViewFlipper() {
        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
        final Button flipper = (Button) findViewById(R.id.toggleButton);
        assert flipper != null;
        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
                video.start();
            }
        });
    }

    private void initButtons() {
        Button noteButton = (Button)findViewById(R.id.noteButton);

        assert noteButton != null;
        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, mExercise.getNotes(), Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        Button startButton = (Button) findViewById(R.id.startButton);
        assert startButton != null;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(5000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText("" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        timer.setText("Done!");
                    }
                }.start();
            }
        });

        Button editButton = (Button)findViewById(R.id.editButton);

        assert editButton != null;
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, EditExerciseActivity.class);
                startActivity(intent);
            }
        });

        Button skipButton = (Button)findViewById(R.id.skipButton);

        assert skipButton != null;
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO handle dialog.
                // if cancel just return
                // else do below.
                ExerciseSequence.getInstance(ExerciseActivity.this).incrementCurrentExercise();
                restartActivity();
            }
        });

        Button restartButton = (Button)findViewById(R.id.restartButton);

        assert restartButton != null;
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExerciseSequence.getInstance(ExerciseActivity.this).restart();
                restartActivity();
            }
        });

        Button editExercisesButton = (Button)findViewById(R.id.editExercisesButton);

        assert editExercisesButton != null;
        editExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, SortListActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean checkMediaNameIsInteger() {
        String mediaName = "";
        if (mediaToDisplay == Exercise.IMAGE) {
            mediaName = mExercise.getPicture();
        } else if ( mediaToDisplay == Exercise.VIDEO ) {
            mediaName = mExercise.getVideo();
        } else {
            //Intentionally left blank as it is Exercise.NONE
            return false;
        }

        try {
            mediaInteger = Integer.parseInt(mediaName);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    private int getMediaInteger() {
        return mediaInteger;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void restartActivity() {
        Intent intent = new Intent(ExerciseActivity.this, ExerciseActivity.class);
        startActivity(intent);
    }

}