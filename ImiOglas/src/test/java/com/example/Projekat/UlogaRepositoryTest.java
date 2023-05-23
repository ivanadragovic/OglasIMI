package com.example.Projekat;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.Sektor;
import com.example.Projekat.models.Uloga;
import com.example.Projekat.repositories.KorisnikRepository;
import com.example.Projekat.repositories.SektorRepository;
import com.example.Projekat.repositories.UlogaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Rollback(false)
    public class UlogaRepositoryTest {
        @Autowired
        UlogaRepository ulogaRepository;
        @Autowired
        SektorRepository sektorRepository;
        @Autowired
        KorisnikRepository korisnikRepository;

        @Test
        public void testCreateRoles(){
            Uloga radnik = new Uloga(1,"Administrator");
            Uloga admin = new Uloga(2,"Poslodavac");
            Uloga poslodavac = new Uloga(3,"Radnik");
            Uloga neregistrovani = new Uloga(4,"Neregistrovani");

            ulogaRepository.saveAll(List.of(radnik,admin,poslodavac,neregistrovani));
            List<Uloga> ulogeList = ulogaRepository.findAll();
            assertThat(ulogeList.size()).isEqualTo(4);


        }

        @Test
        public void testCreateSektors(){


            Sektor  administracija= new Sektor(1,"Administracija");
            Sektor bezbednostIzaštita = new Sektor(2,"Bezbednost i zaštita");
            Sektor cuvanjeInegaDeceIodraslih = new Sektor(3,"Čuvanje i nega dece i odraslih");
            Sektor fizičkiPoslovi = new Sektor(4,"Fizički poslovi");
            Sektor gradjevina = new Sektor(5,"Gradjevina");
            Sektor higijena = new Sektor(6,"Higijena");
            Sektor it = new Sektor(7,"IT");
            Sektor kulinarstvo= new Sektor(8,"Kulinarstvo");
            Sektor majstori = new Sektor(9,"Majstori");
            Sektor marketingIpromocija = new Sektor(10,"Marketing i promocija");
            Sektor medicinaIfarmacija = new Sektor(11,"Medicina i farmacija");
            Sektor negaIlepota = new Sektor(12,"Nega i lepota");
            Sektor omladinskiIstudentskiPoslovi = new Sektor(13,"Omladinski i studentski poslovi");
            Sektor poljoprivreda = new Sektor(14,"Poljoprivreda");
            Sektor prevozIdostava = new Sektor(15,"Prevoz i dostava");
            Sektor proizvodnja = new Sektor(16,"Proizvodnja");
            Sektor skladištenjeIlogistika = new Sektor(17,"Skladištenje i logistika");
            Sektor trgovinaIprodaja = new Sektor(18,"Trgovina i prodaja");
            Sektor turizam = new Sektor(19,"Turizam");
            Sektor ugostiteljstvo = new Sektor(20,"Ugostiteljstvo");
            Sektor ostalo = new Sektor(21,"Ostalo");


            sektorRepository.saveAll(List.of(administracija,bezbednostIzaštita,cuvanjeInegaDeceIodraslih,fizičkiPoslovi,gradjevina,higijena,it,
                    kulinarstvo,majstori,marketingIpromocija,medicinaIfarmacija,negaIlepota,omladinskiIstudentskiPoslovi,poljoprivreda,
                    prevozIdostava,proizvodnja,skladištenjeIlogistika,trgovinaIprodaja,turizam,ugostiteljstvo,ostalo));
            List<Sektor> sektorList = sektorRepository.findAll();
            assertThat(sektorList.size()).isEqualTo(21);


        }


                @Test
                public void napraviAdmin(){

                    Korisnik korisnik = new Korisnik();
                    Korisnik poslodavac=new Korisnik();
                    Korisnik radnik = new Korisnik();

                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


                    korisnik.setIme("Natalija");
                    korisnik.setPrezime("Grujovic");
                    korisnik.setUlogaid(1);
                    korisnik.setEmail("nikonn.nikos@gmail.com");
                    korisnik.setPassword("nnnnnnnn");
                    String encodedPassword = encoder.encode(korisnik.getPassword());
                    korisnik.setPassword(encodedPassword);
                    korisnik.setUsername("natalija.grujovic");
                    korisnik.setSlika("Natalija.jpg");
                    korisnik.setId(1);

                    korisnikRepository.save(korisnik);

                    poslodavac.setIme("Bogdan");
                    poslodavac.setPrezime("Jovanovic");
                    poslodavac.setUlogaid(2);
                    poslodavac.setEmail("bocinjo@gmail.com");
                    poslodavac.setPassword("bbbbbbbb");
                    String encodedPassword1 = encoder.encode(poslodavac.getPassword());
                    poslodavac.setPassword(encodedPassword1);
                    poslodavac.setUsername("boca");
                    poslodavac.setSlika("Bogdan.jpg");
                    poslodavac.setId(2);
                    korisnikRepository.save(poslodavac);

                    radnik.setIme("Rijalda");
                    radnik.setPrezime("Bajraktarevic");
                    radnik.setUlogaid(3);
                    radnik.setEmail("rijalda@gmail.com");
                    radnik.setPassword("rrrrrrrr");
                    String encodedPassword2 = encoder.encode(radnik.getPassword());
                    radnik.setPassword(encodedPassword2);
                    radnik.setUsername("rijalda");
                    radnik.setSlika("Rijalda.jpg");
                    radnik.setId(3);
                    korisnikRepository.save(radnik);


                }

    }


