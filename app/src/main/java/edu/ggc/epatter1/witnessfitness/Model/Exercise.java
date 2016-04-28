package edu.ggc.epatter1.witnessfitness.Model;

import android.graphics.Bitmap;

/**
 * Created by epatter1 on 4/23/2016.
 */
public class Exercise {


    String name;
    String description;
    int picture;
//    int numReps;
//    String notes;
    //TODO add numReps and notes to constructor
    public Exercise (String name, String description, int picture) {
        setDescription(description);
        setName(name);
        setPicture(picture);
//        setNumReps(numReps);
//        setNotes(notes);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

//    public int getNumReps() {
//        return numReps;
//    }
//    public void setNumReps(int numReps) {
//        this.numReps = numReps;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
}
