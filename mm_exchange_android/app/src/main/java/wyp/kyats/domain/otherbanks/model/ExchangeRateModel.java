package wyp.kyats.domain.otherbanks.model;

/**
 * Created by Wai Yan on 3/13/19.
 */
public class ExchangeRateModel {

    public String currency;
    public String buyRate;
    public String sellRate;

    public ExchangeRateModel(String currency, String buyRate, String sellRate) {
        this.currency = currency;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }
}
