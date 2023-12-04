package com.sbaldass.combo.controllers;

import com.sbaldass.combo.dto.MesaDTO;
import com.sbaldass.combo.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<MesaDTO>> getAllMesas() throws Exception {
        return ResponseEntity.ok(mesaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> getMesaById(@PathVariable Long id) throws Exception {
        return mesaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MesaDTO> createMesa(@RequestBody MesaDTO mesa) throws Exception {
        return ResponseEntity.ok(mesaService.save(mesa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaDTO> updateMesa(@PathVariable Long id, @RequestBody MesaDTO mesa) throws Exception {
        return mesaService.findById(id)
                .map(existingMesa -> {
                    existingMesa.setNumero(mesa.getNumero());
                    existingMesa.setStatus(mesa.getStatus());
                    existingMesa.setCapacidade(mesa.getCapacidade());
                    try {
                        return ResponseEntity.ok(mesaService.save(existingMesa));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable Long id) throws Exception {
        mesaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
