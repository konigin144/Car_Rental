package com.example.car_rental.Integration.Controllers;

import com.example.car_rental.entities.Client;
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
@Sql(statements = {"DELETE FROM Clients;",
        "INSERT INTO Clients (\"first_name\",\"last_name\") VALUES (\"Anna\", \"Nowak\");",
        "INSERT INTO Clients (\"first_name\",\"last_name\") VALUES (\"Adam\", \"Kowalski\");"})
public class ClientControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void getAllClients() throws Exception{
        mvc.perform(get("/client")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("Anna")))
                .andExpect(jsonPath("$[0].lastname", is("Nowak")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstname", is("Adam")))
                .andExpect(jsonPath("$[1].lastname", is("Kowalski")));
    }

    @Test
    public void getClientByIdExists() throws Exception {
        mvc.perform(get("/client/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Anna")))
                .andExpect(jsonPath("$.lastname", is("Nowak")));
    }

    @Test
    public void getClientByIdNotExist() throws Exception {
        mvc.perform(get("/client/3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addClient() throws Exception {
        Client client = new Client(1, "Anna", "Nowak");
        String request = om.writeValueAsString(client);

        mvc.perform(post("/client")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateClientExists() throws Exception {
        Client client = new Client(1, "Anna", "Nowak");
        String request = om.writeValueAsString(client);

        mvc.perform(put("/client/1")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateClientNotExist() throws Exception {
        mvc.perform(put("/client/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteClientExists() throws Exception {
        mvc.perform(delete("/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteClientNotExist() throws Exception {
        mvc.perform(delete("/client/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
