package wyp.kyats.cache.centralbank;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ExchangeRatesStorage {

    private final static String PREFERENCES_NAME = "mm.exchange.CentralBank.ExchangeRatesStorage";

    private final static String DATA = PREFERENCES_NAME + "DATA";

    private static ExchangeRatesStorage currenciesStorage;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private ExchangeRatesStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static void initialize(Context context) {

        if (currenciesStorage == null) {
            currenciesStorage = new ExchangeRatesStorage(context);
        }
    }

    public synchronized static ExchangeRatesStorage getInstance() {

        return ExchangeRatesStorage.currenciesStorage;
    }

    public String getData() {
        return this.sharedPreferences.getString(DATA, null);
    }

    public void setData(String data) {
        this.cleanUpAll();
        this.editor.putString(DATA, data);
        this.editor.commit();
    }

    private void cleanUpAll() {
        this.editor.remove(DATA);
        this.editor.commit();
    }
}
