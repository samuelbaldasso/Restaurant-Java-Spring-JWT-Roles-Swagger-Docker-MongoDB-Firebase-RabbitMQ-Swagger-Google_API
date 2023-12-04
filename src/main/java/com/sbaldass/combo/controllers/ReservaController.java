package com.sbaldass.combo.controllers;

import com.sbaldass.combo.dto.ReservaDTO;
import com.sbaldass.combo.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() throws Exception {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) throws Exception {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reserva) throws Exception {
        return ResponseEntity.ok(reservaService.save(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @RequestBody ReservaDTO reserva) throws Exception {
        return reservaService.findById(id)
                .map(existingReserva -> {
                    existingReserva.setStatus(reserva.getStatus());
                    existingReserva.setNumeroPessoas(reserva.getNumeroPessoas());
                    existingReserva.setDataHora(reserva.getDataHora());
                    try {
                        return ResponseEntity.ok(reservaService.save(existingReserva));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) throws Exception {
        reservaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
