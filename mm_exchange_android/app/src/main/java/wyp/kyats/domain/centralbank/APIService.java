package wyp.kyats.domain.centralbank;

import retrofit2.Call;
import retrofit2.http.GET;
import wyp.kyats.domain.centralbank.response.CurrenciesResponseModel;
import wyp.kyats.domain.centralbank.response.ExchangeRateResponseModel;

public interface APIService {

    @GET("/api/latest")
    Call<ExchangeRateResponseModel> getLatestExchangeRates();

    @GET("/api/currencies")
    Call<CurrenciesResponseModel> getCurrencies();
}
