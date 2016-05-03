package edu.ggc.epatter1.witnessfitness.Model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.ggc.epatter1.witnessfitness.R;
import edu.ggc.epatter1.witnessfitness.Support.SharedPreference;

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

    public static final String KEY_UUIDMASTERLIST = "uuidmasterlist";

    /* In order for us to not have to handle a separate case for our instanceVariable-UUID key/value pair
        for SharedPreferences. We generated a fixed, constant UUID for this app to assign to the master list
        needed to restore all the exercises. Exercises get unique UUID everytime they are generated brand new.
        For exercises, UUIDs will NOT be duplicated.  The exception is for this one key/value pair
        for the master list that holds all the UUID for each of the exercises we have available to us in
        the sort order from our list/RecyclerView. Anytime the sort order is changed, this master list needs
        to be updated to reflect the new sort order.

        The master list tracks 2 things.
        1. Any exercise entered into the app. Note: deleting an exerise remove
            its unique UUID from the key/value pair and the master list
        2. It tracks the sort order of the exercises by sorting the UUID of exercise in their correct order chosen
            by the end user

        Warning: ANY CHANGE TO UUIDSTRING WILL REQUIRE A sharedPreference.clearList() call!!!!!!!
        //TODO new Feature: Create a Clear, list, and generate debug button for controlling ExerciseSequece data.
        // Clear - clear all the SharedPreferences space
        // List - show the contents of the SharedPreferences space
        // Generate -- to recreate dummy data
     */
    private static final String UUIDSTRING = "AABE38F4-7673-4434-B470-143524D8E738";



    private static final boolean DEBUG_NOIMAGE = false;
    private static final String TAG = "ExerciseSequence";
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
            Log.d(TAG, "ExerciseSequence: restoring exercises");
            createExercises();
        } else {
            sharedPreference.clearList(mContext);
            this.restoreExercises();

            createExercises();
            Log.d(TAG, "ExerciseSequence: creating new exercises");
        }


    }

    private void restoreExercises() {
        SharedPreference sharedPreference = new SharedPreference(UUID.fromString(UUIDSTRING));
        String savedIdsString = sharedPreference.getStringValue(mContext, KEY_UUIDMASTERLIST);

        Log.d(TAG, "restoreExercises: UUID master list values: " + savedIdsString);

        String[] uuidArr = TextUtils.split(savedIdsString, ",");
        for (int i = 0; i < uuidArr.length - 1; i++) {
            Exercise exercise = new Exercise(mContext, UUID.fromString(uuidArr[i]));
            exercise.restoreData();

            //WARNING: we do NOT want to call my add
            //method containing savedIds. Just call regular
            //mExercises.add -- see my add method for details
            mExercises.add(exercise);
        }

    }

    public void setCurrentExerciseByPostion(int position) {
        if ( position < mExercises.size()) {
            currentExercise = position;
        } else {
            currentExercise = mExercises.size() - 1;
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
        Log.d(TAG, "savedIds: attempting to save main list of UUIDs");
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

        if (mExercises.size() == 0 ) {
            SharedPreference sharedPreference = new SharedPreference(UUID.fromString(UUIDSTRING));
            if (!sharedPreference.hasKey(mContext, KEY_UUIDMASTERLIST)) {

                if (DEBUG_NOIMAGE) {
                    for (int i = 0; i < 100; i++) {
                        Exercise exercise = new Exercise(mContext);
                        exercise.setName("Exercise #" + i);
                        exercise.setDescription("Description #" + i);
                        exercise.setIsTimed(i % 2 == 0);
                        add(exercise);
                    }
                } else {
                    mExercises.add(new Exercise(mContext, "Biceps!", "Flex your biceps!", R.drawable.bicep_exercise, R.raw.biceps_video));
                    mExercises.add(new Exercise(mContext, "Boxing!", "Deliver a blow!", R.drawable.boxing_exercise, R.raw.boxing_video));
                    mExercises.add(new Exercise(mContext, "High Knees!", "Run in place keeping your knees above your waist.", R.drawable.high_knees_exercise, R.raw.high_knees_video));
                    mExercises.add(new Exercise(mContext, "Jumping Jacks!", "Jump in the air and spread your legs and arms while landing with your legs and arms together.", R.drawable.jumping_jack_exercise, R.raw.jumping_jacks_video));
                    mExercises.add(new Exercise(mContext, "Knees!", "Bend and straighten your knee.", R.drawable.knee_exercise, R.raw.knee_lift_video));
                    mExercises.add(new Exercise(mContext, "Pull-ups!", "Pull your self up until your head is above the bar.", R.drawable.pull_ups_exercise, R.raw.pull_up_video));
                    mExercises.add(new Exercise(mContext, "Push-ups!", "Position your shoulders and heels parallel, bend your elbows, and go down and back up.", R.drawable.push_up_exercise, R.raw.push_up_video));
                    mExercises.add(new Exercise(mContext, "Quads!", "Flex your quadriceps.", R.drawable.quad_exercise, R.raw.quads_video));
                    mExercises.add(new Exercise(mContext, "Sit-ups!", "Lay on your back with your knees bent and flex your abs.", R.drawable.sit_up_exercise, R.raw.sit_ups));
                    mExercises.add(new Exercise(mContext, "Back!", "Move your back", R.drawable.back_exercise, R.raw.biceps_video));
                }
            }
        }
    }



}

