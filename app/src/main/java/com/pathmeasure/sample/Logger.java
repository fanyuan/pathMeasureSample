package com.pathmeasure.sample;

import android.util.Log;

public class Logger {
    /**
     * {@link }
     */
    private static final String TAG = "tag_debug";
    public static void d(String msg){
        Log.d(TAG,msg);
    }
}
