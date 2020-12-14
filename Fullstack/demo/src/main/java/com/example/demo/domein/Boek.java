package com.example.demo.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Boek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long id;
    String titel;
    int titelCode;
    int exemplaar;
    String isbn;
    boolean uitgeleend;
    String wtId;

    public String getWtId() {
        //System.out.println("Binnen getwtid");
        return wtId;
    }

    public void setWtId(String wtId) {
        this.wtId = wtId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getExemplaar() {
        return exemplaar;
    }

    public void setExemplaar(int exemplaar) {
        this.exemplaar = exemplaar;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isUitgeleend() {
        return uitgeleend;
    }

    public void setUitgeleend(boolean uitgeleend) {
        this.uitgeleend = uitgeleend;
    }

    public int getTitelCode() {
        return titelCode;
    }

    public void setTitelCode(int titelCode) {
        this.titelCode = titelCode;
    }
}
