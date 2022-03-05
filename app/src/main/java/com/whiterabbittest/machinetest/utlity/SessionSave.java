package com.whiterabbittest.machinetest.utlity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;

/**
 * this class is used to store the values locally and used it whenever necessary
 */

public class SessionSave {



    public static void saveSession(String key, String value, Context context) {
        if (context != null) {
            Editor editor = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE).edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public static void saveSessionInt(String key, Integer value, Context context) {
        if (context != null) {
            Editor editor = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE).edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public static void saveSession(String key, Long value, Context context) {
        if (context != null) {
            Editor editor = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE).edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public static void clearAllSession(Context context) {
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE);
            prefs.getAll().clear();
            return;
        } else {
            return;
        }
    }

    public static String getSession(String key, Context context) {
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE);
            return prefs.getString(key, "");
        } else {
            return "";
        }
    }

    public static Integer getSessionInt(String key, Context context) {
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE);
            return prefs.getInt(key, -1);
        } else {
            return -1;
        }
    }

    public static void saveSession(String key, boolean value, Context context) {
        if (context != null) {
            Editor editor = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE).edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public static boolean getSession(String key, Context context, boolean a) {
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE);
            return prefs.getBoolean(key, false);
        } else {
            return false;
        }
    }

    public static long getSession(String key, Context context, long a) {
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE);
            return prefs.getLong(key, 0);
        } else {
            return 0;
        }
    }

    public static void clearSession(Context context) {
        Editor editor = context.getSharedPreferences("KEY", Activity.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    public static void tutorialChk(boolean isAsk, Context con) {
        Editor editor = con.getSharedPreferences("ASK", Context.MODE_PRIVATE).edit();
        editor.putBoolean("isAsk", isAsk);
        editor.commit();
    }

    public static boolean isAsk(Context con) {
        SharedPreferences sharedPreferences = con.getSharedPreferences("ASK", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isAsk", false);

    }


    public static boolean saveArray(String message, Context con) {
        SharedPreferences sp = con.getSharedPreferences("KEYARRAY", Context.MODE_PRIVATE);
        Editor mEdit1 = sp.edit();
        /* sKey is an array */
        mEdit1.putString("Status_size", message);

        return mEdit1.commit();
    }

    public static ArrayList<String> loadArray(Context con) {
        ArrayList<String> sKey = new ArrayList<>();
        SharedPreferences mSharedPreference1 = con.getSharedPreferences("KEYARRAY", Context.MODE_PRIVATE);
        sKey.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for (int i = 0; i < size; i++) {
            sKey.add(mSharedPreference1.getString("Status_" + i, null));
        }

        return sKey;

    }


    public static void ClearSessionOneTime(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("KEY_ONE_TIME", Activity.MODE_PRIVATE);
        prefs.edit().clear();
    }

    public static String getSessionOneTime(String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences("KEY_ONE_TIME", Activity.MODE_PRIVATE);
        String s = prefs.getString(key, "");
        return s == null ? "" : s;
    }

    public static void saveSessionOneTime(String key, String value, Context context) {
        if (context != null) {
            Editor editor = context.getSharedPreferences("KEY_ONE_TIME", Activity.MODE_PRIVATE).edit();
            editor.putString(key, value);
            editor.commit();
        }
    }
}
