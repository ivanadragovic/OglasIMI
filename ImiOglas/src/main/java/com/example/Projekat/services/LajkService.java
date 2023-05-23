package com.example.Projekat.services;

import com.example.Projekat.models.Lajk;
import com.example.Projekat.repositories.LajkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LajkService {

    @Autowired
    LajkRepository lajkRepository;

    public Optional<Lajk> findLajkById(Integer id) { return lajkRepository.findById(id); }

    public List<Lajk> getLajkovi(){
        return lajkRepository.findAll();
    }

    public void save(Lajk lajk){
        lajkRepository.save(lajk);
    }

    public void delete(Lajk lajk){
        lajkRepository.delete(lajk);
    }

    //brisanje svih lajkova za oglas koji se brise
    public void brisanjeLajkovaO(Integer id)
    {
        List<Lajk> lajkovi = lajkRepository.findAll();
        for(int i=0; i<lajkovi.size(); i++)
        {
            Lajk trenutniLajk = lajkovi.get(i);
            if(trenutniLajk.getOglasid().equals(id))
            {
                lajkRepository.delete(trenutniLajk);
            }
        }
    }

    //brisanje svih lajkova za korisnika koji se brise
    public void brisanjeLajkovaK(Integer id)
    {
        List<Lajk> lajkovi = lajkRepository.findAll();
        for(int i=0; i<lajkovi.size(); i++)
        {
            Lajk trenutniLajk = lajkovi.get(i);
            if(trenutniLajk.getUserid().equals(id))
            {
                lajkRepository.delete(trenutniLajk);
            }
        }
    }

}
