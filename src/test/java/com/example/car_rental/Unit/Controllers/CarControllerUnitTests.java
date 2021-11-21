package com.example.car_rental.Unit.Controllers;

import com.example.car_rental.repositories.*;
import com.example.car_rental.entities.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
public class CarControllerUnitTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private CarRepository carsRepository;

    @Test
    void getAllCars() throws Exception {
        Car car1 = new Car(1,"Toyota","Yaris", true);
        Car car2 = new Car(2,"Renault","Clio",false);

        doReturn(Lists.newArrayList(car1,car2)).when(carsRepository).findAll();

        mvc.perform(get("/car")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("Toyota")))
                .andExpect(jsonPath("$[0].model", is("Yaris")))
                .andExpect(jsonPath("$[0].available", is(true)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].brand", is("Renault")))
                .andExpect(jsonPath("$[1].model", is("Clio")))
                .andExpect(jsonPath("$[1].available", is(false)));
    }

    @Test
    void getCarByIdExists() throws Exception {
        Car car = new Car(1,"Toyota","Yaris", true);

        doReturn(Optional.of(car)).when(carsRepository).findById(1);

        mvc.perform(get("/car/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.brand", is("Toyota")))
                .andExpect(jsonPath("$.model", is("Yaris")))
                .andExpect(jsonPath("$.available", is(true)));
    }

    @Test
    void getCarByIdNotExists() throws Exception {
        when(carsRepository.existsById(1)).thenReturn(false);

        mvc.perform(get("/car/3")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addCar() throws Exception {
        Car car = new Car(1,"Toyota","Yaris", true);
        String request = om.writeValueAsString(car);

        doReturn(car).when(carsRepository).save(car);

        mvc.perform(post("/car")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCarExists() throws Exception {
        Car car1 = new Car(1,"Toyota","Yaris", true);
        Car car2 = new Car(1,"Renault","Clio",false);
        String request = om.writeValueAsString(car2);

       // doReturn(car1).when(carsRepository).save(car1);

        doReturn(Optional.of(car2)).when(carsRepository).findById(1);
        doReturn(car2).when(carsRepository).save(any());
        when(carsRepository.existsById(1)).thenReturn(true);

        mvc.perform(put("/car/1")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateCarNotExists() throws Exception {
        Car car = new Car(1,"Renault","Clio",false);
        String request = om.writeValueAsString(car);

        doReturn(Optional.of(car)).when(carsRepository).findById(1);
        doReturn(car).when(carsRepository).save(any());

        mvc.perform(put("/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteCarExists() throws Exception {
        Car car = new Car(1,"Toyota","Yaris", true);

        doReturn(car).when(carsRepository).save(car);
        when(carsRepository.existsById(1)).thenReturn(true);

        mvc.perform(delete("/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(carsRepository).deleteById(1);
    }
}
