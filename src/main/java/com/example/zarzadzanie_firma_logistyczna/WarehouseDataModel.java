package com.example.zarzadzanie_firma_logistyczna;

public class WarehouseDataModel {

    Integer item_ID;
    String dostepnosc, regal, polka, typ, uwagi, waga;

    public WarehouseDataModel(Integer item_ID, String dostepnosc, String regal, String polka, String typ, String uwagi, String waga) {

        this.item_ID = item_ID;
        this.dostepnosc = dostepnosc;
        this.regal = regal;
        this.polka = polka;
        this.typ = typ;
        this.uwagi = uwagi;
        this.waga = waga;

    }

    public Integer getItem_ID() {
        return item_ID;
    }

    public String getDostepnosc() {
        return dostepnosc;
    }

    public String getRegal() {
        return regal;
    }

    public String getPolka() {
        return polka;
    }

    public String getTyp() {
        return typ;
    }

    public String getUwagi() {
        return uwagi;
    }

    public String getWaga() {
        return waga;
    }

    public void setItem_ID(Integer item_ID) {
        this.item_ID = item_ID;
    }

    public void setDostepnosc(String dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    public void setRegal(String regal) {
        this.regal = regal;
    }

    public void setPolka(String polka) {
        this.polka = polka;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public void setWaga(String waga) {
        this.waga = waga;
    }
}
