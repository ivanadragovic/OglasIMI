package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.Oglas;
import com.example.Projekat.models.PrijavaNaOglas;
import com.example.Projekat.services.KorisnikService;
import com.example.Projekat.services.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PrijavaNaOglasController {
    @Autowired
    private OglasService oglasService;
    @Autowired
    private KorisnikService korisnikService;
}