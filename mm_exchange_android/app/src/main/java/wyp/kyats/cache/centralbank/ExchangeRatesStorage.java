/*
 * Copyright 2019 Wai Yan - (09 97777 3 444).
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.kyats.cache.centralbank;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ExchangeRatesStorage {

    private final static String PREFERENCES_NAME = "mm.exchange.CentralBank.ExchangeRatesStorage";

    private final static String DATA = PREFERENCES_NAME + ".DATA";

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
