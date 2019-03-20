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

package wyp.kyats.ui.fragment;

import android.graphics.Color;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.RelativeLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import wyp.kyats.R;
import wyp.kyats.component.ui.BaseFragment;
import wyp.kyats.component.ui.SnackBar;
import wyp.kyats.cache.app.AppInfoStorage;

public class SettingsFragment extends BaseFragment {

    @BindView(R.id.txvDefaultConvertValue)
    AppCompatTextView txvDefaultConvertValue;

    @BindView(R.id.rlDefaultConvertValue)
    RelativeLayout rlDefaultConvertValue;

    @BindView(R.id.switchBtnDarkMode)
    SwitchCompat switchBtnDarkMode;

    @Override
    protected void createView() {

        if (AppInfoStorage.getInstance().getDefaultConvertValue() != null) {
            txvDefaultConvertValue.setText(AppInfoStorage.getInstance().getDefaultConvertValue());
        }

        rlDefaultConvertValue.setOnClickListener(view -> this.configDefaultConvertValue());
    }

    @Override
    protected int getLayoutXmlId() {
        return R.layout.fragment_settings;
    }

    private void configDefaultConvertValue() {

        new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setItems(new String[]{
                        "1", "10", "100", "1000", "10000"
                }, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            this.changeConfig("1.0");
                            break;
                        case 1:
                            this.changeConfig("10.0");
                            break;
                        case 2:
                            this.changeConfig("100.0");
                            break;
                        case 3:
                            this.changeConfig("10000.0");
                            break;
                        case 4:
                            this.changeConfig("100000.0");
                            break;
                    }
                }).show();
    }

    private void changeConfig(String defaultValue) {
        txvDefaultConvertValue.setText(defaultValue);
        AppInfoStorage.getInstance().setDefaultConvertValue(defaultValue);
    }

    @OnCheckedChanged(R.id.switchBtnDarkMode)
    public void toggleNightMode() {

        new SnackBar(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                getString(R.string.premium_feature),
                Snackbar.LENGTH_INDEFINITE,
                R.drawable.ic_info,
                Color.parseColor("#f9a825"),
                "OK")
                .show();
    }
}
