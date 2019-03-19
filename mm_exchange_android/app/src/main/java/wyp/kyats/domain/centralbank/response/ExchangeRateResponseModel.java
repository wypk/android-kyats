package wyp.kyats.domain.centralbank.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import wyp.kyats.domain.centralbank.model.CurrencyModel;

public class ExchangeRateResponseModel implements Serializable {

    @SerializedName("info")
    public String info;

    @SerializedName("timestamp")
    public String timeStamp;

    @SerializedName("rates")
    public CurrencyModel rates;
}
