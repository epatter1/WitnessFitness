package edu.ggc.epatter1.witnessfitness.Model;

/**
 * Created by epatter1 on 4/26/2016.
 */
public class GlobalExerciseSequence {
    private static GlobalExerciseSequence ourInstance = new GlobalExerciseSequence();

    public static GlobalExerciseSequence getInstance() {
        return ourInstance;
    }

    private GlobalExerciseSequence() {

    }
}
