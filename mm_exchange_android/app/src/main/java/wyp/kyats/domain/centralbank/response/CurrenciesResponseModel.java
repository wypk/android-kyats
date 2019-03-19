package wyp.kyats.domain.centralbank.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import wyp.kyats.domain.centralbank.model.CurrencyModel;

public class CurrenciesResponseModel implements Serializable {

    @SerializedName("info")
    public String info;

    @SerializedName("currencies")
    public CurrencyModel currencies;
}
