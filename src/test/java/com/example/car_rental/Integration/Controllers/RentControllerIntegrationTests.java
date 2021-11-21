package com.example.car_rental.Integration.Controllers;

import com.example.car_rental.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
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
@Sql(statements = {"DELETE FROM Clients;",
        "DELETE FROM CARS;",
        "DELETE FROM RENTS",
        "INSERT INTO Cars (\"brand\",\"model\",\"available\") VALUES (\"Suzuki\", \"Swift\",true);",
        "INSERT INTO Cars (\"brand\",\"model\",\"available\") VALUES (\"Renault\", \"Clio\",false);",
        "INSERT INTO Cars (\"brand\",\"model\",\"available\") VALUES (\"Toyota\", \"Yaris\",true);",
        "INSERT INTO Clients (\"first_name\",\"last_name\") VALUES (\"Anna\", \"Nowak\");",
        "INSERT INTO Clients (\"first_name\",\"last_name\") VALUES (\"Adam\", \"Kowalski\");",
        "INSERT INTO RENTS (\"car_id\",\"client_id\",\"rent_date\",\"return_date\") VALUES (1, 2,1637419462166,1637419532177);",
        "INSERT INTO RENTS (\"car_id\",\"client_id\",\"rent_date\",\"return_date\") VALUES (2, 1,1637419629767,null);"})
public class RentControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void getAllRents() throws Exception{
        mvc.perform(get("/rent")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].car_id", is(1)))
                .andExpect(jsonPath("$[0].client_id", is(2)))
                .andExpect(jsonPath("$[0].rentdate", is("2021-11-20T14:44:22.166+00:00")))
                .andExpect(jsonPath("$[0].returndate", is("2021-11-20T14:45:32.177+00:00")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].car_id", is(2)))
                .andExpect(jsonPath("$[1].client_id", is(1)))
                .andExpect(jsonPath("$[1].rentdate", is("2021-11-20T14:47:09.767+00:00")))
                .andExpect(jsonPath("$[1].returndate", is(IsNull.nullValue())));
    }

    @Test
    public void getRentByIdExists() throws Exception {
        mvc.perform(get("/rent/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.car_id", is(1)))
                .andExpect(jsonPath("$.client_id", is(2)))
                .andExpect(jsonPath("$.rentdate", is("2021-11-20T14:44:22.166+00:00")))
                .andExpect(jsonPath("$.returndate", is("2021-11-20T14:45:32.177+00:00")));
    }

    @Test
    public void getRentByIdNotExist() throws Exception {
        mvc.perform(get("/rent/3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRent() throws Exception {
        Rent rent = new Rent(3,1);
        String request = om.writeValueAsString(rent);

        mvc.perform(post("/rent")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void addRentCarUnavailable() throws Exception {
        mvc.perform(post("/rent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void returnRent() throws Exception {
        mvc.perform(patch("/rent/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void returnRentAlreadyReturned() throws Exception {
        mvc.perform(patch("/rent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateRentExists() throws Exception {
        Rent rent = new Rent(3,1);
        String request = om.writeValueAsString(rent);

        mvc.perform(put("/rent/1")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCarNotExist() throws Exception {
        Rent rent = new Rent(3,1);
        String request = om.writeValueAsString(rent);

        mvc.perform(put("/rent/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteRentExists() throws Exception {
        mvc.perform(delete("/rent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRentNotExist() throws Exception {
        mvc.perform(delete("/rent/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
