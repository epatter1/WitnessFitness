package edu.ggc.epatter1.witnessfitness.Model;

import android.util.Log;
import android.widget.TextView;

import java.util.UUID;

/* This is our business class for one exercise only Mr. Vasili. (Hunt for Red October, the ping scene) */
public class Exercise {
    public static final int NONE = 0;
    public static final int IMAGE = 1;
    public static final int VIDEO = 2;

    private String TAG = "ExerciseModel";

    private UUID mId;
    private String name = "";
    private String description = "";
    private int numReps = 0;  //TODO think about making a string and convert at edittext
    private boolean isTimed;
    private String mDuration = "";
    private String notes = "";
    private String picture = ""; // picture/image location; if number use drawable, else internal storage
    private String video = ""; // video location; if number, use drawable, else internal storage
    //TODO if the integer to string conversion does not work for default drawable then
    // create following
    // private int intImage;
    // private int intVideo;
    // This way you can store the R.id.blahblah directly. To check to see if you need to use image
    // stored in internal storage. You just need to check if picture.isEmpty() and/or video.isEmpty()
    // You are still going to use the mediaToDisplay to decide which one to check and/or use.


    public Exercise() {
        mId = UUID.randomUUID();
    }

    //TODO add numReps and notes to constructor
    public Exercise (String name, String description, int picture, int video) {
        mId = UUID.randomUUID();
        setDescription(description);
        setName(name);
        setPicture(Integer.toString(picture));
        setVideo(Integer.toString(video));
        setNumReps(0);
        setDuration("0");
        setNotes("Need to fix in Exercise model constructor");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Log.d(TAG, "setName: " + name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumReps() {
        return numReps;
    }

    public void setNumReps(int numReps) {
        this.numReps = numReps;
    }

    public boolean isTimed() {
        return isTimed;
    }

    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getNotes() {
        return notes;
   }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {this.video = video;}
}
