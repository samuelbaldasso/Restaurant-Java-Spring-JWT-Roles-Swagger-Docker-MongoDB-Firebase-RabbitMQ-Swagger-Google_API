package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Prato;
import com.sbaldass.combo.dto.PratoDTO;
import com.sbaldass.combo.repositories.PratoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    public PratoDTO salvarPrato(PratoDTO pratoDTO) throws Exception {
        try {
            Prato prato = convertToEntity(pratoDTO);
            prato = pratoRepository.save(prato);
            return convertToDTO(prato);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar prato.");
        }
    }

    public List<PratoDTO> listarTodos() throws Exception {
        try {
            return pratoRepository.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Erro ao listar pratos.");
        }
    }

    public PratoDTO buscarPorId(Long id) {
        try{
            return pratoRepository.findById(id)
                    .map(this::convertToDTO)
                    .orElse(null);
        }catch (NullPointerException e){
            throw new NullPointerException("Prato não encontrado.");
        }
    }

    public PratoDTO atualizarPrato(Long id, PratoDTO pratoDTO) throws Exception {
        try{
            Prato prato = convertToEntity(pratoDTO);
            prato.setId(id); // Garanta que o ID esteja definido
            prato = pratoRepository.save(prato);
            return convertToDTO(prato);
        }catch (Exception e){
            throw new Exception("Erro ao atualizar prato.");
        }
    }

    public void deletarPrato(Long id) throws Exception {
        try{
            pratoRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception("Erro ao deletar prato.");
        }
    }

    private PratoDTO convertToDTO(@Valid Prato prato) {
        PratoDTO pratoDTO = new PratoDTO();
        pratoDTO.setId(prato.getId());
        pratoDTO.setNome(prato.getNome());
        pratoDTO.setDescricao(prato.getDescricao());
        pratoDTO.setPreco(prato.getPreco());
        pratoDTO.setCategoria(prato.getCategoria());
        pratoDTO.setFoto(prato.getFoto());

        return pratoDTO;
    }

    private Prato convertToEntity(PratoDTO pratoDTO) {
        Prato prato = new Prato();
        prato.setNome(pratoDTO.getNome());
        prato.setDescricao(pratoDTO.getDescricao());
        prato.setPreco(pratoDTO.getPreco());
        prato.setCategoria(pratoDTO.getCategoria());
        prato.setFoto(pratoDTO.getFoto());

        return prato;
    }

}
