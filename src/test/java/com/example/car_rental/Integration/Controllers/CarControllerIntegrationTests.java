package com.example.car_rental.Integration.Controllers;

import com.example.car_rental.entities.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "application_test.properties")
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(statements = {"DELETE FROM Cars;",
        "INSERT INTO Cars (\"brand\",\"model\",\"available\") VALUES (\"Suzuki\", \"Swift\",true);",
        "INSERT INTO Cars (\"brand\",\"model\",\"available\") VALUES (\"Renault\", \"Clio\",false);"})
public class CarControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void getAllCars() throws Exception{
        mvc.perform(get("/car")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("Suzuki")))
                .andExpect(jsonPath("$[0].model", is("Swift")))
                .andExpect(jsonPath("$[0].available", is(true)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].brand", is("Renault")))
                .andExpect(jsonPath("$[1].model", is("Clio")))
                .andExpect(jsonPath("$[1].available", is(false)));
    }

    @Test
    public void getCarByIdExists() throws Exception {
        mvc.perform(get("/car/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.brand", is("Suzuki")))
                .andExpect(jsonPath("$.model", is("Swift")))
                .andExpect(jsonPath("$.available", is(true)));
    }

    @Test
    public void getCarByIdNotExist() throws Exception {
        mvc.perform(get("/car/3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCar() throws Exception {
        Car car = new Car("Toyota","Yaris", true);
        String request = om.writeValueAsString(car);

        mvc.perform(post("/car")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCarExists() throws Exception {
        Car car = new Car("Toyota","Corolla", true);
        String request = om.writeValueAsString(car);

        mvc.perform(put("/car/1")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCarNotExist() throws Exception {
        mvc.perform(put("/car/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCarExists() throws Exception {
        mvc.perform(delete("/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCarNotExist() throws Exception {
        mvc.perform(delete("/car/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
