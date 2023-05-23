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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JobsController {

    public Integer idOglasa1;

    @Autowired
    private OglasService oglasService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private PrijavaNaOglasService prijavaNaOglasService;
    @Autowired
    private LajkService lajkService;

    @GetMapping("/jobs")
    public String jobs1(Model model,String keyword,Principal principal)
    {
        if(principal!=null){
            String kw=principal.getName();
            model.addAttribute("korisnik", korisnikService.findByUsername(kw));}
        List<Oglas> oglasList;
        oglasList = oglasService.getOglasi();

        if(keyword!=null){
            System.out.println(keyword);
            model.addAttribute("oglasi",oglasService.findByKeyword(keyword));
        }
        else{
            model.addAttribute("oglasi", oglasList);}

        List<Lajk> lajkovi = lajkService.getLajkovi();
        model.addAttribute("lajkovi",lajkovi);

        return "jobs";
    }

    @PostMapping("/jobs/addNew")
    public String dodajNoviOglas(Oglas oglas, Principal principal, Model model){

        Korisnik korisnik;

        String un = principal.getName();
        korisnik = korisnikService.findByUsername(un);
        oglas.setKorisnikid(korisnik.getId());
        oglasService.save(oglas);

        return "redirect:/jobs";

    }

    @RequestMapping("/jobs")
    public String jobs(Model model, Principal principal)
    {

        String kw =  principal.getName();
        model.addAttribute("oglas", oglasService.findByKeyword(kw));

        List<Oglas> oglasList1;
        oglasList1 = oglasService.getOglasi();

        model.addAttribute("oglasi",oglasList1);

        return "listaMojihOglasa";
    }


    @RequestMapping("/jobs/prijavaNaOglas/{idOglasa}")
    public String dodajNovuPrijavu(@PathVariable(name="idOglasa") Integer idOglasa, Principal principal, RedirectAttributes redir)
    {
        Integer ind=0;
        Korisnik korisnik;
        String un = principal.getName();
        korisnik = korisnikService.findByUsername(un);
        List<PrijavaNaOglas> prijave = prijavaNaOglasService.getPrijave();

        for(PrijavaNaOglas p : prijave)
        {
            System.out.println("uso");
            if(p.getOglasid()==idOglasa && p.getKorisnikid()== korisnik.getId())
            {
                System.out.println("Vec postoi ista prijava");
                redir.addFlashAttribute("prijavaVecPostoji","Vec ste prijavljeni na taj oglas.");
                ind=1;
                break;
            }
        }

        if(ind==0) {
            PrijavaNaOglas prijavaNaOglas = new PrijavaNaOglas();
//            RedirectView redirectView = new RedirectView("/jobs", false);


            prijavaNaOglas.setKorisnikid(korisnik.getId());

            prijavaNaOglas.setOglasid(idOglasa);

            prijavaNaOglasService.save(prijavaNaOglas);
            idOglasa1 = idOglasa;
            return "redirect:/CVstrana";
        }
        return"redirect:/jobs";

    }
    @RequestMapping("/jobs/prijavaNaOglass/{idOglasa}")
    public String dodajNovuPrijavu2(@PathVariable(name="idOglasa") Integer idOglasa, Principal principal, RedirectAttributes redir)
    {
        Integer ind=0;
        Korisnik korisnik;
        String un = principal.getName();
        korisnik = korisnikService.findByUsername(un);
        List<PrijavaNaOglas> prijave = prijavaNaOglasService.getPrijave();

        for(PrijavaNaOglas p : prijave)
        {
            System.out.println("uso");
            if(p.getOglasid()==idOglasa && p.getKorisnikid()== korisnik.getId())
            {
                System.out.println("Vec postoi ista prijava");
                redir.addFlashAttribute("prijavaVecPostoji","Vec ste prijavljeni na taj oglas.");
                ind=1;
                break;
            }
        }

        if(ind==0) {
            PrijavaNaOglas prijavaNaOglas = new PrijavaNaOglas();
//            RedirectView redirectView = new RedirectView("/jobs", false);


            prijavaNaOglas.setKorisnikid(korisnik.getId());

            prijavaNaOglas.setOglasid(idOglasa);

            prijavaNaOglasService.save(prijavaNaOglas);
            idOglasa1 = idOglasa;
            return "redirect:/CVstrana";
        }
        return"redirect:/lajkovi";

    }

    @GetMapping("https://formsubmit.co/form/submission")
    public String home1()
    {
        return "jobs";
    }


    @RequestMapping(value = "/CVstrana")
    public String CV(Model model, Principal principal)
    {
        if(principal!=null){
            String kw=principal.getName();
            model.addAttribute("korisnik", korisnikService.findByUsername(kw));}
        model.addAttribute("oglas",oglasService.findOglasById(idOglasa1));

        List<Korisnik> korisnikList;
        korisnikList = korisnikService.getKorisnici();
        model.addAttribute("korisnici", korisnikList);

        List<Oglas> oglasList;
        oglasList = oglasService.getOglasi();
        model.addAttribute("oglasi", oglasList);

        return "CVstrana";

    }
    @RequestMapping("/jobs/apliciraniposlovi")
    public String Nova( Principal principal, Model model)
    {

        Korisnik korisnik;
        String un = principal.getName();
        korisnik = korisnikService.findByUsername(un);

        PrijavaNaOglas prijavaNaOglas = new PrijavaNaOglas();
        if (principal != null) {
            String kw = principal.getName();
            model.addAttribute("korisnik", korisnikService.findByUsername(kw));
        }

        prijavaNaOglas.setKorisnikid(korisnik.getId());

        List<Oglas> oglasList;
        oglasList = oglasService.getOglasi();
        model.addAttribute("oglasi", oglasList);



        return "redirect:/aplicirano";
    }

        @RequestMapping(value = "/aplicirano")
        public String Novaaa(Model model, Principal principal)
        {
            if(principal!=null){
                String kw=principal.getName();
                model.addAttribute("korisnik", korisnikService.findByUsername(kw));

                Korisnik k=korisnikService.findByUsername(kw);
                Integer idk=k.getId();

                List<PrijavaNaOglas> prijave = prijavaNaOglasService.getPrijave();

                List<Oglas> oglasList;
                oglasList = oglasService.getOglasi();
                model.addAttribute("oglasi", oglasList);
                List<Integer> idsOglas = new ArrayList<>();
                List<Integer> oglasListid=new ArrayList<>();
                for(int i=0;i<oglasList.size();i++)
                {
                    oglasListid.add(oglasList.get(i).getId());
                }
                model.addAttribute("svioglasi",oglasListid);

                for (int i = 0; i < prijave.size(); i++) {
                    if(prijave.get(i).getKorisnikid()==idk)
                        idsOglas.add(prijave.get(i).getOglasid());

                }
                model.addAttribute("oglasid", idsOglas);}
            return "aplicirano";

        }


    @RequestMapping("/delete/{id}")
    public String deleteOglas(@PathVariable(name="id") Integer id,Model model, Principal principal)
    {
        String un =  principal.getName();
        model.addAttribute("korisnik", korisnikService.findByUsername(un));
        List<Korisnik> korisnikList;
        korisnikList = korisnikService.getKorisnici();

        lajkService.brisanjeLajkovaO(id);
        prijavaNaOglasService.brisanjePrijava(id);
        model.addAttribute("korisnici", korisnikList);
        oglasService.delete(id);
        return "redirect:/jobs";
    }
}
