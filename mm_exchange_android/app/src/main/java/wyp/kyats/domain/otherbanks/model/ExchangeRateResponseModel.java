package wyp.kyats.domain.otherbanks.model;

import java.util.List;

/**
 * Created by Wai Yan on 3/13/19.
 */
public class ExchangeRateResponseModel {

    public String updatedDate;
    public List<String> rawRate;

    public ExchangeRateResponseModel(String updatedDate, List<String> rawRate) {
        this.updatedDate = updatedDate;
        this.rawRate = rawRate;
    }
}
