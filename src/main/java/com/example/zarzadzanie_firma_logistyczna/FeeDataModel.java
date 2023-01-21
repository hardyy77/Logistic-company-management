package com.example.zarzadzanie_firma_logistyczna;

public class FeeDataModel {

    Integer fee_ID;
    String prad, internet, ubezpieczenia, pensje, oprogramowanie, szkolenia, marketing, paliwo, data;
    public FeeDataModel(Integer fee_ID, String prad, String internet, String ubezpieczenia, String pensje, String oprogramowanie, String szkolenaia, String marketing, String paliwo, String data) {
        this.fee_ID = fee_ID;
        this.prad = prad;
        this.internet = internet;
        this.ubezpieczenia = ubezpieczenia;
        this.pensje = pensje;
        this.oprogramowanie = oprogramowanie;
        this.szkolenia = szkolenaia;
        this.marketing = marketing;
        this.paliwo = paliwo;
        this.data = data;

    }

    public Integer getFee_ID() {
        return fee_ID;
    }

    public String getPrad() {
        return prad;
    }

    public String getInternet() {
        return internet;
    }

    public String getUbezpieczenia() {
        return ubezpieczenia;
    }

    public String getPensje() {
        return pensje;
    }

    public String getOprogramowanie() {
        return oprogramowanie;
    }

    public String getSzkolenia() {
        return szkolenia;
    }

    public String getMarketing() {
        return marketing;
    }

    public String getPaliwo() {
        return paliwo;
    }

    public String getData() {
        return data;
    }

    public void setFee_ID(Integer fee_ID) {
        this.fee_ID = fee_ID;
    }

    public void setPrad(String prad) {
        this.prad = prad;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public void setUbezpieczenia(String ubezpieczenia) {
        this.ubezpieczenia = ubezpieczenia;
    }

    public void setPensje(String pensje) {
        this.pensje = pensje;
    }

    public void setOprogramowanie(String oprogramowanie) {
        this.oprogramowanie = oprogramowanie;
    }

    public void setSzkolenia(String szkolenia) {
        this.szkolenia = szkolenia;
    }

    public void setMarketing(String marketing) {
        this.marketing = marketing;
    }

    public void setPaliwo(String paliwo) {
        this.paliwo = paliwo;
    }

    public void setData(String data) {
        this.data = data;
    }
}
