package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Reserva;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.ReservaDTO;
import com.sbaldass.combo.repositories.ReservaRepository;
import com.sbaldass.combo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReservaDTO> findAll() throws Exception {
        return reservaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReservaDTO> findById(Long id) throws Exception {
        return reservaRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ReservaDTO save(ReservaDTO reservaDTO) throws Exception {
        Reserva reserva = convertToEntity(reservaDTO);
        return convertToDTO(reservaRepository.save(reserva));
    }

    public void deleteById(Long id) throws Exception {
        reservaRepository.deleteById(id);
    }

    private ReservaDTO convertToDTO(Reserva reserva) {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(reserva.getId());
        reservaDTO.setUsuarioId(reserva.getUsuario().getId());
        reservaDTO.setDataHora(reserva.getDataHora());
        reservaDTO.setNumeroPessoas(reserva.getNumeroPessoas());
        reservaDTO.setStatus(reserva.getStatus());
        return reservaDTO;
    }

    private Reserva convertToEntity(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        User usuario = userRepository.findById(reservaDTO.getUsuarioId()).orElse(null);
        reserva.setId(reservaDTO.getId());
        reserva.setUsuario(usuario);
        reserva.setDataHora(reservaDTO.getDataHora());
        reserva.setNumeroPessoas(reservaDTO.getNumeroPessoas());
        reserva.setStatus(reservaDTO.getStatus());
        return reserva;
    }
}
