package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.ItemPedido;
import com.sbaldass.combo.domain.Pedido;
import com.sbaldass.combo.domain.Prato;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.ItemPedidoDTO;
import com.sbaldass.combo.dto.PedidoDTO;
import com.sbaldass.combo.repositories.ItemPedidoRepository;
import com.sbaldass.combo.repositories.PedidoRepository;
import com.sbaldass.combo.repositories.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PratoRepository pratoRepository;

    public ItemPedido criarPedido(ItemPedido pedido) throws Exception {
        try {
            return itemPedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new Exception("Erro ao criar item de pedido.");
        }
    }

    public List<ItemPedido> listarTodos() throws Exception {
        try {
            return itemPedidoRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Erro ao listar itens de pedidos.");
        }
    }

    public Optional<ItemPedido> buscarPorId(Long id) throws Exception {
        try {
            return itemPedidoRepository.findById(id);
        } catch (Exception e) {
            throw new Exception("Pedido não encontrado.");
        }
    }

    public ItemPedido atualizarPedido(Long id, ItemPedido pedidoAtualizado) throws Exception {
        try {
            return itemPedidoRepository.findById(id)
                    .map((ItemPedido pedido) -> {
                        pedido.setQuantidade(pedidoAtualizado.getQuantidade());
                        pedido.setPrecoUnitario(pedidoAtualizado.getPrecoUnitario());
                        return itemPedidoRepository.save(pedido);
                    })
                            .orElseGet(() -> {
                                pedidoAtualizado.setId(id);
                                return itemPedidoRepository.save(pedidoAtualizado);
                            });
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar item de pedido.");
        }
    }

    public void deletarPedido(Long id) throws Exception {
        try {
            itemPedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Erro ao deletar item de pedido.");
        }
    }

    public ItemPedidoDTO convertToDTO(Long id, ItemPedido itemPedido) {
        ItemPedidoDTO pedidoDTO = new ItemPedidoDTO();
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        Optional<Prato> prato = pratoRepository.findById(id);
        pedido.ifPresent(value -> pedidoDTO.setPedidoId(value.getId()));
        prato.ifPresent(value -> pedidoDTO.setPratoId(value.getId()));
        pedidoDTO.setQuantidade(itemPedido.getQuantidade());
        pedidoDTO.setPrecoUnitario(itemPedido.getPrecoUnitario());
        return pedidoDTO;
    }

    public ItemPedido convertItemPedidoDTOToEntity(ItemPedidoDTO itemPedidoDTO) {
        ItemPedido itemPedido = new ItemPedido();

        Pedido pedido = pedidoRepository.findById(itemPedidoDTO.getPedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + itemPedidoDTO.getPedidoId()));
        itemPedido.setPedidoId(pedido.getId());

        Prato prato = pratoRepository.findById(itemPedidoDTO.getPratoId())
                .orElseThrow(() -> new EntityNotFoundException("Prato não encontrado com ID: " + itemPedidoDTO.getPratoId()));
        itemPedido.setPratoId(prato.getId());

        itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
        itemPedido.setPrecoUnitario(itemPedidoDTO.getPrecoUnitario());

        return itemPedido;
    }

}
