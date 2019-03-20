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
