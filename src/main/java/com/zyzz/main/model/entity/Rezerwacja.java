package com.zyzz.main.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "rezerwacja")
@Data
public class Rezerwacja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private double koszt;

    @ManyToOne
    @JoinColumn(name = "wynajmujacy_id", nullable = false)
    @JsonIgnoreProperties({"id"})
    private Wynajmujacy wynajmujacy;

    @ManyToOne
    @JoinColumn(name = "najemca_id", nullable = false)
    @JsonIgnoreProperties({"id"})
    private Najemca najemca;

    @ManyToOne
    @JoinColumn(name = "obiekt_id", nullable = false)
    @JsonIgnoreProperties({"id"})
    private Obiekt obiekt;
}
