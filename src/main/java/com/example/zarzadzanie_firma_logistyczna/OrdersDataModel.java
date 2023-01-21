package com.example.zarzadzanie_firma_logistyczna;

public class OrdersDataModel {
    String data, klient, ladunek, status, kierowca, ciezarowka;

    public OrdersDataModel(String data, String nazwa, String ladunek, String status, String kierowca, String ciezarowka) {

        this.data = data;
        this.klient = nazwa;
        this.ladunek = ladunek;
        this.status = status;
        this.kierowca = kierowca;
        this.ciezarowka = ciezarowka;

    }

    public String getData_realizacji() {
        return data;
    }

    public String getNazwa() {
        return klient;
    }

    public String getTyp() {
        return ladunek;
    }

    public String getStatus() {
        return status;
    }

    public String getNazwisko() {
        return kierowca;
    }

    public String getMarka() {
        return ciezarowka;
    }


    public void setData_realizacji(String data) {
        this.data = data;
    }

    public void setNazwa(String klient) {
        this.klient = klient;
    }

    public void setTyp(String ladunek) {
        this.ladunek = ladunek;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNazwisko(String kierowca) {
        this.kierowca = kierowca;
    }

    public void setMarka(String ciezarowka) {
        this.ciezarowka = ciezarowka;
    }
}
