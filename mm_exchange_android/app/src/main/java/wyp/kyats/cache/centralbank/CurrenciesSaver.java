package wyp.kyats.cache.centralbank;

import wyp.kyats.domain.centralbank.response.CurrenciesResponseModel;

public class CurrenciesSaver {

    private CurrenciesResponseModel currenciesResponseModel;

    public CurrenciesSaver(CurrenciesResponseModel currenciesResponseModel) {
        this.currenciesResponseModel = currenciesResponseModel;
    }

    public void save() {

        CurrenciesStorage.getInstance().setUSD(currenciesResponseModel.currencies.USD);
        CurrenciesStorage.getInstance().setEUR(currenciesResponseModel.currencies.EUR);
        CurrenciesStorage.getInstance().setSGD(currenciesResponseModel.currencies.SGD);
        CurrenciesStorage.getInstance().setGBP(currenciesResponseModel.currencies.GBP);
        CurrenciesStorage.getInstance().setCHF(currenciesResponseModel.currencies.CHF);
        CurrenciesStorage.getInstance().setJPY(currenciesResponseModel.currencies.JPY);
        CurrenciesStorage.getInstance().setAUD(currenciesResponseModel.currencies.AUD);
        CurrenciesStorage.getInstance().setBDT(currenciesResponseModel.currencies.BDT);
        CurrenciesStorage.getInstance().setBND(currenciesResponseModel.currencies.BND);
        CurrenciesStorage.getInstance().setKHR(currenciesResponseModel.currencies.KHR);
        CurrenciesStorage.getInstance().setCAD(currenciesResponseModel.currencies.CAD);
        CurrenciesStorage.getInstance().setCNY(currenciesResponseModel.currencies.CNY);
        CurrenciesStorage.getInstance().setHKD(currenciesResponseModel.currencies.HKD);
        CurrenciesStorage.getInstance().setINR(currenciesResponseModel.currencies.INR);
        CurrenciesStorage.getInstance().setIDR(currenciesResponseModel.currencies.IDR);
        CurrenciesStorage.getInstance().setKRW(currenciesResponseModel.currencies.KRW);
        CurrenciesStorage.getInstance().setLAK(currenciesResponseModel.currencies.LAK);
        CurrenciesStorage.getInstance().setMYR(currenciesResponseModel.currencies.MYR);
        CurrenciesStorage.getInstance().setNZD(currenciesResponseModel.currencies.NZD);
        CurrenciesStorage.getInstance().setPKR(currenciesResponseModel.currencies.PKR);
        CurrenciesStorage.getInstance().setPHP(currenciesResponseModel.currencies.PHP);
        CurrenciesStorage.getInstance().setLKR(currenciesResponseModel.currencies.LKR);
        CurrenciesStorage.getInstance().setTHB(currenciesResponseModel.currencies.THB);
        CurrenciesStorage.getInstance().setVND(currenciesResponseModel.currencies.VND);
        CurrenciesStorage.getInstance().setBRL(currenciesResponseModel.currencies.BRL);
        CurrenciesStorage.getInstance().setCZK(currenciesResponseModel.currencies.CZK);
        CurrenciesStorage.getInstance().setDKK(currenciesResponseModel.currencies.DKK);
        CurrenciesStorage.getInstance().setEGP(currenciesResponseModel.currencies.EGP);
        CurrenciesStorage.getInstance().setILS(currenciesResponseModel.currencies.ILS);
        CurrenciesStorage.getInstance().setKES(currenciesResponseModel.currencies.KES);
        CurrenciesStorage.getInstance().setKWD(currenciesResponseModel.currencies.KWD);
        CurrenciesStorage.getInstance().setNPR(currenciesResponseModel.currencies.NPR);
        CurrenciesStorage.getInstance().setNOK(currenciesResponseModel.currencies.NOK);
        CurrenciesStorage.getInstance().setRUB(currenciesResponseModel.currencies.RUB);
        CurrenciesStorage.getInstance().setSAR(currenciesResponseModel.currencies.SAR);
        CurrenciesStorage.getInstance().setRSD(currenciesResponseModel.currencies.RSD);
        CurrenciesStorage.getInstance().setZAR(currenciesResponseModel.currencies.ZAR);
        CurrenciesStorage.getInstance().setSEK(currenciesResponseModel.currencies.SEK);
    }
}
