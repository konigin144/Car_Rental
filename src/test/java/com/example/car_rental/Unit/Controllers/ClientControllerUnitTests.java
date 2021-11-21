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
public class ClientControllerUnitTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private ClientRepository clientsRepository;

    @Test
    void getAllClients() throws Exception {
        // given
        Client client1 = new Client(1, "Anna", "Nowak");
        Client client2 = new Client(2, "Adam", "Kowalski");

        // when
        doReturn(Lists.newArrayList(client1, client2)).when(clientsRepository).findAll();

        // then
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
    void getClientByIdExists() throws Exception {
        // given
        Client client = new Client(1, "Anna", "Nowak");

        // when
        doReturn(Optional.of(client)).when(clientsRepository).findById(1);

        // then
        mvc.perform(get("/client/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Anna")))
                .andExpect(jsonPath("$.lastname", is("Nowak")));
    }

    @Test
    void getClientByIdNotExists() throws Exception {
        // when
        when(clientsRepository.existsById(1)).thenReturn(false);

        // then
        mvc.perform(get("/client/3")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addClient() throws Exception {
        // given
        Client client = new Client(1, "Anna", "Nowak");
        String request = om.writeValueAsString(client);

        // when
        doReturn(client).when(clientsRepository).save(client);

        // then
        mvc.perform(post("/client")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateClientExists() throws Exception {
        // given
        Client client = new Client(1, "Anna", "Nowak");
        String request = om.writeValueAsString(client);

        // when
        doReturn(Optional.of(client)).when(clientsRepository).findById(1);
        doReturn(client).when(clientsRepository).save(any());
        when(clientsRepository.existsById(1)).thenReturn(true);

        // then
        mvc.perform(put("/client/1")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateClientNotExists() throws Exception {
        // given
        Client client = new Client(1, "Anna", "Nowak");
        String request = om.writeValueAsString(client);

        // when
        doReturn(Optional.of(client)).when(clientsRepository).findById(1);
        doReturn(client).when(clientsRepository).save(any());

        // then
        mvc.perform(put("/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteClientExists() throws Exception {
        // given
        Client client = new Client(1, "Anna", "Nowak");

        // when
        doReturn(client).when(clientsRepository).save(client);
        when(clientsRepository.existsById(1)).thenReturn(true);

        // then
        mvc.perform(delete("/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientsRepository).deleteById(1);
    }
}
