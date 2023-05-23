package com.example.Projekat;

import com.example.Projekat.models.Oglas;
import com.example.Projekat.repositories.OglasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class napraviOglaseAdmina {

    @Autowired
    OglasRepository oglasRepository;

    @Test
    public void napraviOglaseAdmina(){
        Oglas oglas= new Oglas();
        Oglas oglas1 = new Oglas();
        Oglas oglasPosl = new Oglas();

        oglas.setKorisnikid(1);
        oglas1.setKorisnikid(2);
        oglasPosl.setKorisnikid(2);

        oglas.setId(1);
        oglas1.setId(2);
        oglasPosl.setId(3);

        oglas.setSektorid(15);
        oglas1.setSektorid(10);
        oglasPosl.setSektorid(3);
        oglas.setRadOdKuce("NE");
        oglas1.setRadOdKuce("DA");
        oglasPosl.setRadOdKuce("NE");

        oglas.setPlata(50);
        oglas1.setPlata(100);
        oglasPosl.setPlata(70);

        oglas.setOpis("\"Food house No1\" iz Resnika potražuje vozača B kategorije, za dostavu hrane.  \n" +
                "\n" +
                "Dostava se vrši firminim automobilom, mahom na teritoriji Resnika, Sunčanog brega, Miljakovca 3, Rušnja, Pinosave, Belog potoka, Kijeva, sela Rakovica. .. Odlični krajevi za vožnju, bez stresa zbog gužvi i semafora.\n" +
                "\n" +
                "Kandidat treba da je iz Resnika ili bliže okoline.\n" +
                "\n" +
                "Radno vreme od 16 - 00h, 3 dana u nedelji. Petak i  subota redovno i još jedan dan (mahom četvrtak).\n" +
                "Plan rada poznat minimum 2 nedelje unapred.");

        oglas1.setOpis("Radite od kuce bez ulaganja!\n" +
                "Potrebne ozbiljne i odgovorne osobe zenskog pola, u pitanju je timski rad! Ostvarite finansijsku sigurnost za sebe i svoju porodicu. Zaboravite radno vreme od devet do pet, duge vožnje i kancelarije - ovo je sreća, u karijeri kod kuće!");
        oglasPosl.setOpis("Potrebne medicinske sestre i negovateljice za rad u staračkom domu u Železniku. Za više informacija pozvati na broj 0642461087.");

        oglas.setPosao("Dostava");
        oglas1.setPosao("Online promocija");
        oglasPosl.setPosao("Negovatelj");

        oglas.setRadnaPozicija("Dostavljač hrane");
        oglas1.setRadnaPozicija("Promoter");
        oglasPosl.setRadnaPozicija("Negovatelj u staračkom domu");

        oglas.setBrojLajkova(0);
        oglas1.setBrojLajkova(0);
        oglasPosl.setBrojLajkova(0);

        oglasRepository.save(oglas);
        oglasRepository.save(oglas1);
        oglasRepository.save(oglasPosl);

    }
}
