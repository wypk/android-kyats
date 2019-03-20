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

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import wyp.kyats.R;
import wyp.kyats.cache.otherbanks.OtherBanksExchangeRatesManipulator;
import wyp.kyats.component.ui.RecyclerViewDividerItemDecoration;
import wyp.kyats.domain.otherbanks.webpageparser.CBExchangeRateParser;
import wyp.kyats.domain.otherbanks.model.ExchangeRateModel;
import wyp.kyats.domain.otherbanks.model.ExchangeRateResponseModel;
import wyp.kyats.domain.otherbanks.stringparser.CommonRawRatesParser;
import wyp.kyats.foundation.SharedPreferencesConstants;
import wyp.kyats.ui.adapter.OthersBankExchangeRateRVAdapter;

public class CBBankExchangeRateFragment extends OtherBanksExchangeRateBaseFragment {

    @Override
    public void getLatestExchangeRate() {

        new CBExchangeRateParser(new CBExchangeRateParser.ParserResponse() {

            @Override
            public void onLoading() {
                swipeContainer.setRefreshing(true);
            }

            @Override
            public void onSuccess(@NonNull ExchangeRateResponseModel exchangeRateResponseModel) {

                stopLoading();

                if (exchangeRateResponseModel.updatedDate != null) {

                    OtherBanksExchangeRatesManipulator
                            .save(SharedPreferencesConstants.RATE_CB_BANK_PREFERENCES_NAME,
                                    exchangeRateResponseModel);

                    showOnUI();
                }
            }
        }).execute();
    }

    @Override
    public void showOnUI() {

        ExchangeRateResponseModel exchangeRateResponseModel = OtherBanksExchangeRatesManipulator
                .retrieve(SharedPreferencesConstants.RATE_CB_BANK_PREFERENCES_NAME);

        if (exchangeRateResponseModel != null) {

            rlMainLayout.setVisibility(View.VISIBLE);

            lblUpdatedDate.setText(exchangeRateResponseModel.updatedDate);

            List<String> rawRates = exchangeRateResponseModel.rawRate;

            OthersBankExchangeRateRVAdapter othersBankExchangeRateRVAdapter =
                    new OthersBankExchangeRateRVAdapter(getActivity(),
                            (View view, int position, OthersBankExchangeRateRVAdapter.Model item) -> {
                                //Do nothing
                            }, rate -> {
                        //TODO Converter
                    });
            rvExchange.setHasFixedSize(true);
            rvExchange.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvExchange.setAdapter(othersBankExchangeRateRVAdapter);
            rvExchange.addItemDecoration(
                    new RecyclerViewDividerItemDecoration(getActivity(), R.drawable.view_divider));

            List<OthersBankExchangeRateRVAdapter.Model> models = new ArrayList<>();

            for (String rawRate : rawRates) {

                ExchangeRateModel exchangeRateModel = CommonRawRatesParser.parse(rawRate);

                models.add(new OthersBankExchangeRateRVAdapter.Model(
                        exchangeRateModel.currency,
                        exchangeRateModel.buyRate,
                        exchangeRateModel.sellRate));
            }

            othersBankExchangeRateRVAdapter.addItems(models);
        }
    }
}
