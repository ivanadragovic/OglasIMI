package com.example.Projekat.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oglas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String posao;
    private Integer plata;
    private String radnaPozicija;
    private String radOdKuce;
    private Integer brojLajkova=0;

    @Column(length = 10000)
    private String opis;

    @ManyToOne
    @JoinColumn(name="sektorid", insertable = false, updatable = false)
    private Sektor sektor;
    private Integer sektorid;

    @ManyToOne()
    @JoinColumn(name="korisnikid", insertable = false, updatable = false)
    private Korisnik korisnik;
    private Integer korisnikid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosao() {
        return posao;
    }

    public void setPosao(String posao) {
        this.posao = posao;
    }

    public Integer getPlata() {
        return plata;
    }

    public void setPlata(Integer plata) {
        this.plata = plata;
    }

    public String getRadnaPozicija() {
        return radnaPozicija;
    }

    public void setRadnaPozicija(String radnaPozicija) {
        this.radnaPozicija = radnaPozicija;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Sektor getSektor() {
        return sektor;
    }

    public void setSektor(Sektor sektor) {
        this.sektor = sektor;
    }

    public Integer getSektorid() {
        return sektorid;
    }

    public void setSektorid(Integer sektorid) {
        this.sektorid = sektorid;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Integer getKorisnikid() {
        return korisnikid;
    }

    public void setKorisnikid(Integer korisnikid) {
        this.korisnikid = korisnikid;
    }
}
