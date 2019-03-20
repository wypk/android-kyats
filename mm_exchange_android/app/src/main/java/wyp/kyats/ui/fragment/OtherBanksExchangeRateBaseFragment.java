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

import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import wyp.kyats.R;
import wyp.kyats.component.ui.BaseFragment;

/**
 * Created by Wai Yan on 3/19/19.
 */
public abstract class OtherBanksExchangeRateBaseFragment extends BaseFragment {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.lblUpdatedDate)
    AppCompatTextView lblUpdatedDate;

    @BindView(R.id.rvExchange)
    RecyclerView rvExchange;

    @BindView(R.id.rlMainLayout)
    RelativeLayout rlMainLayout;

    @Override
    protected int getLayoutXmlId() {
        return R.layout.fragment_others_banks_exchange_rate;
    }

    @Override
    protected void createView() {

        this.getLatestExchangeRate();
        this.onRefresh();
        this.showOnUI();
    }

    private void onRefresh() {

        swipeContainer.setColorSchemeResources(
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_red_dark);

        swipeContainer.setOnRefreshListener(this::getLatestExchangeRate);
    }

    public void stopLoading() {
        swipeContainer.setRefreshing(false);
    }

    public abstract void getLatestExchangeRate();

    public abstract void showOnUI();
}
