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

package wyp.kyats.cache.centralbank;

import com.google.gson.Gson;

import wyp.kyats.domain.centralbank.response.ExchangeRateResponseModel;

/**
 * Created by Wai Yan on 3/18/19.
 */
public class CentralBanksGSonMapper {

    public static String toJsonString(ExchangeRateResponseModel exchangeRateResponseModel) {

        return new Gson().toJson(exchangeRateResponseModel);
    }

    public static ExchangeRateResponseModel toPoJo(String json) {

        return new Gson().fromJson(json, ExchangeRateResponseModel.class);
    }
}
