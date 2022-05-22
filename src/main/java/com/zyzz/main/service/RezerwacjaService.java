package com.zyzz.main.service;

import com.zyzz.main.model.entity.dto.RezerwacjaGetDto;
import com.zyzz.main.model.entity.dto.RezerwacjaPostDto;

import java.util.List;

public interface RezerwacjaService {

    List<RezerwacjaGetDto> listaRezerwacji();

    List<RezerwacjaGetDto> listaRezerwacjiDlaNajemcy(String najemca);

    List<RezerwacjaGetDto> listaRezerwacjiDlaObiektu(int obiekt);

    int dodajRezerwacje(RezerwacjaPostDto rezerwacja);

    void zmienRezerwacje(RezerwacjaPostDto rezerwacja, int id);
}
