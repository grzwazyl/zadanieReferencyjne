package com.zyzz.main.mapper;

import com.zyzz.main.model.entity.Rezerwacja;
import com.zyzz.main.model.entity.dto.RezerwacjaGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RezerwacjaMapper {

    @Mappings({
            @Mapping(target = "wynajmujacy", source = "wynajmujacy.nazwa"),
            @Mapping(target = "najemca", source = "najemca.nazwa"),
            @Mapping(target = "obiekt", source = "obiekt.nazwa")
    })
    RezerwacjaGetDto rezerwacjaToRezerwacjaGetDto(Rezerwacja rezerwacja);
}
