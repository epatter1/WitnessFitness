package edu.ggc.epatter1.witnessfitness;

/**
 * Taken from http://androidopentutorials.com/
 * android-sharedpreferences-tutorial-and-example/
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.UUID;

public class SharedPreference {

    public static final String PREFS_NAME = "WitnessFitnessPref";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESC = "description";
    public static final String KEY_REPS = "reps";
    public static final String KEY_IS_TIMED = "timed";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_NOTES = "note";
    public static final String KEY_PIC = "picture";
    public static final String KEY_VID = "video";

    private UUID mId;

    public SharedPreference(UUID id) {
        super();
        mId = id;
    }

    public void saveInt(Context context, String keyName, int value) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        String uniqueKey = keyName + "-" + mId;

        editor.putInt(uniqueKey, value);


        editor.commit(); //4
    }

    public void saveString(Context context, String keyName, String text) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        String uniqueKey = keyName + "-" + mId;

        editor.putString(uniqueKey, text); //3
        editor.commit(); //4
    }

    public void saveBoolean(Context context, String keyName, boolean isTrue) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        String uniqueKey = keyName + "-" + mId;
        editor.putBoolean(uniqueKey, isTrue); //3

        editor.commit(); //4
    }


    // add save for each type



    public String getStringValue(Context context, String keyName) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String uniqueKey = keyName + "-" + mId;
        text = settings.getString(uniqueKey, "");
        return text;
    }

    public int getIntValue(Context context, String keyName) {
        SharedPreferences settings;
        int value;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String uniqueKey = keyName + "-" + mId;
        value = settings.getInt(uniqueKey, 0);
        return value;
    }

    public boolean getBooleanValue(Context context, String keyName) {
        SharedPreferences settings;
        boolean value;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String uniqueKey = keyName + "-" + mId;
        value = settings.getBoolean(uniqueKey, false);
        return value;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public boolean hasKey(Context context, String keyName){
        SharedPreferences settings;
        boolean value = false;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String uniqueKey = keyName + "-" + mId;


        if (settings.contains(uniqueKey))
        {
            value = true;
        }

        return value;
    }
}
