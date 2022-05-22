package com.zyzz.main.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "obiekt")
@Data
public class Obiekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nazwa;

    @Column(name = "cena_jednostkowa", nullable = false)
    private double cenaJednostkowa;

    @Column(name = "powierzchnia_m2", nullable = false)
    private String powierzchnia;

    private String opis;


}
