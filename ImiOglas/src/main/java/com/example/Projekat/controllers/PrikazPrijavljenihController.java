package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.PrijavaNaOglas;
import com.example.Projekat.services.PrijavaNaOglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrikazPrijavljenihController {

    @Autowired
    private PrijavaNaOglasService prijavaNaOglasService;

    @GetMapping("/prikazPrijavljenih")
    public String prijavljeni(@RequestParam(name="idOglasa") Integer idOglasa, Model model)
    {
        List<PrijavaNaOglas> prijave = prijavaNaOglasService.getPrijave();
        List<PrijavaNaOglas> prijaveNaJedanOglas = new ArrayList<PrijavaNaOglas>();
        for(int i=0; i<prijave.size(); i++)
        {
            PrijavaNaOglas trenutnaPrijava = prijave.get(i);
            if(trenutnaPrijava!=null && trenutnaPrijava.getOglasid().equals(idOglasa))
            {
                prijaveNaJedanOglas.add(trenutnaPrijava);
            }
        }

        int ind=0;
        List<Korisnik> korisnici = new ArrayList<Korisnik>();
        for(int i=0; i<prijaveNaJedanOglas.size(); i++)
        {
            Korisnik korisnik = prijaveNaJedanOglas.get(i).getKorisnik();
            korisnici.add(korisnik);
            ind=1;
        }
        if(ind==1)
        {
            model.addAttribute("korisnici",korisnici);
        }

        return "prikazPrijavljenih";
    }

}