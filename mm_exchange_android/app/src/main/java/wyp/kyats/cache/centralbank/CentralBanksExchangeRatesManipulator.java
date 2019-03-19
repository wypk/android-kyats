package wyp.kyats.cache.centralbank;

import wyp.kyats.domain.centralbank.response.ExchangeRateResponseModel;

/**
 * Created by Wai Yan on 3/18/19.
 */
public class CentralBanksExchangeRatesManipulator {

    public static void save(ExchangeRateResponseModel exchangeRateResponseModel) {

        ExchangeRatesStorage.getInstance().setData(CentralBanksGSonMapper.toJsonString(exchangeRateResponseModel));
    }

    public static ExchangeRateResponseModel retrieve() {

        return CentralBanksGSonMapper.toPoJo(ExchangeRatesStorage.getInstance().getData());
    }
}
