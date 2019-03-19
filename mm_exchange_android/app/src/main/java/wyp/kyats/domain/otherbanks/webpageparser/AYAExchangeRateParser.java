package wyp.kyats.domain.otherbanks.webpageparser;

import android.os.AsyncTask;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import wyp.kyats.component.util.JSoupUtil;
import wyp.kyats.component.util.Logger;
import wyp.kyats.domain.otherbanks.model.ExchangeRateResponseModel;

/**
 * Created by Wai Yan on 3/14/19.
 */
public class AYAExchangeRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {

    private static final String AYA_BANK_URL = "https://www.ayabank.com/en_US/";
    private ParserResponse parserResponse;

    public AYAExchangeRateParser(ParserResponse parserResponse) {
        this.parserResponse = parserResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.parserResponse.onLoading();
    }

    @Override
    protected ExchangeRateResponseModel doInBackground(String... params) {

        String updatedDate = null;
        List<String> rawRates = new ArrayList<>();

        Document pageDocument = JSoupUtil.getData(AYA_BANK_URL);
        Logger.d(this.getClass(), "AYA PageDocument >> " + pageDocument);

        if (pageDocument != null) {

            updatedDate = pageDocument.select("#tablepress-1 > tbody > tr.row-1 > td.column-1").text();
            Logger.d(this.getClass(), "AYA Update Date >> " + updatedDate);

            String usdRate = pageDocument.select("#tablepress-1 > tbody > tr.row-2").text();
            Logger.d(this.getClass(), "AYA usdRate >> " + usdRate);
            rawRates.add(usdRate);

            String euroRate = pageDocument.select("#tablepress-1 > tbody > tr.row-3").text();
            Logger.d(this.getClass(), "AYA euro >> " + euroRate);
            rawRates.add(euroRate);

            String sgdRate = pageDocument.select("#tablepress-1 > tbody > tr.row-4").text();
            Logger.d(this.getClass(), "AYA sgdRate >> " + sgdRate);
            rawRates.add(sgdRate);
        }
        return new ExchangeRateResponseModel(updatedDate, rawRates);
    }

    @Override
    protected void onPostExecute(ExchangeRateResponseModel exchangeRateResponseModel) {
        super.onPostExecute(exchangeRateResponseModel);
        this.parserResponse.onSuccess(exchangeRateResponseModel);
    }

    public interface ParserResponse {

        void onLoading();

        void onSuccess(ExchangeRateResponseModel exchangeRateResponseModel);
    }
}

