package com.zyzz.main.controller;

import com.zyzz.main.model.entity.dto.RezerwacjaGetDto;
import com.zyzz.main.model.entity.dto.RezerwacjaPostDto;
import com.zyzz.main.service.RezerwacjaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class RezerwacjaController {

    private final RezerwacjaServiceImpl rezerwacjaService;

    public RezerwacjaController(RezerwacjaServiceImpl rezerwacjaService) {
        this.rezerwacjaService = rezerwacjaService;
    }



    @GetMapping("/rezerwacje")
    public ResponseEntity<List<RezerwacjaGetDto>> listaRezerwacji() {

        List<RezerwacjaGetDto> list = rezerwacjaService.listaRezerwacji();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/rezerwacje", params ="najemca" )
    public ResponseEntity<List<RezerwacjaGetDto>> listaRezerwacjiDlaNajemcy(@RequestParam("najemca") String najemca) {

        List<RezerwacjaGetDto> list = rezerwacjaService.listaRezerwacjiDlaNajemcy(najemca);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/rezerwacje", params ="obiekt_id" )
    public ResponseEntity<List<RezerwacjaGetDto>> listaRezerwacjiDlaObiektu(@RequestParam("obiekt_id") int obiekt) {

        List<RezerwacjaGetDto> list = rezerwacjaService.listaRezerwacjiDlaObiektu(obiekt);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/rezerwacje")
    public ResponseEntity dodajRezerwacje(@RequestBody RezerwacjaPostDto rezerwacja) {

        rezerwacjaService.dodajRezerwacje(rezerwacja);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rezerwacje/{id}")
    public ResponseEntity zmienRezerwacje(@RequestBody RezerwacjaPostDto rezerwacja, @PathVariable("id") int id){

        rezerwacjaService.zmienRezerwacje(rezerwacja, id);
        return ResponseEntity.noContent().build();
    }

}