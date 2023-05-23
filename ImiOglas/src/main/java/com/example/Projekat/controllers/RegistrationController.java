package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.repositories.KorisnikRepository;
import com.example.Projekat.services.KorisnikService;
import com.example.Projekat.services.RegistracijaKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class RegistrationController implements RegistracijaKorisnikService {

    @Autowired private KorisnikService korisnikService;
    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping(value="registracija")
        public RedirectView addNew(Korisnik korisnik, RedirectAttributes redir, @RequestParam("fileImage") MultipartFile file)
    {
        if(checkIfUserExist(korisnik.getUsername())){
            RedirectView redirectView = new RedirectView("/register",false);
            redir.addFlashAttribute("message","Korisnik sa tim username-om vec postoji!Prijavite se ili se registrujte sa drugim username-om!");
            return redirectView;
        }
        if(checkIfEmailExists(korisnik.getEmail())){
            RedirectView redirectView = new RedirectView("/register",false);
            redir.addFlashAttribute("messageEmail","Korisnik sa tim email-om vec postoji!Prijavite se ili se registrujte sa drugim email-om!");
            return redirectView;
        }
        try {
            korisnikService.saveImage(file,korisnik);
        } catch (IOException e) {
            e.printStackTrace();
        }

        korisnikService.save(korisnik);
        RedirectView redirectView = new RedirectView("/login",true);
        redir.addFlashAttribute("message","Uspešno ste se registrovali.Sada se možete prijaviti.");
        return redirectView;
    }

    @Autowired
    private KorisnikRepository korisnikRepository;

    public boolean checkIfUserExist(String username) {
        return korisnikRepository.findByUsername(username) !=null ? true : false;
    }

    public boolean checkIfEmailExists(String email) {
        return korisnikRepository.findByEmail(email) !=null ? true : false;
    }
}
