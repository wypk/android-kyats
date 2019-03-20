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

package wyp.kyats.domain.otherbanks.stringparser;

import wyp.kyats.domain.otherbanks.model.ExchangeRateModel;

/**
 * Created by Wai Yan on 3/20/19.
 */
public class CommonRawRatesParser {

    public static ExchangeRateModel parse(String rawRate) {

        String[] splitStr = rawRate.split("\\s+");

        String currency = splitStr[0];
        String buyRate = splitStr[1];
        String sellRate = splitStr[2];

        return new ExchangeRateModel(currency, buyRate, sellRate);
    }
}
