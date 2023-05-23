package com.example.Projekat.repositories;

import com.example.Projekat.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository <Korisnik, Integer> {

    Korisnik findByUsername(String username);

    Korisnik findByEmail(String email);

    @Query(value = "update korisnik set korisnik.ulogaid=:uid where korisnik.id =:id", nativeQuery = true)
    void updateUserId(@Param("id") Integer id, @Param("uid") Integer uid);

    @Query(value = "update korisnik set korisnik.slika=:fileName where korisnik.id =:id", nativeQuery = true)
    void updateSlika(@Param("fileName") String fileName, @Param("id") Integer id);

    @Query(value = "update korisnik set korisnik.ime=:ime,korisnik.prezime=:prezime,korisnik.username=:username,korisnik.email=:email where korisnik.id =:id", nativeQuery = true)
    void updateProfil(@Param("id") Integer id,@Param("ime") String ime,@Param("prezime") String prezime,@Param("email") String email,@Param("username") String usrname);

    @Query(value = "update korisnik set korisnik.ime=:ime,korisnik.prezime=:prezime,korisnik.username=:username,korisnik.email=:email, korisnik.slika=:slika where korisnik.id =:id", nativeQuery = true)
    void updateProfilSaSlikom(@Param("id") Integer id,@Param("ime") String ime,@Param("prezime") String prezime,@Param("email") String email,@Param("username") String usrname,@Param("slika") String slika);

    @Override
    void deleteById(Integer id);
}
