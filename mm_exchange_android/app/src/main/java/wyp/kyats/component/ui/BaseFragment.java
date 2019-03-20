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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private View fragmentView = null;
    private EsProgressDialog progressDialog = EsProgressDialog.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (null == this.fragmentView) {
            this.fragmentView = inflater.inflate(this.getLayoutXmlId(), container, false);
            ButterKnife.bind(this, this.fragmentView);
            this.createView();
        }
        return this.fragmentView;
    }

    protected abstract void createView();

    protected abstract int getLayoutXmlId();

    public View getFragmentView() {
        return this.fragmentView;
    }

    public void showProgressBar() {
        progressDialog.showProgressDialog(getActivity());
    }

    public void hideProgressBar() {
        progressDialog.hideProgressDialog();
    }

    public void onInternetAvailable(BaseActivity parentActivity) {
        parentActivity.onInternetAvailable();
    }

    public void onInternetUnavailable(BaseActivity parentActivity) {
        parentActivity.onInternetUnavailable();
    }
}
