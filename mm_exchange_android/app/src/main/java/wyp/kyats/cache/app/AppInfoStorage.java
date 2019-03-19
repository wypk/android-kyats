package wyp.kyats.cache.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppInfoStorage {

    private final static String PREFERENCES_NAME = "mm.exchange.AppInfoStorage";

    private final static String DEFAULT_CONVERT_VALUE_KEY = PREFERENCES_NAME + "DEFAULT_CONVERT_VALUE";
    private final static String DEFAULT_THEME = PREFERENCES_NAME + "DEFAULT_THEME";

    private static AppInfoStorage appInfoStorage;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private AppInfoStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static void initialize(Context context) {
        if (appInfoStorage == null) {
            appInfoStorage = new AppInfoStorage(context);
        }
    }

    public synchronized static AppInfoStorage getInstance() {
        return AppInfoStorage.appInfoStorage;
    }

    public String getDefaultConvertValue() {
        return this.sharedPreferences.getString(DEFAULT_CONVERT_VALUE_KEY, null);
    }

    public void setDefaultConvertValue(String defaultConvertValue) {
        this.editor.putString(DEFAULT_CONVERT_VALUE_KEY, defaultConvertValue);
        this.editor.commit();
    }

    public String getDefaultTheme() {
        return this.sharedPreferences.getString(DEFAULT_THEME, null);
    }

    public void saveDefaultTheme(String defaultTheme) {
        this.editor.putString(DEFAULT_THEME, defaultTheme);
        this.editor.commit();
    }
}
