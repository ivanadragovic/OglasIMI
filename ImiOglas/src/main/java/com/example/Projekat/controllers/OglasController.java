package com.example.Projekat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OglasController {

    @GetMapping("/opis-posla")
    public String opisPosla(){
        return "listaKorisnika";
    }

}
