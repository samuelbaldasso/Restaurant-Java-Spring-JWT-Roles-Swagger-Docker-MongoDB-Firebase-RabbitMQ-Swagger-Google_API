package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Avaliacao;
import com.sbaldass.combo.domain.Prato;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.AvaliacaoDTO;
import com.sbaldass.combo.repositories.AvaliacaoRepository;
import com.sbaldass.combo.repositories.PratoRepository;
import com.sbaldass.combo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PratoRepository pratoRepository;

    public List<AvaliacaoDTO> findAll() throws Exception {
        return avaliacaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AvaliacaoDTO> findById(Long id) throws Exception {
        return avaliacaoRepository.findById(id)
                .map(this::convertToDTO);
    }

    public AvaliacaoDTO save(AvaliacaoDTO avaliacaoDTO) throws Exception {
        Avaliacao avaliacao = convertToEntity(avaliacaoDTO);
        return convertToDTO(avaliacaoRepository.save(avaliacao));
    }

    public void deleteById(Long id) throws Exception {
        avaliacaoRepository.deleteById(id);
    }

    private AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setUserId(avaliacao.getUsuario().getId());
        avaliacaoDTO.setPratoId(avaliacao.getPratoId());
        avaliacaoDTO.setPontuacao(avaliacao.getPontuacao());
        avaliacaoDTO.setComentario(avaliacao.getComentario());
        return avaliacaoDTO;
    }

    private Avaliacao convertToEntity(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = new Avaliacao();
        User usuario = userRepository.findById(avaliacaoDTO.getUserId()).orElse(null);
        Prato prato = pratoRepository.findById(avaliacaoDTO.getPratoId()).orElse(null);
        avaliacao.setId(avaliacaoDTO.getId());
        avaliacao.setUsuario(usuario);
        avaliacao.setPratoId(avaliacaoDTO.getPratoId());
        avaliacao.setPontuacao(avaliacaoDTO.getPontuacao());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        return avaliacao;
    }
}

