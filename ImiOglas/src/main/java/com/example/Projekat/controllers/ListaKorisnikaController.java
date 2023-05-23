package com.example.Projekat.controllers;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.Lajk;
import com.example.Projekat.models.Oglas;
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
public class ListaKorisnikaController {

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private OglasService oglasService;
    @Autowired
    private PrijavaNaOglasService prijavaNaOglasService;
    @Autowired
    private LajkService lajkService;

    @RequestMapping("/listaKorisnika")
    public String listaKorisnika(Model model, Principal principal)
    {
        String un =  principal.getName();
        model.addAttribute("korisnik", korisnikService.findByUsername(un));
        List<Korisnik> korisnikList;
        korisnikList = korisnikService.getKorisnici();

        model.addAttribute("korisnici", korisnikList);

        String kw =  principal.getName();
        model.addAttribute("oglas", oglasService.findByKeyword(kw));
        List<Oglas> oglasList;
        oglasList = oglasService.getOglasi();

        model.addAttribute("oglasi",oglasList);


        return "listaKorisnika";
    }

    @RequestMapping("/listaMojihOglasa")
    public String listaMojihOglasa(Model model, Principal principal)
    {

        String un =  principal.getName();
        model.addAttribute("k", korisnikService.findByUsername(un));
        List<Korisnik> korisnikList;
        korisnikList = korisnikService.getKorisnici();

        model.addAttribute("korisnici1", korisnikList);

        String kw =  principal.getName();
        model.addAttribute("o", oglasService.findByKeyword(kw));
        List<Oglas> oglasList1;
        oglasList1 = oglasService.getOglasi();

        model.addAttribute("oglasi1",oglasList1);


        return "listaMojihOglasa";
    }

    @RequestMapping("/deleteKorisnik/{id}")
    public String deleteKorisnik(@PathVariable(name="id") Integer id,Model model, Principal principal)
    {
        String kw =  principal.getName();
        model.addAttribute("oglas", oglasService.findByKeyword(kw));
        List<Oglas> oglasList1;
        oglasList1 = oglasService.getOglasi();

        model.addAttribute("oglasi",oglasList1);


        String un =  principal.getName();
        model.addAttribute("k", korisnikService.findByUsername(un));
        List<Korisnik> korisnikList;
        korisnikList = korisnikService.getKorisnici();

        model.addAttribute("korisnici", korisnikList);
        int ind=0;
        Integer oid=0;
        Integer trenutniBrLajkova=0;
        List <Lajk> lajkovi = lajkService.getLajkovi();
        for(Korisnik korisnik:korisnikList){
            if(korisnik.getId()==id)
            {
                if(korisnik.getUloga().getId()==1)
                {
                    System.out.println("Ne moze se obrisati admin!");
                    ind=1;
                }
                else if(korisnik.getUlogaid()==3)
                {

                    for (Lajk l : lajkovi) {
                        if (l.getUserid() == id) {
                            lajkService.delete(l);
                            oid = l.getOglas().getId();
                            trenutniBrLajkova = l.getOglas().getBrojLajkova();
                            trenutniBrLajkova--;
                            oglasService.updateLajk(trenutniBrLajkova, oid);
                        }
                    }
                 //   lajkService.brisanjeLajkovaK(id);
                    prijavaNaOglasService.brisanjePrijavaK(id);
                    korisnikService.delete(id);
                    ind=1;
                }
            }
        }
        if(ind==0) {
            for (Oglas oglas : oglasList1) {
                if (oglas.getKorisnikid() == id)
                {
                    lajkService.brisanjeLajkovaO(oglas.getId());
                    prijavaNaOglasService.brisanjePrijava(oglas.getId());
                    oglasService.delete(oglas.getId());
                }
            }
            lajkService.brisanjeLajkovaK(id);
            korisnikService.delete(id);
        }
        return "redirect:/listaKorisnika";
    }


    @RequestMapping("deleteSvojOglas/{id}")
    public String deleteSvojOglas(@PathVariable(name="id") Integer id)
    {
        lajkService.brisanjeLajkovaO(id);
        prijavaNaOglasService.brisanjePrijava(id);
        oglasService.delete(id);
        return "redirect:/listaMojihOglasa";
    }


    @RequestMapping("izmeniUlogu/{id}")
    public String izmeniUlogu(@PathVariable(name="id") Integer id,@RequestParam(name="uid",required = true) Integer uid)
    {
        Optional<Korisnik> result = korisnikService.findById(id);
        Korisnik korisnik = result.orElse(null);
        System.out.println(uid);
        System.out.println(id);
        korisnikService.updateUloga(id,uid,korisnik);

        System.out.println(korisnik.getUloga().getId());
        System.out.println(korisnik.getUlogaid());

        return "redirect:/listaKorisnika";
    }

}
