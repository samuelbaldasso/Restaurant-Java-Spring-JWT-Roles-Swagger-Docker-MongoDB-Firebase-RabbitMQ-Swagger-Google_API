package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.ItemPedido;
import com.sbaldass.combo.domain.Pedido;
import com.sbaldass.combo.domain.Prato;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.ItemPedidoDTO;
import com.sbaldass.combo.dto.PedidoDTO;
import com.sbaldass.combo.repositories.PedidoRepository;
import com.sbaldass.combo.repositories.PratoRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private UserService usuarioService;

    public Pedido criarPedido(Pedido pedido) throws Exception {
        try {
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new Exception("Erro ao criar pedido.");
        }
    }

    public List<Pedido> listarTodos() throws Exception {
        try {
            return pedidoRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Erro ao listar pedidos.");
        }
    }

    public Optional<Pedido> buscarPorId(Long id) throws Exception {
        try {
            return pedidoRepository.findById(id);
        } catch (Exception e) {
            throw new Exception("Pedido não encontrado.");
        }
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) throws Exception {
        try {
            return pedidoRepository.findById(id)
                    .map(pedido -> pedidoRepository.save(pedido))
                    .orElseGet(() -> {
                        pedidoAtualizado.setId(id);
                        return pedidoRepository.save(pedidoAtualizado);
                    });
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar pedido.");
        }
    }

    public void deletarPedido(Long id) throws Exception {
        try {
            pedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Erro ao deletar pedido.");
        }
    }

    private ItemPedidoDTO convertItemPedidoToDTO(ItemPedido itemPedido) {
        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
        itemPedidoDTO.setId(itemPedido.getId());
        itemPedidoDTO.setPedidoId(itemPedido.getPedido().getId());
        itemPedidoDTO.setPratoId(itemPedido.getPrato().getId());
        itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
        itemPedidoDTO.setPrecoUnitario(itemPedido.getPrecoUnitario());

        return itemPedidoDTO;
    }

    private ItemPedido convertItemPedidoDTOToEntity(ItemPedidoDTO itemPedidoDTO) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(itemPedidoDTO.getId());

        Pedido pedido = pedidoRepository.findById(itemPedidoDTO.getPedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + itemPedidoDTO.getPedidoId()));
        itemPedido.setPedido(pedido);

        Prato prato = pratoRepository.findById(itemPedidoDTO.getPratoId())
                .orElseThrow(() -> new EntityNotFoundException("Prato não encontrado com ID: " + itemPedidoDTO.getPratoId()));
        itemPedido.setPrato(prato);

        itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
        itemPedido.setPrecoUnitario(itemPedidoDTO.getPrecoUnitario());

        return itemPedido;
    }

    public PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setDataHora(pedido.getDataHora());
        pedidoDTO.setStatus(pedido.getStatus());
        pedidoDTO.setTotal(pedido.getTotal());
        pedidoDTO.setUsuarioId(pedido.getUsuario().getId());

        List<ItemPedidoDTO> itensDTO = pedido.getItens().stream()
                .map(this::convertItemPedidoToDTO)
                .collect(Collectors.toList());
        pedidoDTO.setItens(itensDTO);

        return pedidoDTO;
    }

    public Pedido convertToEntity(PedidoDTO pedidoDTO) throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setDataHora(pedidoDTO.getDataHora());
        pedido.setStatus(pedidoDTO.getStatus());
        pedido.setTotal(pedidoDTO.getTotal());

        User usuario = usuarioService.findUserById(pedidoDTO.getUsuarioId());
        pedido.setUsuario(usuario);

        List<ItemPedido> itens = pedidoDTO.getItens().stream()
                .map(this::convertItemPedidoDTOToEntity)
                .collect(Collectors.toList());

        for (ItemPedido item : itens) {
            item.setPedido(pedido);
        }

        pedido.setItens(itens);

        return pedido;
    }


}
