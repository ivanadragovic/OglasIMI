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
public class PrijavaNaOglas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @ManyToOne
    @JoinColumn(name="oglasid", insertable = false, updatable = false)
    private Oglas oglas;
    private Integer oglasid;

    @ManyToOne
    @JoinColumn(name="korisnikid", insertable = false, updatable = false)
    private Korisnik korisnik;
    private Integer korisnikid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

    public Integer getOglasid() {
        return oglasid;
    }

    public void setOglasid(Integer oglasid) {
        this.oglasid = oglasid;
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