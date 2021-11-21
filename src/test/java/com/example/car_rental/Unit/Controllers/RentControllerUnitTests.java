package com.example.car_rental.Unit.Controllers;

import com.example.car_rental.repositories.*;
import com.example.car_rental.entities.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RentControllerUnitTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private RentRepository rentRepository;

    @Test
    void getAllRents() throws Exception {
        Date date = new java.util.Date();
        Rent rent1 = new Rent(1,12,13, date, date);
        Rent rent2 = new Rent(2, 22, 23, date, date);

        doReturn(Lists.newArrayList(rent1, rent2)).when(rentRepository).findAll();

        mvc.perform(get("/rent")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].car_id", is(12)))
                .andExpect(jsonPath("$[0].client_id", is(13)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].car_id", is(22)))
                .andExpect(jsonPath("$[1].client_id", is(23)));
    }


    @Test
    void getRentByIdExists() throws Exception {
        Date date = new java.util.Date();
        Rent rent = new Rent(1,12,13, date, date);

        doReturn(Optional.of(rent)).when(rentRepository).findById(1);

        mvc.perform(get("/rent/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.car_id", is(12)))
                .andExpect(jsonPath("$.client_id", is(13)));
    }

    @Test
    void getRentByIdNotExists() throws Exception {
        when(rentRepository.existsById(1)).thenReturn(false);

        mvc.perform(get("/rent/3")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateRentExists() throws Exception {
        Date date = new java.util.Date();
        Rent rent = new Rent(1,12,13, date, date);
        String request = om.writeValueAsString(rent);

        doReturn(Optional.of(rent)).when(rentRepository).findById(1);
        doReturn(rent).when(rentRepository).save(any());
        when(rentRepository.existsById(1)).thenReturn(true);

        mvc.perform(put("/rent/1")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateRentNotExists() throws Exception {
        Date date = new java.util.Date();
        Rent rent = new Rent(1,12,13, date, date);
        String request = om.writeValueAsString(rent);

        doReturn(Optional.of(rent)).when(rentRepository).findById(1);
        doReturn(rent).when(rentRepository).save(any());

        mvc.perform(put("/rent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteRentExists() throws Exception {
        Date date = new java.util.Date();
        Rent rent = new Rent(1,12,13, date, date);

        doReturn(rent).when(rentRepository).save(rent);
        when(rentRepository.existsById(1)).thenReturn(true);

        mvc.perform(delete("/rent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(rentRepository).deleteById(1);
    }
}
