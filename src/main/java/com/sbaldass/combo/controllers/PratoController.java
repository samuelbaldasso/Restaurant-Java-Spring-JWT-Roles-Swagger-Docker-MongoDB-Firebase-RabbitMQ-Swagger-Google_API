package com.sbaldass.combo.controllers;

import com.sbaldass.combo.services.PratoService;
import com.sbaldass.combo.dto.PratoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @PostMapping
    public PratoDTO criarPrato(@RequestBody PratoDTO pratoDTO) throws Exception {
        return pratoService.salvarPrato(pratoDTO);
    }

    @GetMapping
    public List<PratoDTO> listarPratos() throws Exception {
        return pratoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoDTO> buscarPratoPorId(@PathVariable Long id) {
        PratoDTO pratoDTO = pratoService.buscarPorId(id);
        if (pratoDTO != null) {
            return ResponseEntity.ok(pratoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public PratoDTO atualizarPrato(@PathVariable Long id, @RequestBody PratoDTO pratoDTO) throws Exception {
        return pratoService.atualizarPrato(id, pratoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrato(@PathVariable Long id) throws Exception {
        pratoService.deletarPrato(id);
        return ResponseEntity.ok().build();
    }
}
