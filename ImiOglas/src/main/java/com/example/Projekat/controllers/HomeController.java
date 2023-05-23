package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.Oglas;
import com.example.Projekat.services.KorisnikService;
import com.example.Projekat.services.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    KorisnikService korisnikService;
    @Autowired
    OglasService oglasService;

    @GetMapping("/")
    public String home(Model model, Principal principal)
    {
        if(principal!=null){
            String kw=principal.getName();
            model.addAttribute("korisnik", korisnikService.findByUsername(kw));}
        return "index";
    }


    @GetMapping("/index")
    public String home1(Model model, Principal principal)
    {
        if(principal!=null){
            String kw=principal.getName();
            model.addAttribute("korisnik", korisnikService.findByUsername(kw));}

        return "index";
    }



}
