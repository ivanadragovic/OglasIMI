package com.example.Projekat.repositories;

import com.example.Projekat.models.Sektor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SektorRepository extends JpaRepository <Sektor, Integer> {


}
