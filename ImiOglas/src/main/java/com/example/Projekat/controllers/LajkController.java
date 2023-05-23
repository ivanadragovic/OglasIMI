package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.Lajk;
import com.example.Projekat.models.Oglas;
import com.example.Projekat.models.PrijavaNaOglas;
import com.example.Projekat.services.KorisnikService;
import com.example.Projekat.services.LajkService;
import com.example.Projekat.services.OglasService;
import com.example.Projekat.services.PrijavaNaOglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class LajkController {

    @Autowired
    KorisnikService korisnikService;
    @Autowired
    LajkService lajkService;
    @Autowired
    PrijavaNaOglasService prijavaNaOglasService;
    @Autowired private OglasService oglasService;

    @GetMapping("/lajkovi")
    public String home(Model model, Principal principal)
    {
        if(principal!=null){
            String kw=principal.getName();
            model.addAttribute("k", korisnikService.findByUsername(kw));}

        List<Lajk> lajkList;
        lajkList = lajkService.getLajkovi();

        model.addAttribute("lajkovi",lajkList);

        return "lajkovi";
    }

    @RequestMapping("dodajLajk/{id}")
    public String izmeniUlogu(@PathVariable(name="id") Integer id,Principal principal)
    {

        //id je id oglasa!
        Integer ind=0;
        String un =  principal.getName();
        Korisnik k =  korisnikService.findByUsername(un);
        //k je korisnik koji lajkuje!

//        Optional<Lajk> result = lajkService.findLajkById(id);
//        Lajk lajk = result.orElse(null);

        Lajk lajk = new Lajk(k.getId(),id);

        List <Lajk> lajkovi = lajkService.getLajkovi();

       Oglas trenutni = oglasService.findOglasById(id);
       Integer trenutniBrLajkova = trenutni.getBrojLajkova();

        if(lajkovi!=null) {
            for (Lajk l : lajkovi) {
                if (l.getOglasid() == id && l.getKorisnik().getId()==k.getId()) {
                    System.out.println("Vec je lajkovano--->dislike");
                    lajkService.delete(l);
                    trenutniBrLajkova--;
                    oglasService.updateLajk(trenutniBrLajkova,id);
                    ind = 1;
                    break;
                }
            }
        }
        if(ind==0) {
            lajkService.save(lajk);
            trenutniBrLajkova++;
            oglasService.updateLajk(trenutniBrLajkova,id);
        }


        return "redirect:/jobs";
    }

    @RequestMapping("deleteLajk/{id}")
    public String deleteSvojOglas(@PathVariable(name="id") Integer id)
    {
        //id je id lajka
        Integer oid=0;
        Integer trenutniBrLajkova=0;
        List <Lajk> lajkovi = lajkService.getLajkovi();
        if(lajkovi!=null) {
            for (Lajk l : lajkovi) {
                if (l.getId() == id) {
                    lajkService.delete(l);
                     oid= l.getOglas().getId();
                    trenutniBrLajkova=l.getOglas().getBrojLajkova();
                    trenutniBrLajkova--;
                    oglasService.updateLajk(trenutniBrLajkova,oid);
                    break;
                }
            }
        }
        return "redirect:/lajkovi";
    }

}
