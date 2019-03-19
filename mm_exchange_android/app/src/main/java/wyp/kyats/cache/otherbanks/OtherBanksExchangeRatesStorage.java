package wyp.kyats.cache.otherbanks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Wai Yan on 3/18/19.
 */
public class OtherBanksExchangeRatesStorage {

    private final static String PREFERENCES_NAME = "mm.exchange.OtherBanks.ExchangeRatesStorage";

    private static OtherBanksExchangeRatesStorage otherBanksExchangeRatesStorage;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private OtherBanksExchangeRatesStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static void initialize(Context context) {

        if (otherBanksExchangeRatesStorage == null) {
            otherBanksExchangeRatesStorage = new OtherBanksExchangeRatesStorage(context);
        }
    }

    public synchronized static OtherBanksExchangeRatesStorage getInstance() {

        return OtherBanksExchangeRatesStorage.otherBanksExchangeRatesStorage;
    }

    public void setData(String key, String data) {
        this.cleanUp(key);
        this.editor.putString(key, data);
        this.editor.commit();
    }

    public String getData(String key) {
        return this.sharedPreferences.getString(key, null);
    }

    private void cleanUp(String key){
        this.editor.remove(key);
        this.editor.commit();
    }
}
