package edu.ggc.epatter1.witnessfitness.Model;

import android.content.Context;
import android.util.Log;

import java.util.UUID;

import edu.ggc.epatter1.witnessfitness.Support.SharedPreference;

/* This is our business class for one exercise only Mr. Vasili. (Hunt for Red October, the ping scene) */
public class Exercise {

    private static final String TAG = "ExerciseModel";

    public static final int NONE = 0;
    public static final int IMAGE = 1;
    public static final int VIDEO = 2;



    private UUID mId;
    private String name = "";
    private String description = "";
    private int numReps = 0;  //TODO think about making a string and convert at edittext
    private boolean isTimed;
    private String mDuration = "";
    private String notes = "";
    private String picture = ""; // picture/image location; if number use drawable, else internal storage
    private String video = ""; // video location; if number, use drawable, else internal storage
    private SharedPreference sharedPreference;
    private Context mContext;

    //TODO if the integer to string conversion does not work for default drawable then
    // create following
    // private int intImage;
    // private int intVideo;
    // This way you can store the R.id.blahblah directly. To check to see if you need to use image
    // stored in internal storage. You just need to check if picture.isEmpty() and/or video.isEmpty()
    // You are still going to use the mediaToDisplay to decide which one to check and/or use.




    public Exercise(Context context) {
        mContext = context;
        mId = UUID.randomUUID();
        sharedPreference = new SharedPreference(mId);

    }

    public Exercise(Context context, UUID id) {
        mContext = context;
        mId = id;
        sharedPreference = new SharedPreference(mId);
    }

    //TODO add numReps and notes to constructor

    /**
     * Only call this when creating new Exercises with values. Use Exercise(Context, UUID) for all other calls.
     * @param context
     * @param name
     * @param description
     * @param picture
     * @param video
     */
    public Exercise (Context context, String name, String description, int picture, int video) {
        mContext = context;
        mId = UUID.randomUUID();
        sharedPreference = new SharedPreference(mId);
        setDescription(description);
        setName(name);
        setPicture(Integer.toString(picture));
        setVideo(Integer.toString(video));
        setNumReps(0);
        setDuration("0");
        setNotes("Don't let me catch you slacking...");

    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Log.d(TAG, "setName: " + name);
        this.name = name;
        sharedPreference.saveString(mContext, SharedPreference.KEY_NAME, name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Log.d(TAG, "setDescription: " + description);
        this.description = description;
        sharedPreference.saveString(mContext, SharedPreference.KEY_DESC, description);

    }

    public int getNumReps() {
        return numReps;
    }

    public void setNumReps(int numReps) {
        Log.d(TAG, "setNumReps: " + numReps);
        this.numReps = numReps;
        sharedPreference.saveInt(mContext, SharedPreference.KEY_REPS, numReps);

    }

    public boolean isTimed() {
        return isTimed;
    }

    public void setIsTimed(boolean isTimed) {
        Log.d(TAG, "setIsTimed: " + isTimed);
        this.isTimed = isTimed;
        sharedPreference.saveBoolean(mContext, SharedPreference.KEY_IS_TIMED, isTimed);

    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String mDuration) {
        Log.d(TAG, "setDuration: " + mDuration);
        this.mDuration = mDuration;
        sharedPreference.saveString(mContext, SharedPreference.KEY_DURATION, mDuration);

    }

    public String getNotes() {
        return notes;
   }

    public void setNotes(String notes) {
        Log.d(TAG, "setNotes: " + notes);
        this.notes = notes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        Log.d(TAG, "setPicture: " + picture);
        this.picture = picture;
        sharedPreference.saveString(mContext, SharedPreference.KEY_PIC, picture);

    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        Log.d(TAG, "setVideo: " + video);
        this.video = video;
        sharedPreference.saveString(mContext, SharedPreference.KEY_VID, video);

    }

    public void restoreData(){
        name = sharedPreference.getStringValue(mContext, SharedPreference.KEY_NAME);
        description = sharedPreference.getStringValue(mContext, SharedPreference.KEY_DESC);
        numReps = sharedPreference.getIntValue(mContext, SharedPreference.KEY_REPS);
        isTimed = sharedPreference.getBooleanValue(mContext, SharedPreference.KEY_IS_TIMED);
        mDuration = sharedPreference.getStringValue(mContext, SharedPreference.KEY_DURATION);
        notes = sharedPreference.getStringValue(mContext, SharedPreference.KEY_NOTES);
        picture = sharedPreference.getStringValue(mContext, SharedPreference.KEY_PIC); // picture/image location; if number use drawable, else internal storage
        video = sharedPreference.getStringValue(mContext, SharedPreference.KEY_VID);
    }
}
