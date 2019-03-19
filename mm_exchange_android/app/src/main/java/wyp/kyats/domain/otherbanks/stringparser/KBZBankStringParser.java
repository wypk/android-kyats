package wyp.kyats.domain.otherbanks.stringparser;

import org.apache.commons.lang3.StringUtils;

import wyp.kyats.domain.otherbanks.model.ExchangeRateModel;

/**
 * Created by Wai Yan on 3/14/19.
 */
public class KBZBankStringParser {

    public static ExchangeRateModel parse(String rawRate) {

        String currency = StringUtils.substringBefore(rawRate,"BUY").trim();
        String buyRate = StringUtils.substringBetween(rawRate,"BUY","SELL").trim();
        String sellRate = StringUtils.substringAfter(rawRate,"SELL").trim();

        return new ExchangeRateModel(currency, buyRate, sellRate);
    }
}
