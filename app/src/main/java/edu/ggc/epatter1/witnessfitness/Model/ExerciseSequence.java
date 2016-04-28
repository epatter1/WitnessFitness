package edu.ggc.epatter1.witnessfitness.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by epatter1 on 4/26/2016.
 */
public class ExerciseSequence {

    private List<Exercise> mExercises;

    public ExerciseSequence() {
        mExercises = new ArrayList<>();
    }

    public void add (Exercise e) {
        mExercises.add(e);
    }

    public List<Exercise> getmExercises(){
        return mExercises;
    }


}

