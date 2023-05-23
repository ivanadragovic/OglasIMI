package com.example.Projekat.repositories;

import com.example.Projekat.models.Lajk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LajkRepository extends JpaRepository<Lajk,Integer> {

}
