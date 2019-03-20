/*
 * Copyright 2019 Wai Yan - (09 97777 3 444).
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.kyats.cache.centralbank;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CurrenciesStorage {

    private final static String PREFERENCES_NAME = "mm.exchange.CurrenciesStorage";

    private final static String CNY = PREFERENCES_NAME + "CNY";
    private final static String JPY = PREFERENCES_NAME + "JPY";
    private final static String CZK = PREFERENCES_NAME + "CZK";
    private final static String KWD = PREFERENCES_NAME + "KWD";
    private final static String BDT = PREFERENCES_NAME + "BDT";
    private final static String LKR = PREFERENCES_NAME + "LKR";
    private final static String VND = PREFERENCES_NAME + "VND";
    private final static String CAD = PREFERENCES_NAME + "CAD";
    private final static String ZAR = PREFERENCES_NAME + "ZAR";
    private final static String AUD = PREFERENCES_NAME + "AUD";
    private final static String NZD = PREFERENCES_NAME + "NZD";
    private final static String KES = PREFERENCES_NAME + "KES";
    private final static String GBP = PREFERENCES_NAME + "GBP";
    private final static String NOK = PREFERENCES_NAME + "NOK";
    private final static String ILS = PREFERENCES_NAME + "ILS";
    private final static String CHF = PREFERENCES_NAME + "CHF";
    private final static String RUB = PREFERENCES_NAME + "RUB";
    private final static String SAR = PREFERENCES_NAME + "SAR";
    private final static String PKR = PREFERENCES_NAME + "PKR";
    private final static String EGP = PREFERENCES_NAME + "EGP";
    private final static String INR = PREFERENCES_NAME + "INR";
    private final static String THB = PREFERENCES_NAME + "THB";
    private final static String IDR = PREFERENCES_NAME + "IDR";
    private final static String SGD = PREFERENCES_NAME + "SGD";
    private final static String HKD = PREFERENCES_NAME + "HKD";
    private final static String KHR = PREFERENCES_NAME + "KHR";
    private final static String DKK = PREFERENCES_NAME + "DKK";
    private final static String SEK = PREFERENCES_NAME + "SEK";
    private final static String RSD = PREFERENCES_NAME + "RSD";
    private final static String MYR = PREFERENCES_NAME + "MYR";
    private final static String BRL = PREFERENCES_NAME + "BRL";
    private final static String EUR = PREFERENCES_NAME + "EUR";
    private final static String NPR = PREFERENCES_NAME + "NPR";
    private final static String PHP = PREFERENCES_NAME + "PHP";
    private final static String USD = PREFERENCES_NAME + "USD";
    private final static String LAK = PREFERENCES_NAME + "LAK";
    private final static String KRW = PREFERENCES_NAME + "KRW";
    private final static String BND = PREFERENCES_NAME + "BND";

    private static CurrenciesStorage currenciesStorage = null;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private CurrenciesStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static void initialize(Context context) {

        if (currenciesStorage == null) {
            currenciesStorage = new CurrenciesStorage(context);
        }
    }

    public synchronized static CurrenciesStorage getInstance() {

        return CurrenciesStorage.currenciesStorage;
    }

    public String getCNY() {
        return this.sharedPreferences.getString(CNY, null);
    }

    public void setCNY(String cny) {
        this.editor.putString(CNY, cny);
        this.editor.commit();
    }

    public String getJPY() {
        return this.sharedPreferences.getString(JPY, null);
    }

    public void setJPY(String jpy) {
        this.editor.putString(JPY, jpy);
        this.editor.commit();
    }

    public String getCZK() {
        return this.sharedPreferences.getString(CZK, null);
    }

    public void setCZK(String czk) {
        this.editor.putString(CZK, czk);
        this.editor.commit();
    }

    public String getKWD() {
        return this.sharedPreferences.getString(KWD, null);
    }

    public void setKWD(String kwd) {
        this.editor.putString(KWD, kwd);
        this.editor.commit();
    }

    public String getBDT() {
        return this.sharedPreferences.getString(BDT, null);
    }

    public void setBDT(String bdt) {
        this.editor.putString(BDT, bdt);
        this.editor.commit();
    }

    public String getLKR() {
        return this.sharedPreferences.getString(LKR, null);
    }

    public void setLKR(String lkr) {
        this.editor.putString(LKR, lkr);
        this.editor.commit();
    }

    public String getVND() {
        return this.sharedPreferences.getString(VND, null);
    }

    public void setVND(String vnd) {
        this.editor.putString(VND, vnd);
        this.editor.commit();
    }

    public String getCAD() {
        return this.sharedPreferences.getString(CAD, null);
    }

    public void setCAD(String cad) {
        this.editor.putString(CAD, cad);
        this.editor.commit();
    }

    public String getZAR() {
        return this.sharedPreferences.getString(ZAR, null);
    }

    public void setZAR(String zar) {
        this.editor.putString(ZAR, zar);
        this.editor.commit();
    }

    public String getAUD() {
        return this.sharedPreferences.getString(AUD, null);
    }

    public void setAUD(String aud) {
        this.editor.putString(AUD, aud);
        this.editor.commit();
    }

    public String getNZD() {
        return this.sharedPreferences.getString(NZD, null);
    }

    public void setNZD(String nzd) {
        this.editor.putString(NZD, nzd);
        this.editor.commit();
    }

    public String getKES() {
        return this.sharedPreferences.getString(KES, null);
    }

    public void setKES(String kes) {
        this.editor.putString(KES, kes);
        this.editor.commit();
    }

    public String getGBP() {
        return this.sharedPreferences.getString(GBP, null);
    }

    public void setGBP(String gbp) {
        this.editor.putString(GBP, gbp);
        this.editor.commit();
    }

    public String getNOK() {
        return this.sharedPreferences.getString(NOK, null);
    }

    public void setNOK(String nok) {
        this.editor.putString(NOK, nok);
        this.editor.commit();
    }

    public String getILS() {
        return this.sharedPreferences.getString(ILS, null);
    }

    public void setILS(String ils) {
        this.editor.putString(ILS, ils);
        this.editor.commit();
    }

    public String getCHF() {
        return this.sharedPreferences.getString(CHF, null);
    }

    public void setCHF(String chf) {
        this.editor.putString(CHF, chf);
        this.editor.commit();
    }

    public String getRUB() {
        return this.sharedPreferences.getString(RUB, null);
    }

    public void setRUB(String rub) {
        this.editor.putString(RUB, rub);
        this.editor.commit();
    }

    public String getSAR() {
        return this.sharedPreferences.getString(SAR, null);
    }

    public void setSAR(String sar) {
        this.editor.putString(SAR, sar);
        this.editor.commit();
    }

    public String getPKR() {
        return this.sharedPreferences.getString(PKR, null);
    }

    public void setPKR(String pkr) {
        this.editor.putString(PKR, pkr);
        this.editor.commit();
    }

    public String getEGP() {
        return this.sharedPreferences.getString(EGP, null);
    }

    public void setEGP(String egp) {
        this.editor.putString(EGP, egp);
        this.editor.commit();
    }

    public String getINR() {
        return this.sharedPreferences.getString(INR, null);
    }

    public void setINR(String inr) {
        this.editor.putString(INR, inr);
        this.editor.commit();
    }

    public String getTHB() {
        return this.sharedPreferences.getString(THB, null);
    }

    public void setTHB(String thb) {
        this.editor.putString(THB, thb);
        this.editor.commit();
    }

    public String getIDR() {
        return this.sharedPreferences.getString(IDR, null);
    }

    public void setIDR(String idr) {
        this.editor.putString(IDR, idr);
        this.editor.commit();
    }

    public String getSGD() {
        return this.sharedPreferences.getString(SGD, null);
    }

    public void setSGD(String sgd) {
        this.editor.putString(SGD, sgd);
        this.editor.commit();
    }

    public String getHKD() {
        return this.sharedPreferences.getString(HKD, null);
    }

    public void setHKD(String hkd) {
        this.editor.putString(HKD, hkd);
        this.editor.commit();
    }

    public String getKHR() {
        return this.sharedPreferences.getString(KHR, null);
    }

    public void setKHR(String khr) {
        this.editor.putString(KHR, khr);
        this.editor.commit();
    }

    public String getDKK() {
        return this.sharedPreferences.getString(DKK, null);
    }

    public void setDKK(String dkk) {
        this.editor.putString(DKK, dkk);
        this.editor.commit();
    }

    public String getSEK() {
        return this.sharedPreferences.getString(SEK, null);
    }

    public void setSEK(String sek) {
        this.editor.putString(SEK, sek);
        this.editor.commit();
    }

    public String getRSD() {
        return this.sharedPreferences.getString(RSD, null);
    }

    public void setRSD(String rsd) {
        this.editor.putString(RSD, rsd);
        this.editor.commit();
    }

    public String getMYR() {
        return this.sharedPreferences.getString(MYR, null);
    }

    public void setMYR(String myr) {
        this.editor.putString(MYR, myr);
        this.editor.commit();
    }

    public String getBRL() {
        return this.sharedPreferences.getString(BRL, null);
    }

    public void setBRL(String brl) {
        this.editor.putString(BRL, brl);
        this.editor.commit();
    }

    public String getEUR() {
        return this.sharedPreferences.getString(EUR, null);
    }

    public void setEUR(String eur) {
        this.editor.putString(EUR, eur);
        this.editor.commit();
    }

    public String getNPR() {
        return this.sharedPreferences.getString(NPR, null);
    }

    public void setNPR(String npr) {
        this.editor.putString(NPR, npr);
        this.editor.commit();
    }

    public String getPHP() {
        return this.sharedPreferences.getString(PHP, null);
    }

    public void setPHP(String php) {
        this.editor.putString(PHP, php);
        this.editor.commit();
    }

    public String getUSD() {
        return this.sharedPreferences.getString(USD, null);
    }

    public void setUSD(String usd) {
        this.editor.putString(USD, usd);
        this.editor.commit();
    }

    public String getLAK() {
        return this.sharedPreferences.getString(LAK, null);
    }

    public void setLAK(String lak) {
        this.editor.putString(LAK, lak);
        this.editor.commit();
    }

    public String getKRW() {
        return this.sharedPreferences.getString(KRW, null);
    }

    public void setKRW(String krw) {
        this.editor.putString(KRW, krw);
        this.editor.commit();
    }

    public String getBND() {
        return this.sharedPreferences.getString(BND, null);
    }

    public void setBND(String bnd) {
        this.editor.putString(BND, bnd);
        this.editor.commit();
    }

    public boolean isCredentialsAvailable() {

        return (this.getUSD() != null && !this.getUSD().isEmpty()) &&
                (this.getEUR() != null && !this.getEUR().isEmpty()) &&
                (this.getSGD() != null && !this.getSGD().isEmpty()) &&
                (this.getGBP() != null && !this.getGBP().isEmpty()) &&
                (this.getCHF() != null && !this.getCHF().isEmpty()) &&
                (this.getJPY() != null && !this.getJPY().isEmpty()) &&
                (this.getAUD() != null && !this.getAUD().isEmpty()) &&
                (this.getBDT() != null && !this.getBDT().isEmpty()) &&
                (this.getBND() != null && !this.getBND().isEmpty()) &&
                (this.getKHR() != null && !this.getKHR().isEmpty()) &&
                (this.getCAD() != null && !this.getCAD().isEmpty()) &&
                (this.getHKD() != null && !this.getHKD().isEmpty()) &&
                (this.getINR() != null && !this.getINR().isEmpty()) &&
                (this.getIDR() != null && !this.getIDR().isEmpty()) &&
                (this.getKRW() != null && !this.getKRW().isEmpty()) &&
                (this.getLAK() != null && !this.getLAK().isEmpty()) &&
                (this.getMYR() != null && !this.getMYR().isEmpty()) &&
                (this.getNZD() != null && !this.getNZD().isEmpty()) &&
                (this.getPKR() != null && !this.getPKR().isEmpty()) &&
                (this.getPHP() != null && !this.getPHP().isEmpty()) &&
                (this.getTHB() != null && !this.getTHB().isEmpty()) &&
                (this.getVND() != null && !this.getVND().isEmpty()) &&
                (this.getBRL() != null && !this.getBRL().isEmpty()) &&
                (this.getCZK() != null && !this.getCZK().isEmpty()) &&
                (this.getDKK() != null && !this.getDKK().isEmpty()) &&
                (this.getGBP() != null && !this.getGBP().isEmpty()) &&
                (this.getILS() != null && !this.getILS().isEmpty()) &&
                (this.getKES() != null && !this.getKES().isEmpty()) &&
                (this.getKWD() != null && !this.getKWD().isEmpty()) &&
                (this.getNPR() != null && !this.getNPR().isEmpty()) &&
                (this.getNOK() != null && !this.getNOK().isEmpty()) &&
                (this.getRUB() != null && !this.getRUB().isEmpty()) &&
                (this.getSAR() != null && !this.getSAR().isEmpty()) &&
                (this.getRSD() != null && !this.getRSD().isEmpty()) &&
                (this.getZAR() != null && !this.getZAR().isEmpty()) &&
                (this.getSEK() != null && !this.getSEK().isEmpty());
    }
}
