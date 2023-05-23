package com.example.Projekat.services;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.Oglas;
import com.example.Projekat.models.PrijavaNaOglas;
import com.example.Projekat.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {



    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private OglasService oglasService;
    @Autowired private PrijavaNaOglasService prijavaNaOglasService;

    @Autowired private LajkService lajkService;

    public List<Korisnik> getKorisnici(){
        return korisnikRepository.findAll();
    }


    public Korisnik findByUsername(String un) {
        return korisnikRepository.findByUsername(un);
    }

    public Korisnik save(Korisnik korisnik) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(korisnik.getPassword());
        korisnik.setPassword(encodedPassword);
        korisnikRepository.save(korisnik);
        return korisnik;

    }

    public void saveImage(MultipartFile file, Korisnik korisnik) throws IOException
    {
        Integer slika=1;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName==""){
            fileName="profilna.png";
            slika=0;
        }
        korisnikRepository.updateSlika(fileName,korisnik.getId());
        korisnik.setSlika(fileName);

        Korisnik sacuvani = save(korisnik);

        String uploadDir="./src/main/resources/static/assets/images/photos/"+sacuvani.getUsername();

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath))
        {
            Files.createDirectories(uploadPath);
        }
        try {
            if(slika==1) {
                InputStream inputStream = file.getInputStream();
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            else{
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(Path.of("./src/main/resources/static/assets/images/profilna.png"), filePath);
            }
        } catch (IOException e) {

            throw new IOException("Ne moze da se sacuva fajl: "+ fileName);

        }
    }

    public Optional <Korisnik> findById(int id){
        return korisnikRepository.findById(id);
    }

    public void delete(Integer id){
        korisnikRepository.deleteById(id);
    }


    //NE RADI KAD SE PONAVLJAJU I EMAIL I USERNAME
    //NE RADI KAD JE USERNAME NOV
    public String updateKorisnik(Integer id, String ime, String prezime, String username, String email, Korisnik k) throws IOException {

        String trenutnaSlika=k.getSlika();
        String poruka="";
        Integer promenjeni=0;
        int i=0;

        Integer ind=0;
        Integer ind1=0;
        for(int j=0;j<getKorisnici().size();j++)
        {
            Korisnik k1 = getKorisnici().get(j);
            if(k1.getUsername().equals(username)) {
                username=k.getUsername();
                ind = 1;
            }
            if(k1.getEmail().equals(email)){
                email=k.getEmail();
                ind1=1;
            }
            if(ind==1 && ind1==1) break;
        }

        if(ime=="") ime= k.getIme();
        else promenjeni++;

        if(prezime=="") prezime = k.getPrezime();
        else promenjeni++;

        if(email=="") email = k.getEmail();
        else promenjeni++;

        if(username=="") username = k.getUsername();
        else promenjeni++;

        if(ind==1){

            if(promenjeni>0 && ind1==1) {
                korisnikRepository.updateProfil(id,ime,prezime,email,username);
                return "Uneti username i email vec postoje!"+"\n"+"Ostali podaci su sacuvani!";
            }
            else if(promenjeni==0 && ind1==1){
                korisnikRepository.updateProfil(id,ime,prezime,email,username);
                return "Uneti username i email vec postoje!";
            }
            else if(promenjeni>0){
                if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]*.[a-zA-Z]{2,6}$")){

                    email=k.getEmail();
                    korisnikRepository.updateProfil(id,ime,prezime,email,username);
                    if(promenjeni>2)
                        return  "*Uneti username vec postoji!"+"\n" + " **Neispravan email." + "\n" +"Ostali podaci su sacuvani!";
                    else
                        return  "*Uneti username vec postoji!"+"\n" + " **Neispravan email.";
                }
                else
                {
                    korisnikRepository.updateProfil(id,ime,prezime,email,username);
                    return  "Uneti username vec postoji!Ostali podaci su sacuvani!";
                }
            }

//            if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]*.[a-zA-Z]{2,6}$")){
//                email=k.getEmail();
//                korisnikRepository.updateProfil(id,ime,prezime,email,username);
//                return "*Uneti username vec postoji! **Neispravan email";}
//            else{
                korisnikRepository.updateProfil(id,ime,prezime,email,username);
                return "Uneti username vec postoji!";
//            }

        }


        if(username!=k.getUsername()) {

            if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]*.[a-zA-Z]{2,6}$")){
                email=k.getEmail();
                username=k.getUsername();
                korisnikRepository.updateProfil(id,ime,prezime,email,username);
                if(promenjeni>2)
                    return "*Neispravan email"+"/n"+"Ostali podaci su sacuvani!";
                else return "Neispravan email.";
            }
            if(ind1==1){
                if(promenjeni>2){

                    username=k.getUsername();
                    korisnikRepository.updateProfil(id,ime,prezime,email,username);
                    return "Uneti email vec postoji!"+"/n"+"Ostali podaci su sacuvani!";}
                    else return  "Uneti email vec postoji!"; }

            System.out.println(username);
            String uploadDir="./src/main/resources/static/assets/images/photos/" + username;
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath))
            {
                Files.createDirectories(uploadPath);
                System.out.println("PRAVI SE");
            }

            try {

                Path filePath = uploadPath.resolve(trenutnaSlika);
                System.out.println(filePath);

                Files.copy(Path.of("./src/main/resources/static/assets/images/photos/"+k.getUsername()+'/'+trenutnaSlika), filePath,StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {

                throw new IOException("Ne moze da se sacuva fajl: "+ trenutnaSlika);

            }

            korisnikRepository.updateProfilSaSlikom(id,ime,prezime,email,username,trenutnaSlika);
            return "Uspesno ste promenili username!Prijavite se sa novim username-om!";
        }

        if(ind1==1){
            if(promenjeni>0){
                korisnikRepository.updateProfil(id,ime,prezime,email,username);
                return "Uneti email vec postoji!Ostali podaci su sacuvani!";}
            else
            {
                korisnikRepository.updateProfil(id,ime,prezime,email,username);
                return "Uneti email vec postoji!";}

        }

        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]*.[a-zA-Z]{2,6}$")){
            email=k.getEmail();
            korisnikRepository.updateProfil(id,ime,prezime,email,username);
            if(promenjeni<=1) return "Neispravan email";
            else return "*Neispravan email."+"\n" + "Ostali podaci su sacuvani!";
        }
        korisnikRepository.updateProfil(id,ime,prezime,email,username);
        return "Podaci su sacuvani!";
    }

    public void updateUloga(Integer id, Integer uid, Korisnik k) {

        List<Oglas> oglasi = oglasService.getOglasi();
        List<PrijavaNaOglas> prijave =prijavaNaOglasService.getPrijave();
        if (k.getUlogaid() == 2) {
            if (uid == 3) {
                for (Oglas oglas : oglasi) {
                    if (oglas.getKorisnikid() == id) {
                        lajkService.brisanjeLajkovaO(oglas.getId());
                        prijavaNaOglasService.brisanjePrijava(oglas.getId());
                        oglasService.delete(oglas.getId());
                    }
                }
            }
        }
        if(k.getUlogaid()==3)
        {
            if(uid==2){
                for (PrijavaNaOglas prijava:prijave) {
                        prijavaNaOglasService.brisanjePrijavaK(k.getId());
                }
            }
        }


        korisnikRepository.updateUserId(id, uid);

    }

    public void saveImage1(MultipartFile file, Korisnik korisnik) throws IOException {
        Integer slika=1;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName==""){
            System.out.println("nemaaaaaa");
            fileName="profilna.png";
            slika=0;
        }

        korisnikRepository.updateSlika(fileName,korisnik.getId());


        String uploadDir="./src/main/resources/static/assets/images/photos/"+korisnik.getUsername();

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath))
        {
            Files.createDirectories(uploadPath);
            System.out.println("PRAVI SE");
        }

        try {
            if(slika==1) {
                InputStream inputStream = file.getInputStream();
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); //dodala sam replace ako vec postoji ta putanja
            }
            else{
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(Path.of("./src/main/resources/static/assets/images/profilna.png"), filePath);
            }
        } catch (IOException e) {

            throw new IOException("Ne moze da se sacuva fajl: "+ fileName);

        }

    }


}

