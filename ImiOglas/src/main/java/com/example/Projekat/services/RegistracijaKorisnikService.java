package com.example.Projekat.services;

import com.example.Projekat.models.Korisnik;

public interface RegistracijaKorisnikService {
    boolean checkIfUserExist(final String username);
}
