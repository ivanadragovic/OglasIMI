package com.example.Projekat.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class Lajk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name="userid", insertable = false, updatable = false)
    private Korisnik korisnik;
    private Integer userid;

    @ManyToOne()
    @JoinColumn(name="oglasid", insertable = false, updatable = false)
    private Oglas oglas;
    private Integer oglasid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Lajk(Integer userid, Integer oglasid) {
        this.userid = userid;
        this.oglasid = oglasid;
    }
}
