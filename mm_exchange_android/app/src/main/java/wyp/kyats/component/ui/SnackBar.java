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

package wyp.kyats.component.ui;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class SnackBar {

    private Snackbar snackbar;

    private View view;
    private String message;
    private int duration;
    private int iconResId;
    private int backgroundColorResId;
    @Nullable
    private String actionMessage;

    public SnackBar(View view,
                    String message,
                    int duration,
                    @DrawableRes int iconResId,
                    @ColorInt int backgroundColorResId,
                    @Nullable String actionMessage) {

        this.view = view;
        this.message = message;
        this.duration = duration;
        this.iconResId = iconResId;
        this.backgroundColorResId = backgroundColorResId;
        this.actionMessage = actionMessage;
    }

    public void show() {
        snackbar = Snackbar.make(view, message, duration);
        if (actionMessage != null) {
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.setAction(this.actionMessage, v -> snackbar.dismiss());
        }
        TextView textView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(iconResId, 0, 0, 0);
        textView.setCompoundDrawablePadding(16);
        snackbar.getView().setBackgroundColor(backgroundColorResId);
        snackbar.show();
    }

    public void hide() {
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }
}
