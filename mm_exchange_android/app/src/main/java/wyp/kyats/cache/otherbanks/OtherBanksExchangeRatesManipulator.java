package wyp.kyats.cache.otherbanks;

import wyp.kyats.domain.otherbanks.model.ExchangeRateResponseModel;

/**
 * Created by Wai Yan on 3/18/19.
 */
public class OtherBanksExchangeRatesManipulator {

    public static void save(String key,ExchangeRateResponseModel exchangeRateResponseModel) {

        OtherBanksExchangeRatesStorage.getInstance().setData(key,OtherBanksGSonMapper.toJsonString(exchangeRateResponseModel));
    }

    public static ExchangeRateResponseModel retrieve(String key) {

        return OtherBanksGSonMapper.toPoJo(OtherBanksExchangeRatesStorage.getInstance().getData(key));
    }
}
