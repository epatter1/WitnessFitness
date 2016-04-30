package edu.ggc.epatter1.witnessfitness.Model;

import android.graphics.Bitmap;

/**
 * Created by epatter1 on 4/23/2016.
 * TODO not in this class, but have an onSTART, onSTOP, and onRESUME to persist data
 */
public class Exercise {


    String name;
    String description;
    int picture;
    int video;
//    int numReps;
//    String notes;
    //TODO add numReps and notes to constructor
    public Exercise (String name, String description, int picture, int video) {
        setDescription(description);
        setName(name);
        setPicture(picture);
        setVideo(video);
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

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {this.video = video;}

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
