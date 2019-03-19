package wyp.kyats.cache.otherbanks;

import com.google.gson.Gson;

import wyp.kyats.domain.otherbanks.model.ExchangeRateResponseModel;

/**
 * Created by Wai Yan on 3/18/19.
 */
public class OtherBanksGSonMapper {

    public static String toJsonString(ExchangeRateResponseModel exchangeRateResponseModel) {

        return new Gson().toJson(exchangeRateResponseModel);
    }

    public static ExchangeRateResponseModel toPoJo(String json) {

        return new Gson().fromJson(json, ExchangeRateResponseModel.class);
    }
}
