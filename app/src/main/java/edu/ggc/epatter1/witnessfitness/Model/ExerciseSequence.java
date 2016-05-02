package edu.ggc.epatter1.witnessfitness.Model;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.ggc.epatter1.witnessfitness.R;
import edu.ggc.epatter1.witnessfitness.SharedPreference;

public class ExerciseSequence {

    //start singleton design pattern
    //to use the singleton pattern, do one of the following:
    // 1. ExerciseSequence.getInstance().getExercises(); // return ArrayList of Exercises
    // 2. ExerciseSequence.getInstance().getCurrentExercise(); //return current Exercise


    private static ExerciseSequence ourInstance;

    public static ExerciseSequence getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new ExerciseSequence(context.getApplicationContext());

        }
        return ourInstance;
    }
    //end singleton design pattern

    private static final String KEY_UUIDMASTERLIST = "uuidmasterlist";
    private static final String UUIDSTRING = "AABE38F4-7673-4434-B470-143524D8E738";
    private static final boolean DEBUG_NOIMAGE = false;
    //start object class
    private List<Exercise> mExercises;
    private int currentExercise = 0;
    private Context mContext;

    private ExerciseSequence(Context context) {
        mContext = context;
        mExercises = new ArrayList<>();

        SharedPreference sharedPreference = new SharedPreference(UUID.fromString(UUIDSTRING));
        //Alternate between the 2 following lines to clearlist and create or pull from storage
        String flippableString = KEY_UUIDMASTERLIST;
        //String flippableString = "foobar";
        if (sharedPreference.hasKey(mContext, flippableString)) {
            this.restoreExercises();
        } else {
            sharedPreference.clearList(mContext);
            createExercises();
        }
    }

    private void restoreExercises() {
        SharedPreference sharedPreference = new SharedPreference(UUID.fromString(UUIDSTRING));
        String savedIdsString = sharedPreference.getStringValue(mContext, KEY_UUIDMASTERLIST);

        String[] uuidArr = TextUtils.split(savedIdsString, ",");
        for (int i = 0; i < uuidArr.length - 1; i++) {
            Exercise exercise = new Exercise(mContext, UUID.fromString(uuidArr[i]));
            exercise.restoreData();

            //WARNING: we do NOT want to call my add
            //method containing savedIds. Just call regular
            //mExercises.add
            mExercises.add(exercise);
        }

    }

    public List<Exercise> getExercises() {
        return mExercises;
    }


    public Exercise getCurrentExercise(){
        return mExercises.get(currentExercise);
    }

    public void incrementCurrentExercise() {
        if ( currentExercise < mExercises.size() - 1) {
            currentExercise++;
        }
    }

    public void restart() {
        currentExercise = 0;
    }

    public void add (Exercise e) {
        mExercises.add(e);
        savedIds();

    }

    private void savedIds() {
        SharedPreference sharedPreference = new SharedPreference(UUID.fromString(UUIDSTRING));

        ArrayList<String> uuidAL = new ArrayList<>();
        for (Exercise e: this.getExercises()) {
            uuidAL.add(e.getId().toString());
        }

        String savedIdsString = TextUtils.join(",", uuidAL);

        sharedPreference.saveString(mContext, KEY_UUIDMASTERLIST, savedIdsString);
    }

    public void createExercises() {
        // mExercises here

        if (DEBUG_NOIMAGE) {
            for (int i = 0; i < 100; i++) {
                Exercise exercise = new Exercise(mContext);
                exercise.setName("Exercise #" + i);
                exercise.setDescription("Description #" + i);
                exercise.setIsTimed(i % 2 == 0);
                add(exercise);
            }
        } else {

            mExercises.add(new Exercise(mContext, "Back!", "Move your back", R.drawable.back_exercise, R.raw.biceps_video
            ));
            mExercises.add(new Exercise(mContext, "Biceps!", "Flex your biceps!", R.drawable.bicep_exercise, R.raw.biceps_video));
            mExercises.add(new Exercise(mContext, "Boxing!", "Deliver a blow!", R.drawable.boxing_exercise, R.raw.boxing_video));
            mExercises.add(new Exercise(mContext, "High Knees!", "Run in place keeping your knees above your waist.", R.drawable.high_knees_exercise, R.raw.high_knees_video));
            mExercises.add(new Exercise(mContext, "Jumping Jacks!", "Jump in the air and spread your legs and arms while landing with your legs and arms together.", R.drawable.jumping_jack_exercise, R.raw.jumping_jacks_video));
            mExercises.add(new Exercise(mContext, "Knees!", "Bend and straighten your knee.", R.drawable.knee_exercise, R.raw.knee_lift_video));
            mExercises.add(new Exercise(mContext, "Pull-ups!", "Pull your self up until your head is above the bar.", R.drawable.pull_ups_exercise, R.raw.pull_up_video));
            mExercises.add(new Exercise(mContext, "Push-ups!", "Position your shoulders and heels parallel, bend your elbows, and go down and back up.", R.drawable.push_up_exercise, R.raw.push_up_video));
            mExercises.add(new Exercise(mContext, "Quads!", "Flex your quadriceps.", R.drawable.quad_exercise, R.raw.quads_video));
            mExercises.add(new Exercise(mContext, "Sit-ups!", "Lay on your back with your knees bent and flex your abs.", R.drawable.sit_up_exercise, R.raw.sit_ups));
        }
    }



}

