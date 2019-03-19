package wyp.kyats.component.util;


import android.util.Log;

import java.util.Date;

import wyp.kyats.BuildConfig;

public class Logger {

    private static final String TAG = "Myanmar-Exchange";

    public static void d(Class<?> clazz, String log) {

        if (BuildConfig.DEBUG) {
            Log.d(TAG, clazz.getName() + " : " + Thread.currentThread().getName() + " - " + (new Date()).toString() + " >>> " + log);
        }
    }
}
