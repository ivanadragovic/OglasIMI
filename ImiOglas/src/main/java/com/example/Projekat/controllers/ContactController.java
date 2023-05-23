package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    KorisnikService korisnikService;
    @GetMapping("/contact")
    public String contact(Model model, Principal principal){
        if(principal!=null){
        String k = principal.getName();
        model.addAttribute("korisnik",korisnikService.findByUsername(k));}
        Optional<Korisnik> result = korisnikService.findById(1);
        Korisnik admin = result.orElse(null);

        model.addAttribute("admin",admin);
        return "contact";
    }
}
