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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String prezime;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String ulogaIme;

//    @OneToMany(mappedBy="department", cascade = CascadeType.ALL, fetch=FetchType.EAGER)

    @ManyToOne()
    @JoinColumn(name="ulogaid", insertable = false, updatable = false)
    private Uloga uloga;
    private Integer ulogaid;

    @Column(nullable=true, length = 255)
    private String slika;


    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
//
//    public String setUlogaIme(Integer ulogaid)
//    {
//        System.out.println("uso");
//        if(ulogaid==1)
//            ulogaIme="Administrator";
//        else if(ulogaid==2) ulogaIme="Poslodavac";
//        else ulogaIme="Radnik";
//
//        return ulogaIme;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Uloga getUloga() {
        return uloga;
    }

//    public void setUloga(Uloga uloga) {
//        this.uloga = uloga;
//    }

    public Integer getUlogaid() {
        return ulogaid;
    }

    public void setUlogaid(Integer ulogaid) {
        this.ulogaid = ulogaid;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uloga=" + uloga +
                ", ulogaid=" + ulogaid +
                ", slika='" + slika + '\'' +
                '}';
    }

    @Transient
    public String getImagePath(){
        if(slika==null || username==null) return null;
        return "/src/main/resources/static/assets/images/photos/" + username+ "/" +slika;
    }

}
