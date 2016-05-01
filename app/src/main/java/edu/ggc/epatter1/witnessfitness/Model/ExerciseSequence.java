package edu.ggc.epatter1.witnessfitness.Model;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import edu.ggc.epatter1.witnessfitness.R;

public class ExerciseSequence {

    //start singleton desgin pattern
    //to use the singleton pattern, do one of the following:
    // 1. ExerciseSequence.getInstance().getExercises(); // return ArrayList of Exercises
    // 2. ExerciseSequence.getInstance().getCurrentExercise(); //return current Exercise

    private static ExerciseSequence ourInstance = new ExerciseSequence();
    private int currentExercise = 0;


    public static ExerciseSequence getInstance() {
        return ourInstance;
    }
    //end singleton design pattern

    //start object class
    private List<Exercise> mExercises;

    private ExerciseSequence() {
        mExercises = new ArrayList<>();
        createExercises();
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
    }

    public void createExercises() {
        // mExercises here

        //for (int i = 0; i < 10; i++) {
        //    Exercise exercise = new Exercise();
        //    exercise.setName("Exercise: " + i);
        //    exercise.setDescription("Description: " + i);
        //    exercise.setDuration(Integer.toString(i));
        //    exercise.setNumReps(i);
       // }

        mExercises.add(new Exercise("Back!", "Move your back", R.drawable.back_exercise, R.raw.biceps_video
        ));
        mExercises.add(new Exercise("Biceps!", "Flex your biceps!", R.drawable.bicep_exercise, R.raw.biceps_video));
        mExercises.add(new Exercise("Boxing!", "Deliver a blow!", R.drawable.boxing_exercise, R.raw.boxing_video));
        mExercises.add(new Exercise("High Knees!", "Run in place keeping your knees above your waist.", R.drawable.high_knees_exercise, R.raw.high_knees_video));
        mExercises.add(new Exercise("Jumping Jacks!", "Jump in the air and spread your legs and arms while landing with your legs and arms together.", R.drawable.jumping_jack_exercise, R.raw.jumping_jacks_video));
        mExercises.add(new Exercise("Knees!", "Bend and straighten your knee.", R.drawable.knee_exercise, R.raw.knee_lift_video));
        mExercises.add(new Exercise("Pull-ups!", "Pull your self up until your head is above the bar.", R.drawable.pull_ups_exercise, R.raw.pull_up_video));
        mExercises.add(new Exercise("Push-ups!", "Position your shoulders and heels parallel, bend your elbows, and go down and back up.", R.drawable.push_up_exercise, R.raw.push_up_video));
        mExercises.add(new Exercise("Quads!", "Flex your quadriceps.", R.drawable.quad_exercise, R.raw.quads_video));
        mExercises.add(new Exercise("Sit-ups!", "Lay on your back with your knees bent and flex your abs.", R.drawable.sit_up_exercise, R.raw.sit_ups));

    }



}

