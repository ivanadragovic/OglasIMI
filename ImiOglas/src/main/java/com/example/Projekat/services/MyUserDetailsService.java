package com.example.Projekat.services;

import com.example.Projekat.models.Korisnik;
import com.example.Projekat.models.UserPrincipal;
import com.example.Projekat.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired KorisnikRepository korisnikRepository;

    //uzimamo korisnika i trazimo ga po username-u
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByUsername(username);

        if(korisnik==null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new UserPrincipal(korisnik);
    }
}
