package com.example.Projekat.services;

import com.example.Projekat.models.Oglas;
import com.example.Projekat.repositories.OglasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OglasService {

    @Autowired
    OglasRepository oglasRepository;

    public Oglas findOglasById(Integer id) { return oglasRepository.getById(id); }

    //Vrati listu oglasa

    public List<Oglas> getOglasi(){
        return oglasRepository.findAll();
    }

    //Dodaj nov oglas
    public void save(Oglas oglas){
        oglasRepository.save(oglas);
    }

    //Pretrazivanje oglasa
    public List<Oglas> findByKeyword(String keyword) {
        return oglasRepository.findByKeyword(keyword);
    }

    public void delete(Integer id){
        oglasRepository.deleteById(id);
    }

    public void updateLajk(Integer trenutniBrLajkova,Integer id) {
        oglasRepository.updateLajk(trenutniBrLajkova,id);
    }
}
