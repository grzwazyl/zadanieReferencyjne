package com.zyzz.main.model.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class RezerwacjaPostDto {

    @NotNull
    @JsonProperty("start_date")
    private LocalDate startDate;

    @NotNull
    @JsonProperty("end_date")
    private LocalDate endDate;

    @NotNull
    @JsonProperty("wynajmujacy_id")
    private int wynajmujacyId;

    @NotNull
    @JsonProperty("najemca_id")
    private int najemcaId;

    @NotNull
    @JsonProperty("obiekt_id")
    private int obiektId;
}
