package wyp.kyats.ui.fragment;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import wyp.kyats.R;
import wyp.kyats.cache.otherbanks.OtherBanksExchangeRatesManipulator;
import wyp.kyats.component.ui.RecyclerViewDividerItemDecoration;
import wyp.kyats.domain.otherbanks.stringparser.KBZBankStringParser;
import wyp.kyats.domain.otherbanks.webpageparser.KBZExchangeRateParser;
import wyp.kyats.domain.otherbanks.model.ExchangeRateModel;
import wyp.kyats.domain.otherbanks.model.ExchangeRateResponseModel;
import wyp.kyats.foundation.SharedPreferencesConstants;
import wyp.kyats.ui.adapter.OthersBankExchangeRateRVAdapter;

public class KBZBankExchangeRateFragment extends OtherBanksExchangeRateBaseFragment {

    @Override
    public void getLatestExchangeRate() {

        new KBZExchangeRateParser(new KBZExchangeRateParser.ParserResponse() {

            @Override
            public void onLoading() {
                swipeContainer.setRefreshing(true);
            }

            @Override
            public void onSuccess(@NonNull ExchangeRateResponseModel exchangeRateResponseModel) {
                stopLoading();
                if (exchangeRateResponseModel.updatedDate != null) {

                    OtherBanksExchangeRatesManipulator.save(SharedPreferencesConstants.RATE_KBZ_BANK_PREFERENCES_NAME, exchangeRateResponseModel);

                    showOnUI();
                }
            }
        }).execute();
    }

    @Override
    public void showOnUI() {

        ExchangeRateResponseModel exchangeRateResponseModel = OtherBanksExchangeRatesManipulator
                .retrieve(SharedPreferencesConstants.RATE_KBZ_BANK_PREFERENCES_NAME);

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

                ExchangeRateModel exchangeRateModel = KBZBankStringParser.parse(rawRate);

                models.add(new OthersBankExchangeRateRVAdapter.Model(
                        exchangeRateModel.currency,
                        exchangeRateModel.buyRate,
                        exchangeRateModel.sellRate));
            }

            othersBankExchangeRateRVAdapter.addItems(models);
        }
    }
}
