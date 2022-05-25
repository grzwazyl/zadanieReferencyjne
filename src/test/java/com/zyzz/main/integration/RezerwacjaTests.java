package com.zyzz.main.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyzz.main.model.entity.dto.RezerwacjaGetDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RezerwacjaTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void zwraca_liste_rezerwacji_dla_najemcy() throws Exception {

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/rezerwacje")
                .contentType(MediaType.APPLICATION_JSON)
                .param("najemca", "Mariusz Pudzianowski")
                ).andExpect(status().isOk()).andReturn();

        List<RezerwacjaGetDto> list = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<RezerwacjaGetDto>>() {});

        assertThat(list.stream()
                .allMatch(rezerwacjaGetDto -> rezerwacjaGetDto.getNajemca().equals("Mariusz Pudzianowski")));

    }

}
