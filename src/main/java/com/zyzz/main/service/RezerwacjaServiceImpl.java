package com.zyzz.main.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RezerwacjaServiceImpl implements RezerwacjaService {

    private final RezerwacjaDao rezerwacjaDao;
    private final NajemcaDao najemcaDao;
    private final RezerwacjaMapper rezerwacjaMapper;
    private final ObiektDao obiektDao;
    private final WynajmujacyDao wynajmujacyDao;

    public RezerwacjaServiceImpl(RezerwacjaDao rezerwacjaDao, NajemcaDao najemcaDao, RezerwacjaMapper rezerwacjaMapper, ObiektDao obiektDao, WynajmujacyDao wynajmujacyDao) {
        this.rezerwacjaDao = rezerwacjaDao;
        this.najemcaDao = najemcaDao;
        this.rezerwacjaMapper = rezerwacjaMapper;
        this.obiektDao = obiektDao;
        this.wynajmujacyDao = wynajmujacyDao;
    }

    @Override
    public List<RezerwacjaGetDto> listaRezerwacji() {

        List<RezerwacjaGetDto> list = rezerwacjaDao.findAll().stream()
                .map(rezerwacja -> rezerwacjaMapper.rezerwacjaToRezerwacjaGetDto(rezerwacja))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<RezerwacjaGetDto> listaRezerwacjiDlaNajemcy(String nazwa) {
        Najemca najemca = najemcaDao.findByName(nazwa);

        List<RezerwacjaGetDto> list = rezerwacjaDao.listaDlaNajemcy(najemca).stream()
                .map(rezerwacja -> rezerwacjaMapper.rezerwacjaToRezerwacjaGetDto(rezerwacja))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<RezerwacjaGetDto> listaRezerwacjiDlaObiektu(int id) {
        Obiekt obiekt = obiektDao.getById(id);
        System.out.println(obiekt);

        List<RezerwacjaGetDto> list = rezerwacjaDao.listaDlaObiektu(obiekt).stream()
                .map(rezerwacja -> rezerwacjaMapper.rezerwacjaToRezerwacjaGetDto(rezerwacja))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public int dodajRezerwacje(RezerwacjaPostDto postDto) {

        LocalDate start = postDto.getStartDate();
        LocalDate end = postDto.getEndDate();

        if (datyChronologicznie(start, end)) {
            throw new ZlaDataRezerwacjiException("Koncowa data nie moze byc wczesniej niz poczatkowa");
        }

        if (jestKolizja(start, end, postDto.getObiektId())) {
            throw new KolizjaRezerwacjiException("Rezerwacja w tym czasie nie jest możliwa");
        }

        rezerwacjaDao.save(mapToRezerwacja(postDto));

        return 1;
    }

    @Override
    public void zmienRezerwacje(RezerwacjaPostDto postDto, int id) {

        LocalDate start = postDto.getStartDate();
        LocalDate end = postDto.getEndDate();

        if (datyChronologicznie(start, end)) {
            throw new ZlaDataRezerwacjiException("Koncowa data nie moze byc wczesniej niz poczatkowa");
        }

        if (jestKolizjaDlaRezerwacji(start, end, postDto.getObiektId(), id)) {
            throw new KolizjaRezerwacjiException("Rezerwacja w tym czasie nie jest możliwa");
        }

        Rezerwacja rezerwacja = mapToRezerwacja(postDto);
        rezerwacja.setId(id);

        rezerwacjaDao.save(rezerwacja);

    }

    private boolean datyChronologicznie(LocalDate start, LocalDate end) {
        return start.isAfter(end);
    }



    private boolean jestKolizja(LocalDate start, LocalDate end, int id) {
        return rezerwacjaDao.czyJestKolizja(start, end, id);
    }

    private boolean jestKolizjaDlaRezerwacji(LocalDate start, LocalDate end, int obiektId, int id) {
        return rezerwacjaDao.czyJestKolizjaDlaRezerwacji(start, end, obiektId, id);
    }

    private Rezerwacja mapToRezerwacja(RezerwacjaPostDto dto) {
        Wynajmujacy wynajmujacy = wynajmujacyDao.getById(dto.getWynajmujacyId());
        Najemca najemca = najemcaDao.getById(dto.getNajemcaId());
        Obiekt obiekt = obiektDao.getById(dto.getObiektId());

        LocalDate start = dto.getStartDate();
        LocalDate end = dto.getEndDate();

        double koszt = obliczKoszt(start, end, obiekt.getCenaJednostkowa());

        Rezerwacja nowaRezerwacja = new Rezerwacja();
        nowaRezerwacja.setStartDate(start);
        nowaRezerwacja.setEndDate(end);
        nowaRezerwacja.setKoszt(koszt);
        nowaRezerwacja.setWynajmujacy(wynajmujacy);
        nowaRezerwacja.setNajemca(najemca);
        nowaRezerwacja.setObiekt(obiekt);

        return nowaRezerwacja;

    }

    private double obliczKoszt(LocalDate startDate, LocalDate endDate, double cenaJednostkowa) {
        return ChronoUnit.DAYS.between(startDate, endDate) * cenaJednostkowa;
    }


}
