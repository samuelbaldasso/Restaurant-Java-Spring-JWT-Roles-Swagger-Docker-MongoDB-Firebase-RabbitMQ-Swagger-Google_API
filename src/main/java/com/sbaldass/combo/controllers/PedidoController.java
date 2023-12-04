package com.sbaldass.combo.controllers;

import com.sbaldass.combo.services.PedidoService;
import com.sbaldass.combo.domain.Pedido;
import com.sbaldass.combo.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) throws Exception {
        Pedido pedido = pedidoService.convertToEntity(pedidoDTO);
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        PedidoDTO novoPedidoDTO = pedidoService.convertToDTO(novoPedido);
        return ResponseEntity.ok(novoPedidoDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() throws Exception {
        List<Pedido> pedidos = pedidoService.listarTodos();
        List<PedidoDTO> pedidosDTO = pedidos.stream()
                .map(pedidoService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) throws Exception {
        return pedidoService.buscarPorId(id)
                .map(pedido -> ResponseEntity.ok(pedidoService.convertToDTO(pedido)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) throws Exception {
        Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoService.convertToEntity(pedidoDTO));
        PedidoDTO pedidoDTOAtualizado = pedidoService.convertToDTO(pedidoAtualizado);
        return ResponseEntity.ok(pedidoDTOAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) throws Exception {
        pedidoService.deletarPedido(id);
        return ResponseEntity.ok().build();
    }
}
