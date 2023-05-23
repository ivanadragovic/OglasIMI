package com.example.Projekat.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Sektor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nazivSektora;

    @OneToMany(mappedBy = "sektor")
    private List<Oglas> oglasi;

    public Sektor(Integer id, String nazivSektora) {
        this.id = id;
        this.nazivSektora = nazivSektora;
    }

    public Sektor(Integer id) {
        this.id = id;
    }

    public Sektor(String nazivSektora) {
        this.nazivSektora = nazivSektora;
    }

    public Sektor(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivSektora() {
        return nazivSektora;
    }

    public void setNazivSektora(String nazivSektora) {
        this.nazivSektora = nazivSektora;
    }

    public List<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(List<Oglas> oglasi) {
        this.oglasi = oglasi;
    }
}
