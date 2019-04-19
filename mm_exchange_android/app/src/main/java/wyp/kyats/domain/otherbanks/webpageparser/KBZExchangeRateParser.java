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

package wyp.kyats.domain.otherbanks.webpageparser;

import android.os.AsyncTask;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import wyp.kyats.component.util.JSoupUtil;
import wyp.kyats.component.util.Logger;
import wyp.kyats.domain.otherbanks.model.ExchangeRateResponseModel;


/**
 * Created by Wai Yan on 3/13/19.
 */
public class KBZExchangeRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {

    private static final String KBZ_BANK_URL = "https://www.kbzbank.com/en/";
    private ParserResponse parserResponse;

    public KBZExchangeRateParser(ParserResponse parserResponse) {
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

        Document pageDocument = JSoupUtil.getData(KBZ_BANK_URL);
        if (pageDocument != null) {

            updatedDate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-1 > div > p.has-text-color.has-vivid-red-color")
                    .first()
                    .text();
            updatedDate = StringUtils.substringAfter(updatedDate, "Date").trim().replace("–", "");

            Logger.d(this.getClass(), "KBZ Update Date >> " + updatedDate);

            String usdBuyRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-1 > div > p:nth-child(2)")
                    .get(1)
                    .text();
            Logger.d(this.getClass(), "KBZ usdBuyRate >> " + usdBuyRate);

            String usdSellRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-1 > div > p:nth-child(3)")
                    .first()
                    .text();
            Logger.d(this.getClass(), "KBZ usdSellRate >> " + usdSellRate);

            rawRates.add("USD" + usdBuyRate + usdSellRate);

            String sgdBuyRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-2 > div > p:nth-child(2)")
                    .first()
                    .text();
            Logger.d(this.getClass(), "KBZ sgdBuyRate >> " + sgdBuyRate);

            String sgdSellRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-2 > div > p:nth-child(3)")
                    .first()
                    .text();
            Logger.d(this.getClass(), "KBZ sgdSellRate >> " + sgdSellRate);

            rawRates.add("SGD" + sgdBuyRate + sgdSellRate);

            String eurBuyRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-3 > div > p:nth-child(2)")
                    .text();
            Logger.d(this.getClass(), "KBZ eurBuyRate >> " + eurBuyRate);

            String eurSellRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-3 > div > p:nth-child(3)")
                    .text();
            Logger.d(this.getClass(), "KBZ eurSellRate >> " + eurSellRate);

            rawRates.add("EUR" + eurBuyRate + eurSellRate);

            String thbBuyRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-4 > div > p:nth-child(2)")
                    .text();
            Logger.d(this.getClass(), "KBZ thbBuyRate >> " + thbBuyRate);

            String thbSellRate = pageDocument
                    .select("div > div.wp-block-kadence-column.inner-column-4 > div > p:nth-child(3)")
                    .text();
            Logger.d(this.getClass(), "KBZ thbSellRate >> " + thbSellRate);

            rawRates.add("THB" + thbBuyRate + thbSellRate);
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
