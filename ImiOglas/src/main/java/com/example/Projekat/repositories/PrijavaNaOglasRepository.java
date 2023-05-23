package com.example.Projekat.repositories;

import com.example.Projekat.models.PrijavaNaOglas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrijavaNaOglasRepository extends JpaRepository<PrijavaNaOglas, Integer> {

}