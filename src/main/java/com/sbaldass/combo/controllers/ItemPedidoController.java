package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.ItemPedido;
import com.sbaldass.combo.domain.Pedido;
import com.sbaldass.combo.dto.ItemPedidoDTO;
import com.sbaldass.combo.dto.PedidoDTO;
import com.sbaldass.combo.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/itensPedido")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService pedidoService;

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> criarPedido(@RequestBody ItemPedidoDTO pedidoDTO) throws Exception {
        ItemPedido pedido = pedidoService.convertItemPedidoDTOToEntity(pedidoDTO);
        ItemPedido novoPedido = pedidoService.criarPedido(pedido);
        ItemPedidoDTO novoPedidoDTO = pedidoService.convertToDTO(novoPedido.getPedidoId(), novoPedido);
        return ResponseEntity.ok(novoPedidoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> listarTodos() throws Exception {
        List<ItemPedido> pedidos = pedidoService.listarTodos();
        List<ItemPedidoDTO> pedidosDTO = pedidos.stream()
                .map((ItemPedido item) -> pedidoService.convertToDTO(item.getPedidoId(), item))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> buscarPorId(@PathVariable Long id) throws Exception {
        return pedidoService.buscarPorId(id)
                .map(pedido -> ResponseEntity.ok(pedidoService.convertToDTO(pedido.getPedidoId(), pedido)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> atualizarPedido(@PathVariable Long id, @RequestBody ItemPedidoDTO pedidoDTO) throws Exception {
        ItemPedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoService.convertItemPedidoDTOToEntity(pedidoDTO));
        ItemPedidoDTO pedidoDTOAtualizado = pedidoService.convertToDTO(pedidoAtualizado.getPedidoId(), pedidoAtualizado);
        return ResponseEntity.ok(pedidoDTOAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) throws Exception {
        pedidoService.deletarPedido(id);
        return ResponseEntity.ok().build();
    }
}
