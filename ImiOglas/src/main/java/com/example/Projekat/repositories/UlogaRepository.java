package com.example.Projekat.repositories;

import com.example.Projekat.models.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UlogaRepository extends JpaRepository <Uloga, Integer> {


}
