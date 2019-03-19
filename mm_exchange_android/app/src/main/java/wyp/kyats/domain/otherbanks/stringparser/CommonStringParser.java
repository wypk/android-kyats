package wyp.kyats.domain.otherbanks.stringparser;

import wyp.kyats.domain.otherbanks.model.ExchangeRateModel;

/**
 * Created by Wai Yan on 3/20/19.
 */
public class CommonStringParser {

    public static ExchangeRateModel parse(String rawRate) {

        String[] splitStr = rawRate.split("\\s+");

        String currency = splitStr[0];
        String buyRate = splitStr[1];
        String sellRate = splitStr[2];

        return new ExchangeRateModel(currency, buyRate, sellRate);
    }
}
