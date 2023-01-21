package com.example.zarzadzanie_firma_logistyczna;

public class InvoicesDataModel {

    Integer invoice_ID;
    String Customer_ID, data, czy_oplacona, kwota;

    public InvoicesDataModel(Integer invoice_ID, String Customer_ID, String data, String czy_oplacona, String kwota) {

        this.invoice_ID = invoice_ID;
        this.Customer_ID = Customer_ID;
        this.data = data;
        this.czy_oplacona = czy_oplacona;
        this.kwota = kwota;

    }

    public Integer getInvoice_ID() {
        return invoice_ID;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public String getData() {
        return data;
    }

    public String getCzy_oplacona() {
        return czy_oplacona;
    }

    public String getKwota() {
        return kwota;
    }

    public void setInvoice_ID(Integer invoice_ID) {
        this.invoice_ID = invoice_ID;
    }

    public void setCustomer_ID(String Customer_ID) {
        this.Customer_ID = Customer_ID;
    }
    public void setData(String data) {
        this.data = data;
    }

    public void setCzy_oplacona(String czy_oplacona) {
        this.czy_oplacona = czy_oplacona;
    }

    public void setKwota(String kwota) {
        this.kwota = kwota;
    }


}
