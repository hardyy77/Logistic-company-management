package com.example.zarzadzanie_firma_logistyczna;

public class CustomersDataModel {

    Integer customer_ID;
    String nazwa, miasto, adres, email, nr_tel, nip;

    public CustomersDataModel(Integer customer_ID, String nazwa, String miasto, String adres, String email, String nr_tel, String nip) {

        this.customer_ID = customer_ID;
        this.nazwa = nazwa;
        this.miasto = miasto;
        this.adres = adres;
        this.email = email;
        this.nr_tel = nr_tel;
        this.nip = nip;

    }

    public CustomersDataModel() {

    }


    public Integer getCustomer_ID() {
        return customer_ID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getAdres() {
        return adres;
    }

    public String getEmail() {
        return email;
    }

    public String getNr_tel() {
        return nr_tel;
    }

    public String getNip() {
        return nip;
    }

    public void setCustomer_ID(Integer customer_ID) {
        this.customer_ID = customer_ID;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }


}
