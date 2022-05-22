package com.zyzz.main.model.entity.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class RezerwacjaGetDto {

    private LocalDate startDate;
    private LocalDate endDate;

    private double koszt;

    private String wynajmujacy;
    private String najemca;
    private String obiekt;
}
