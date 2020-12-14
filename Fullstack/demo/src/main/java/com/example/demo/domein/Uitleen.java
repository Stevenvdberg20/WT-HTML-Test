package com.example.demo.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Uitleen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long id;
    String lener;
    String boekTitel;
    LocalDate beginDatum;
    LocalDate eindDatum;
    String wtId;

    public String getWtId() {
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

    public String getLener() {
        return lener;
    }

    public void setLener(String lener) {
        this.lener = lener;
    }

    public String getBoekTitel() {
        return boekTitel;
    }

    public void setBoekTitel(String boekTitel) {
        this.boekTitel = boekTitel;
    }

    public LocalDate getBeginDatum() {
        return beginDatum;
    }

    public void setBeginDatum(LocalDate beginDatum) {
        this.beginDatum = beginDatum;
    }

    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(LocalDate eindDatum) {
        this.eindDatum = eindDatum;
    }
}
