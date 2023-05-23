package com.example.Projekat.services;

import com.example.Projekat.models.Oglas;
import com.example.Projekat.models.PrijavaNaOglas;
import com.example.Projekat.repositories.PrijavaNaOglasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrijavaNaOglasService {

    @Autowired
    PrijavaNaOglasRepository prijavaNaOglasRepository;

    //vrati listu prijava
    public List<PrijavaNaOglas> getPrijave(){ return prijavaNaOglasRepository.findAll(); }

    //dodaj novu prijavu
    public void save(PrijavaNaOglas prijavaNaOglas){ prijavaNaOglasRepository.save(prijavaNaOglas); }

    //obrisi prijavu po id-u
    public void delete(Integer id){ prijavaNaOglasRepository.deleteById(id); }

    public void brisanjePrijava(Integer id)
    {
        //brisanje svih prijava na oglas koji treba da se obrise
        List<PrijavaNaOglas> prijave = prijavaNaOglasRepository.findAll();
        for(int i=0; i<prijave.size(); i++)
        {
            PrijavaNaOglas tekucaPrijava = prijave.get(i);
            if(tekucaPrijava.getOglasid().equals(id))
            {
                prijavaNaOglasRepository.delete(tekucaPrijava);
            }
        }
    }

    public void brisanjePrijavaK(Integer id)
    {
        //brisanje svih prijava na oglas korisnika kog brisemo
        List<PrijavaNaOglas> prijave = prijavaNaOglasRepository.findAll();
        for(int i=0; i<prijave.size(); i++)
        {
            PrijavaNaOglas tekucaPrijava = prijave.get(i);
            if(tekucaPrijava.getKorisnikid().equals(id))
            {
                prijavaNaOglasRepository.delete(tekucaPrijava);
            }
        }
    }
}