package edu.ggc.epatter1.witnessfitness.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by epatter1 on 4/26/2016.
 */
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
        generateDummyData();
    }

    public List<Exercise> getExercises() {
        return mExercises;
    }


    public Exercise getCurrentExercise(){
        return mExercises.get(currentExercise);
    }

    public void incrementCurrentExercise() {
        currentExercise++;
    }

    public void restart() {
        currentExercise = 0;
    }
    //adds exercise to AL
    public void add (Exercise e) {
        mExercises.add(e);
    }

    public List<Exercise> getmExercises(){
        return mExercises;
    }

    private void generateDummyData() {
        for (int i = 0; i < 10; i++){
            Exercise exercise = new Exercise();
            exercise.setName("Exercise # " + i);
            exercise.setDescription("Foot/IN/Mouth");
            //setReps here
            //setIsTimed (counter) here
            mExercises.add(exercise);

        }
    }


}

