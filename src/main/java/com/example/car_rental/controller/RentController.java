package com.example.car_rental.controller;

import com.example.car_rental.entities.Rent;
import com.example.car_rental.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/rent", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RentController {
    @Autowired
    private RentService rentService;

    @GetMapping(value = "")
    public List<Rent> getAllRents() {
        return rentService.getAllRents();
    }

    @GetMapping(value = "/{id}")
    public Rent getRentById(@PathVariable Integer id) {
        Rent rent = rentService.getRentById(id);
        if (rent != null) {
            return rent;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    @PostMapping(value = "")
    public void addRent(@RequestBody Rent Rent) {
        if (rentService.addRent(Rent)) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Rent created");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    @PutMapping(value = "/{id}")
    public void putRent(@RequestBody Rent Rent, @PathVariable Integer id) {
        if (rentService.updateRent(id, Rent)) {
            throw new ResponseStatusException(HttpStatus.OK, "Rent updated");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    @PatchMapping(value = "/{id}")
    public void returnRent(@PathVariable Integer id) {
        if (rentService.returnRent(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "Rent updated");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRent(@PathVariable Integer id) {
        if (rentService.deleteRent(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "Rent deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }
}
