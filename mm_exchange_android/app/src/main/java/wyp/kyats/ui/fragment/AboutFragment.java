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

import android.content.Intent;
import android.net.Uri;
import android.widget.RelativeLayout;

import com.marcoscg.licenser.Library;
import com.marcoscg.licenser.License;
import com.marcoscg.licenser.LicenserDialog;

import java.util.Objects;

import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.OnClick;
import wyp.kyats.R;
import wyp.kyats.component.ui.BaseFragment;

public class AboutFragment extends BaseFragment {

    @BindView(R.id.rlLibraries)
    RelativeLayout rlLibraries;

    @BindView(R.id.rlReportBugs)
    RelativeLayout rlReportBugs;

    @BindView(R.id.rlRateOnGPlay)
    RelativeLayout rlRateOnGPlay;

    @BindView(R.id.rlIconsCredit)
    RelativeLayout rlIconsCredit;

    @BindView(R.id.rlPicsCredit)
    RelativeLayout rlPicsCredit;

    @Override
    protected void createView() {

    }

    @Override
    protected int getLayoutXmlId() {
        return R.layout.fragment_abouts;
    }

    @OnClick(R.id.rlLibraries)
    public void onClickRlLibraries() {

        LicenserDialog licenserDialog = new LicenserDialog(Objects.requireNonNull(getActivity()))
                .setTitle("Licenses")
                .setCustomNoticeTitle("Notices for libraries")
                .setCancelable(true)
                .setLibrary(new Library("Retrofit",
                        "https://square.github.io/retrofit/",
                        License.APACHE))
                .setLibrary(new Library("Lottie",
                        "https://github.com/airbnb/lottie-android",
                        License.APACHE))
                .setLibrary(new Library("JSoup",
                        "https://jsoup.org/",
                        License.APACHE))
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    //Do Nothing:
                });
        licenserDialog.show();
    }

    @OnClick(R.id.rlReportBugs)
    public void onClickRlReportBugs() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "waiyanphyoecu.wyp@gmail.com"));
        startActivity(emailIntent);
    }

    @OnClick(R.id.rlRateOnGPlay)
    public void onClickRlRateOnGPlay() {

        final String appPackageName = Objects.requireNonNull(getActivity()).getPackageName();
        try {
            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException exception) {
            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            exception.printStackTrace();
        }
    }

    @OnClick(R.id.rlIconsCredit)
    public void onClickRlIconsCredit() {

        final String FLAT_ICON_URL = "https://www.flaticon.com/";
        final String ICON_FINDER_URL = "https://www.iconfinder.com/";

        new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setTitle(R.string.icons_credit)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    //Dismiss
                })
                .setItems(new String[]{FLAT_ICON_URL, ICON_FINDER_URL}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            this.showOnWebView(FLAT_ICON_URL);
                            break;
                        case 1:
                            this.showOnWebView(ICON_FINDER_URL);
                            break;
                    }
                }).show();
    }

    @OnClick(R.id.rlPicsCredit)
    public void onClickRlPicsCredit() {

        final String UNSPLASH_URL = "https://unsplash.com/";
        final String MYANPX_URL = "https://myanpx.com/";

        new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setTitle(R.string.images_credit)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    //Dismiss
                })
                .setItems(new String[]{UNSPLASH_URL, MYANPX_URL}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            this.showOnWebView(UNSPLASH_URL);
                            break;
                        case 1:
                            this.showOnWebView(MYANPX_URL);
                            break;
                    }
                }).show();
    }

    private void showOnWebView(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
