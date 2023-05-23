package com.example.Projekat.repositories;

import com.example.Projekat.models.Oglas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OglasRepository extends JpaRepository <Oglas, Integer> {
    @Query(value = "select * from oglas join sektor on oglas.sektorid = sektor.id" +
            " where sektor.naziv_sektora like %:keyword% or oglas.radna_pozicija like %:keyword% or oglas.posao like %:keyword%", nativeQuery = true)
    List<Oglas> findByKeyword(@Param("keyword") String keyword);

    @Override
    void deleteById(Integer integer);

    @Query(value="update oglas set oglas.broj_lajkova=:brojLajkova where oglas.id=:id",nativeQuery = true)
    void updateLajk(@Param("brojLajkova") Integer brojLajkova,@Param("id") Integer id);
}
