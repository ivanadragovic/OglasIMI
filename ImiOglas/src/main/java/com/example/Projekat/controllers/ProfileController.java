package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private KorisnikService korisnikService;


    @RequestMapping(value = "/profile")
    public String profil(Model model, Principal principal)
    {

        String un =  principal.getName();
        model.addAttribute("korisnik", korisnikService.findByUsername(un));
        List<Korisnik> korisnikList;
        korisnikList = korisnikService.getKorisnici();

        model.addAttribute("korisnici", korisnikList);

        return "team";
    }

    @PostMapping(value="izmeniSliku/{id}")
    public RedirectView updateSlika(@RequestParam(name ="fileImage")MultipartFile file, RedirectAttributes redir,  @PathVariable Integer id ,Principal principal,Model model)
    {
        String p =  principal.getName();
        model.addAttribute("korisnik", korisnikService.findById(id));

        Optional<Korisnik> result = korisnikService.findById(id);
        Korisnik korisnik = result.orElse(null);
        List<Korisnik> korisnici;
        korisnici = korisnikService.getKorisnici();
        model.addAttribute("korisnici",korisnici);

        try {
            korisnikService.saveImage1(file,korisnik);
        } catch (IOException e) {
            e.printStackTrace();
        }


        RedirectView redirectView = new RedirectView("/profile",true);
     //   redir.addFlashAttribute("message","Uspešno ste se registrovali.Sada se možete prijaviti.");
        return redirectView;
    }
    @RequestMapping( value = "izmeniProfil/{id}")
    public RedirectView updateKorisnik(@RequestParam(name="ime", required = false) String ime, @RequestParam(name="prezime",required = false) String prezime, @RequestParam(name="username",required = false) String username, @RequestParam(name="email",required = false) String email, @PathVariable Integer id, Model model, Principal principal, RedirectAttributes redir) throws IOException {


        String p =  principal.getName();
        model.addAttribute("korisnik", korisnikService.findById(id));
        Optional<Korisnik> result = korisnikService.findById(id);
        Korisnik korisnik = result.orElse(null);

        List<Korisnik> korisnici;
        korisnici = korisnikService.getKorisnici();

        model.addAttribute("korisnici",korisnici);
        System.out.println("uso");
        System.out.println(ime + prezime + email + username);



        String poruka =  korisnikService.updateKorisnik(id,ime,prezime,username,email, korisnik);
        System.out.println(poruka);
        if(poruka.equals("Uspesno ste promenili username!Prijavite se sa novim username-om!"))
        {
            RedirectView redirectView = new RedirectView("/login",true);
            redir.addFlashAttribute("porukaUspesnaPromenaLogin",poruka);
            return redirectView;
        }

        RedirectView redirectView = new RedirectView("/profile",false);
        redir.addFlashAttribute("porukaPromenaProfila",poruka);

        System.out.println(poruka);
        return redirectView;
    }

//    @PostMapping(value="/dodajSliku")
//    public String sacuvaj(@RequestParam("file") MultipartFile file, Korisnik korisnik)
//    {
//        korisnikService.saveImage(file,korisnik);
//        return "redirect:/team.html";
//    }

}
