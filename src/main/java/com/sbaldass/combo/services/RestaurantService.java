package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Restaurant;
import com.sbaldass.combo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restauranteRepository;

    public List<Restaurant> findAll() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurant> findById(String id) {
        return restauranteRepository.findById(id);
    }

    public Restaurant save(Restaurant restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public void deleteById(String id) {
        restauranteRepository.deleteById(id);
    }
}
