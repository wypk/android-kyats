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
