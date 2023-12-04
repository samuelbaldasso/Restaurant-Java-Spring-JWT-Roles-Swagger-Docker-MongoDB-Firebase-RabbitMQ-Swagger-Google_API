package com.sbaldass.combo.controllers;

import com.sbaldass.combo.dto.AvaliacaoDTO;
import com.sbaldass.combo.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> getAllAvaliacoes() {
        try {
            List<AvaliacaoDTO> avaliacoes = avaliacaoService.findAll();
            return ResponseEntity.ok(avaliacoes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> getAvaliacaoById(@PathVariable Long id) {
        try {
            return avaliacaoService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        try {
            AvaliacaoDTO savedAvaliacao = avaliacaoService.save(avaliacaoDTO);
            return ResponseEntity.ok(savedAvaliacao);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        try {
            return avaliacaoService.findById(id)
                    .map(existingAvaliacao -> {
                        avaliacaoDTO.setId(id);
                        AvaliacaoDTO updatedAvaliacao;
                        try {
                            updatedAvaliacao = avaliacaoService.save(avaliacaoDTO);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return ResponseEntity.ok(updatedAvaliacao);
                    })
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        try {
            avaliacaoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
