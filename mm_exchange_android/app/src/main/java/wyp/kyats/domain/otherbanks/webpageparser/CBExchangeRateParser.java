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

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import wyp.kyats.component.util.JSoupUtil;
import wyp.kyats.component.util.Logger;
import wyp.kyats.domain.otherbanks.model.ExchangeRateResponseModel;

/**
 * Created by Wai Yan on 3/14/19.
 */
public class CBExchangeRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {

    private static final String CB_BANK_URL = "https://www.cbbank.com.mm/exchange_rate.aspx";
    private ParserResponse parserResponse;

    public CBExchangeRateParser(ParserResponse parserResponse) {
        this.parserResponse = parserResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.parserResponse.onLoading();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected ExchangeRateResponseModel doInBackground(String... params) {

        List<String> rawRates = new ArrayList<>();
        String updatedDate = null;

        Document pageDocument = JSoupUtil.getData(CB_BANK_URL);
        Logger.d(this.getClass(), "CB PageDocument >> " + pageDocument);

        if (pageDocument != null) {

            updatedDate = pageDocument.select("#form1 > div:nth-child(3) > table > tbody > tr:nth-child(7) > td > label").text();
            updatedDate = StringUtils.substringAfter(updatedDate,"Updated on").trim();
            Logger.d(this.getClass(), "CB Update Date >> " + updatedDate);

            Elements tableElement = pageDocument.select("#form1 > div:nth-child(3) > table > tbody");

            for (Element element : tableElement.select("tr")) {
                rawRates.add(element.text());
            }

            Logger.d(this.getClass(), "CB rawRates >> " + rawRates);

            //Remove First and Last Column
            if (rawRates.size() > 0) {
                rawRates.remove(0); //First
                rawRates.remove(rawRates.size() - 1); //Last
            }
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

