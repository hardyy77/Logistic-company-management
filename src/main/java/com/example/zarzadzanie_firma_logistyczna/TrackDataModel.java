package com.example.zarzadzanie_firma_logistyczna;

public class TrackDataModel {
    Integer track_ID;
    String data_rozpoczecia, order_ID, dlugosc, przewidywana_pogoda, sredni_stan_asfaltu, punkt_startowy, punkt_docelowy;

    public TrackDataModel(Integer track_ID, String data_rozpoczecia, String order_ID, String dlugosc, String przewidywana_pogoda, String sredni_stan_asfaltu, String punkt_startowy, String punkt_docelowy){
        this.track_ID = track_ID;
        this.data_rozpoczecia = data_rozpoczecia;
        this.order_ID = order_ID;
        this.dlugosc = dlugosc;
        this.przewidywana_pogoda = przewidywana_pogoda;
        this.sredni_stan_asfaltu = sredni_stan_asfaltu;
        this.punkt_startowy = punkt_startowy;
        this.punkt_docelowy = punkt_docelowy;
    }

    public Integer getTrack_ID() {
        return track_ID;
    }

    public String getData_rozpoczecia() {
        return data_rozpoczecia;
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public String getDlugosc() {
        return dlugosc;
    }

    public String getPrzewidywana_pogoda() {
        return przewidywana_pogoda;
    }

    public String getSredni_stan_asfaltu() {
        return sredni_stan_asfaltu;
    }

    public String getPunkt_startowy() {
        return punkt_startowy;
    }

    public String getPunkt_docelowy() {
        return punkt_docelowy;
    }

    public void setTrack_ID(Integer track_ID) {
        this.track_ID = track_ID;
    }

    public void setData_rozpoczecia(String data_rozpoczecia) {
        this.data_rozpoczecia = data_rozpoczecia;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public void setDlugosc(String dlugosc) {
        this.dlugosc = dlugosc;
    }

    public void setPrzewidywana_pogoda(String przewidywana_pogoda) {
        this.przewidywana_pogoda = przewidywana_pogoda;
    }

    public void setSredni_stan_asfaltu(String sredni_stan_asfaltu) {
        this.sredni_stan_asfaltu = sredni_stan_asfaltu;
    }

    public void setPunkt_startowy(String punkt_startowy) {
        this.punkt_startowy = punkt_startowy;
    }

    public void setPunkt_docelowy(String punkt_docelowy) {
        this.punkt_docelowy = punkt_docelowy;
    }
}
