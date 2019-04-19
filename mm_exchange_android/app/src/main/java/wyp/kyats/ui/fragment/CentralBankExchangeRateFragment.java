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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wyp.kyats.R;
import wyp.kyats.cache.centralbank.CentralBanksExchangeRatesManipulator;
import wyp.kyats.cache.centralbank.CurrenciesStorage;
import wyp.kyats.component.ui.BaseFragment;
import wyp.kyats.component.ui.RecyclerViewDividerItemDecoration;
import wyp.kyats.component.util.Logger;
import wyp.kyats.domain.centralbank.ApiUtils;
import wyp.kyats.domain.centralbank.response.ExchangeRateResponseModel;
import wyp.kyats.ui.activity.ConvertActivity;
import wyp.kyats.ui.adapter.CentralBankExchangeRateRVAdapter;

import static wyp.kyats.ui.adapter.CentralBankExchangeRateRVAdapter.Model;

public class CentralBankExchangeRateFragment extends BaseFragment {

    @BindView(R.id.srlCentralBank)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.lblUpdatedDate)
    AppCompatTextView lblUpdatedDate;

    @BindView(R.id.rvExchange)
    RecyclerView rvExchange;

    @Override
    protected void createView() {
        this.getLatestExchangeRate();
        this.showOnUI();
        this.onRefresh();
    }

    @Override
    protected int getLayoutXmlId() {
        return R.layout.fragment_central_bank_exchange_rate;
    }

    private void onRefresh() {

        swipeContainer.setColorSchemeResources(R.color.colorPrimaryDark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_red_dark);

        swipeContainer.setOnRefreshListener(this::getLatestExchangeRate);
    }

    private void getLatestExchangeRate() {

        swipeContainer.setRefreshing(true);

        ApiUtils.getAPIService().getLatestExchangeRates()
                .enqueue(new Callback<ExchangeRateResponseModel>() {

                    @Override
                    public void onResponse(@NonNull Call<ExchangeRateResponseModel> call,
                            @NonNull Response<ExchangeRateResponseModel> response) {

                        ExchangeRateResponseModel exchangeRateResponseModel = response.body();
                        assert exchangeRateResponseModel != null;

                        Logger.d(this.getClass(), "Exchange Rate Response INFO:" + exchangeRateResponseModel.info);

                        CentralBanksExchangeRatesManipulator.save(exchangeRateResponseModel);

                        showOnUI();
                        stopLoading();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ExchangeRateResponseModel> call,
                            @NonNull Throwable t) {

                        stopLoading();
                        Logger.d(this.getClass(), "Error :" + t);
                    }
                });

    }

    private void stopLoading() {
        swipeContainer.setRefreshing(false);
    }

    @SuppressLint("SimpleDateFormat")
    private void showOnUI() {

        if (CentralBanksExchangeRatesManipulator.retrieve() != null) {

            Date date = new Date(Long.parseLong(CentralBanksExchangeRatesManipulator.retrieve().timeStamp) * 1000L);
            SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            jdf.setTimeZone(Calendar.getInstance().getTimeZone());
            String updatedDate = jdf.format(date);
            lblUpdatedDate.setText(updatedDate);

            this.showExchangeRates();
        }
    }

    private void showExchangeRates() {

        CentralBankExchangeRateRVAdapter centralBankExchangeRateRVAdapter = new CentralBankExchangeRateRVAdapter(getActivity(),
                        (View view, int position, Model item) -> {

                            Intent intent = new Intent(this.getContext(), ConvertActivity.class);
                            intent.putExtra("model", item);
                            startActivity(intent);

                        });
        rvExchange.setHasFixedSize(true);
        rvExchange.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvExchange.setAdapter(centralBankExchangeRateRVAdapter);
        rvExchange.addItemDecoration(new RecyclerViewDividerItemDecoration(getActivity()));

        List<Model> models = new ArrayList<>();

        models.add(new Model(R.drawable.ic_flag_usd, "USD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.USD,
                CurrenciesStorage.getInstance().getUSD()));
        models.add(new Model(R.drawable.ic_flag_eur, "EUR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.EUR,
                CurrenciesStorage.getInstance().getEUR()));
        models.add(new Model(R.drawable.ic_flag_sgd, "SGD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.SGD,
                CurrenciesStorage.getInstance().getSGD()));
        models.add(new Model(R.drawable.ic_flag_jpy, "JPY",
                new DecimalFormat("##.###").format((Double.parseDouble(CentralBanksExchangeRatesManipulator.retrieve().rates.JPY.replaceAll(",","")) / 100)),
                CurrenciesStorage.getInstance().getJPY()));
        models.add(new Model(R.drawable.ic_flag_gbp, "GBP",
                CentralBanksExchangeRatesManipulator.retrieve().rates.GBP,
                CurrenciesStorage.getInstance().getGBP()));
        models.add(new Model(R.drawable.ic_flag_aud, "AUD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.AUD,
                CurrenciesStorage.getInstance().getAUD()));
        models.add(new Model(R.drawable.ic_flag_chf, "CHF",
                CentralBanksExchangeRatesManipulator.retrieve().rates.CHF,
                CurrenciesStorage.getInstance().getCHF()));
        models.add(new Model(R.drawable.ic_flag_bdt, "BDT",
                CentralBanksExchangeRatesManipulator.retrieve().rates.BDT,
                CurrenciesStorage.getInstance().getBDT()));
        models.add(new Model(R.drawable.ic_flag_bnd, "BND",
                CentralBanksExchangeRatesManipulator.retrieve().rates.BND,
                CurrenciesStorage.getInstance().getBND()));
        models.add(new Model(R.drawable.ic_flag_khr, "KHR",
                new DecimalFormat("##.###").format((Double.parseDouble(CentralBanksExchangeRatesManipulator.retrieve().rates.KHR.replaceAll(",","")) / 100)),
                CurrenciesStorage.getInstance().getKHR()));
        models.add(new Model(R.drawable.ic_flag_cad, "CAD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.CAD,
                CurrenciesStorage.getInstance().getCAD()));
        models.add(new Model(R.drawable.ic_flag_cny, "CNY",
                CentralBanksExchangeRatesManipulator.retrieve().rates.CNY,
                CurrenciesStorage.getInstance().getCNY()));
        models.add(new Model(R.drawable.ic_flag_hkd, "HKD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.HKD,
                CurrenciesStorage.getInstance().getHKD()));
        models.add(new Model(R.drawable.ic_flag_inr, "INR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.INR,
                CurrenciesStorage.getInstance().getINR()));
        models.add(new Model(R.drawable.ic_flag_idr, "IDR",
                new DecimalFormat("##.###").format((Double.parseDouble(CentralBanksExchangeRatesManipulator.retrieve().rates.IDR.replaceAll(",","")) / 100)),
                CurrenciesStorage.getInstance().getIDR()));
        models.add(new Model(R.drawable.ic_flag_krw, "KRW",
                new DecimalFormat("##.###").format((Double.parseDouble(CentralBanksExchangeRatesManipulator.retrieve().rates.KRW.replaceAll(",","")) / 100)),
                CurrenciesStorage.getInstance().getKRW()));
        models.add(new Model(R.drawable.ic_flag_lak, "LAK",
                new DecimalFormat("##.###").format((Double.parseDouble(CentralBanksExchangeRatesManipulator.retrieve().rates.LAK.replaceAll(",","")) / 100)),
                CurrenciesStorage.getInstance().getLAK()));
        models.add(new Model(R.drawable.ic_flag_myr, "MYR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.MYR,
                CurrenciesStorage.getInstance().getMYR()));
        models.add(new Model(R.drawable.ic_flag_nzd, "NZD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.NZD,
                CurrenciesStorage.getInstance().getNZD()));
        models.add(new Model(R.drawable.ic_flag_pkr, "PKR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.PKR,
                CurrenciesStorage.getInstance().getPKR()));
        models.add(new Model(R.drawable.ic_flag_php, "PHP",
                CentralBanksExchangeRatesManipulator.retrieve().rates.PHP,
                CurrenciesStorage.getInstance().getPHP()));
        models.add(new Model(R.drawable.ic_flag_lkr, "LKR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.LKR,
                CurrenciesStorage.getInstance().getLKR()));
        models.add(new Model(R.drawable.ic_flag_thb, "THB",
                CentralBanksExchangeRatesManipulator.retrieve().rates.THB,
                CurrenciesStorage.getInstance().getTHB()));
        models.add(new Model(R.drawable.ic_flag_vnd, "VND",
                new DecimalFormat("##.###").format((Double.parseDouble(CentralBanksExchangeRatesManipulator.retrieve().rates.VND.replaceAll(",","")) / 100)),
                CurrenciesStorage.getInstance().getVND()));
        models.add(new Model(R.drawable.ic_flag_brl, "BRL",
                CentralBanksExchangeRatesManipulator.retrieve().rates.BRL,
                CurrenciesStorage.getInstance().getBRL()));
        models.add(new Model(R.drawable.ic_flag_czk, "CZK",
                CentralBanksExchangeRatesManipulator.retrieve().rates.CZK,
                CurrenciesStorage.getInstance().getCZK()));
        models.add(new Model(R.drawable.ic_flag_dkk, "DKK",
                CentralBanksExchangeRatesManipulator.retrieve().rates.DKK,
                CurrenciesStorage.getInstance().getDKK()));
        models.add(new Model(R.drawable.ic_flag_egp, "EGP",
                CentralBanksExchangeRatesManipulator.retrieve().rates.EGP,
                CurrenciesStorage.getInstance().getEGP()));
        models.add(new Model(R.drawable.ic_flag_ils, "ILS",
                CentralBanksExchangeRatesManipulator.retrieve().rates.ILS,
                CurrenciesStorage.getInstance().getILS()));
        models.add(new Model(R.drawable.ic_flag_kes, "KES",
                CentralBanksExchangeRatesManipulator.retrieve().rates.KES,
                CurrenciesStorage.getInstance().getKES()));
        models.add(new Model(R.drawable.ic_flag_kwd, "KWD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.KWD,
                CurrenciesStorage.getInstance().getKWD()));
        models.add(new Model(R.drawable.ic_flag_npr, "NPR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.NPR,
                CurrenciesStorage.getInstance().getNPR()));
        models.add(new Model(R.drawable.ic_flag_nok, "NOK",
                CentralBanksExchangeRatesManipulator.retrieve().rates.NOK,
                CurrenciesStorage.getInstance().getNOK()));
        models.add(new Model(R.drawable.ic_flag_rub, "RUB",
                CentralBanksExchangeRatesManipulator.retrieve().rates.RUB,
                CurrenciesStorage.getInstance().getRUB()));
        models.add(new Model(R.drawable.ic_flag_sar, "SAR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.SAR,
                CurrenciesStorage.getInstance().getSAR()));
        models.add(new Model(R.drawable.ic_flag_rsd, "RSD",
                CentralBanksExchangeRatesManipulator.retrieve().rates.RSD,
                CurrenciesStorage.getInstance().getRSD()));
        models.add(new Model(R.drawable.ic_flag_zar, "ZAR",
                CentralBanksExchangeRatesManipulator.retrieve().rates.ZAR,
                CurrenciesStorage.getInstance().getZAR()));
        models.add(new Model(R.drawable.ic_flag_sek, "SEK",
                CentralBanksExchangeRatesManipulator.retrieve().rates.SEK,
                CurrenciesStorage.getInstance().getSEK()));
        centralBankExchangeRateRVAdapter.addItems(models);
    }
}
