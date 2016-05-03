package edu.ggc.epatter1.witnessfitness.Support;

/**
 * Taken from http://androidopentutorials.com/
 * android-sharedpreferences-tutorial-and-example/
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.Map;
import java.util.UUID;

import edu.ggc.epatter1.witnessfitness.Model.ExerciseSequence;

public class SharedPreference {

    private static final String TAG = "SharedPref";

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
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        String uniqueKey = keyName + "-" + mId;

        editor.putInt(uniqueKey, value);


        editor.commit(); //4
    }

    public void saveString(Context context, String keyName, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        String uniqueKey = keyName + "-" + mId;

        editor.putString(uniqueKey, text); //3
        editor.commit(); //4

        // Only print out the SharedPreferences when we are saving the master list
        if (keyName.equals(ExerciseSequence.KEY_UUIDMASTERLIST)) {
            listPreferences(context);
        }
    }

    public void saveBoolean(Context context, String keyName, boolean isTrue) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

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

        // Only print out the SharedPreferences when we are saving the master list
        if (keyName.equals(ExerciseSequence.KEY_UUIDMASTERLIST)) {
            listPreferences(context);
        }

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

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String uniqueKey = keyName + "-" + mId;


        if (settings.contains(uniqueKey))
        {
            value = true;
        }

        return value;
    }

    public void clearList(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void deleteValue(Context context, String valueType) {
        String prefKey = mId.toString()+"-"+valueType;
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(prefKey);
        editor.commit();
    }

    public void listPreferences(Context context) {
        Log.d(TAG, "listPreferences: Request to list data");
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Map<String, ?> allPrefs = settings.getAll();

        if (allPrefs.size() == 0) {
            Log.d(TAG, "listPreferences: sharedPreference list is empty");
            return;
        }

        for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
            Log.d(TAG, "listPreferenceskey: " + entry.getKey() + " -- value: " + entry.getValue());
        }
    }
}
