package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.Restaurant;
import com.sbaldass.combo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restauranteService;

    @GetMapping
    public List<Restaurant> getAllRestaurantes() {
        return restauranteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestauranteById(@PathVariable String id) {
        Optional<Restaurant> restaurante = restauranteService.findById(id);
        return restaurante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Restaurant createRestaurante(@RequestBody Restaurant restaurante) {
        return restauranteService.save(restaurante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurante(@PathVariable String id, @RequestBody Restaurant restauranteDetails) {
        Optional<Restaurant> restaurante = restauranteService.findById(id);

        if (restaurante.isPresent()) {
            Restaurant updatedRestaurante = restaurante.get();
            updatedRestaurante.setName(restauranteDetails.getName());
            updatedRestaurante.setLocation(restauranteDetails.getLocation());
            updatedRestaurante.setDescription(restauranteDetails.getDescription());
            return ResponseEntity.ok(restauranteService.save(updatedRestaurante));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable String id) {
        if (restauranteService.findById(id).isPresent()) {
            restauranteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
