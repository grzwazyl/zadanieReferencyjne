package com.zyzz.main.unit.service;

import com.zyzz.main.dao.NajemcaDao;
import com.zyzz.main.dao.ObiektDao;
import com.zyzz.main.dao.RezerwacjaDao;
import com.zyzz.main.dao.WynajmujacyDao;
import com.zyzz.main.exception.KolizjaRezerwacjiException;
import com.zyzz.main.exception.ZlaDataRezerwacjiException;
import com.zyzz.main.mapper.RezerwacjaMapper;
import com.zyzz.main.model.entity.Najemca;
import com.zyzz.main.model.entity.Obiekt;
import com.zyzz.main.model.entity.Rezerwacja;
import com.zyzz.main.model.entity.Wynajmujacy;
import com.zyzz.main.model.entity.dto.RezerwacjaGetDto;
import com.zyzz.main.model.entity.dto.RezerwacjaPostDto;
import com.zyzz.main.service.RezerwacjaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RezerwacjaServiceTests {

    @Mock
    RezerwacjaDao rezerwacjaDao;
    @Mock
    NajemcaDao najemcaDao;
    @Mock
    RezerwacjaMapper rezerwacjaMapper;
    @Mock
    ObiektDao obiektDao;
    @Mock
    WynajmujacyDao wynajmujacyDao;

    @InjectMocks
    RezerwacjaServiceImpl rezerwacjaService;

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    public void zwraca_liste_dto_z_rezerwacjami_dla_najemcy(){
        Najemca najemca = new Najemca();
        najemca.setId(1);
        najemca.setNazwa("testowyNajemca");

        //zwraca liste rezerwacji
        when(rezerwacjaDao.listaDlaNajemcy(najemca)).thenReturn(getList());

        //lista powinna zawierac tylko rezerwacji dla danego najemcy
        assertThat(rezerwacjaService.listaRezerwacjiDlaNajemcy(najemca.getNazwa())
                .stream()
                .allMatch(rezerwacjaGetDto -> rezerwacjaGetDto.getNajemca().equals("testowyNajemca")));

    }

    @Test
    public void rzuca_wyjatkiem_dla_niepoprawnego_najemcy() {
        Najemca najemca = new Najemca(69, "nieIstniejacyNajemca");

        assertThrows(RuntimeException.class, () -> rezerwacjaService.listaRezerwacjiDlaNajemcy(najemca.getNazwa()));
    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    public void zwraca_liste_dto_z_rezerwacjami_dla_obiektu(){
        Obiekt obiekt = new Obiekt(1, "obiektTestowy", 150, 56, "opis");

        //zwaraca liste rezerwacji
        when(rezerwacjaDao.listaDlaObiektu(obiekt)).thenReturn(getList());

        //lista powinna zawierac rezerwacji tylko dla danego obiektu
        assertThat(rezerwacjaService.listaRezerwacjiDlaObiektu(obiekt.getId())
                .stream()
                .allMatch(rezerwacjaGetDto -> rezerwacjaGetDto.getObiekt().equals(obiekt.getNazwa())));

    }

    @Test
    public void rzuca_wyjatkiem_dla_niepoprawnego_obiektu() {
        int niepoprawneId = 420;

        assertThrows(RuntimeException.class, () -> rezerwacjaService.listaRezerwacjiDlaObiektu(niepoprawneId));
    }

    @Test
    public void rzuca_wyjatkiem_dla_niepoprawnych_dat() {
        LocalDate start = LocalDate.of(2022, 4, 12);
        LocalDate end = LocalDate.of(2022, 6, 20);
        RezerwacjaPostDto dto = new RezerwacjaPostDto(end ,start, 1, 1, 1);

        when(rezerwacjaDao.czyJestKolizja(start, end , dto.getObiektId())).thenReturn(true);

        assertThrows(ZlaDataRezerwacjiException.class, () -> rezerwacjaService.dodajRezerwacje(dto));

        dto.setStartDate(start);
        dto.setEndDate(end);
        assertThrows(KolizjaRezerwacjiException.class, () -> rezerwacjaService.dodajRezerwacje(dto));

    }



    //Lista rezerwacji
    public List<Rezerwacja> getList(){
        Najemca testowyNajemca = new Najemca(1, "testowyNajemca");
        Obiekt testowyObjekt = new Obiekt(1, "obiektTestowy", 120, 56, "opis");

        Wynajmujacy w = new Wynajmujacy(1, "wynajm1");
        Wynajmujacy w2 = new Wynajmujacy(2, "wynajm2");
        Wynajmujacy w3 = new Wynajmujacy(3, "wynajm3");


        Najemca n2 = new Najemca(2, "najemca2");
        Najemca n3 = new Najemca(3, "najemca3");


        Obiekt o2 = new Obiekt(2, "obiekt2", 140, 52, "opis2");
        Obiekt o3 = new Obiekt(3, "obiekt3", 180, 60, "opis3");

        List<Rezerwacja> list = new ArrayList<>();
        list.add(new Rezerwacja(1 , LocalDate.of(2022, 4, 29), LocalDate.of(2022, 5, 06), 4000,w, testowyNajemca, testowyObjekt));
        list.add(new Rezerwacja(2 , LocalDate.of(2022, 6, 29), LocalDate.of(2022, 7, 06), 4000,w2, n2, o2));
        list.add(new Rezerwacja(3 , LocalDate.of(2022, 8, 29), LocalDate.of(2022, 9, 06), 4000,w3, testowyNajemca, o3));
        list.add(new Rezerwacja(4 , LocalDate.of(2021, 3, 29), LocalDate.of(2022, 4, 06), 4000,w, n2, testowyObjekt));
        list.add(new Rezerwacja(5 , LocalDate.of(2023, 5, 29), LocalDate.of(2022, 6, 06), 4000,w3, testowyNajemca, o2));
        list.add(new Rezerwacja(6 , LocalDate.of(2024, 7, 29), LocalDate.of(2022, 8, 06), 4000,w2, n3, testowyObjekt));

        return list;
    }


}
