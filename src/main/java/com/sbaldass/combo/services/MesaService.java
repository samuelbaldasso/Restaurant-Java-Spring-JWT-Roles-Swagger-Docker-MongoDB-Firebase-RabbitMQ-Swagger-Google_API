package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Mesa;
import com.sbaldass.combo.dto.MesaDTO;
import com.sbaldass.combo.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<MesaDTO> findAll() throws Exception {
        return mesaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MesaDTO> findById(Long id) throws Exception {
        return mesaRepository.findById(id)
                .map(this::convertToDTO);
    }

    public MesaDTO save(MesaDTO mesaDTO) throws Exception {
        Mesa mesa = convertToEntity(mesaDTO);
        return convertToDTO(mesaRepository.save(mesa));
    }

    public void deleteById(Long id) throws Exception {
        mesaRepository.deleteById(id);
    }

    private MesaDTO convertToDTO(Mesa mesa) {
        MesaDTO mesaDTO = new MesaDTO();
        mesaDTO.setId(mesa.getId());
        mesaDTO.setNumero(mesa.getNumero());
        mesaDTO.setCapacidade(mesa.getCapacidade());
        mesaDTO.setStatus(mesa.getStatus());
        return mesaDTO;
    }

    private Mesa convertToEntity(MesaDTO mesaDTO) {
        Mesa mesa = new Mesa();
        mesa.setId(mesaDTO.getId());
        mesa.setNumero(mesaDTO.getNumero());
        mesa.setCapacidade(mesaDTO.getCapacidade());
        mesa.setStatus(mesaDTO.getStatus());
        return mesa;
    }
}
