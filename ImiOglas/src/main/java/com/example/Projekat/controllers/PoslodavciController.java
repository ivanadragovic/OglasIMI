package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PoslodavciController {


    @Autowired
   private KorisnikService korisnikService;

    @GetMapping("/poslodavci")
    public String home(Model model)
    {
        List<Korisnik> korisnikList;
        korisnikList = korisnikService.getKorisnici();

        model.addAttribute("korisnici", korisnikList);


        return "poslodavci";
    }
}
