package it.laziocrea.jemoloapp.service.impl;

import it.laziocrea.jemoloapp.service.AnagraficaCandidatoService;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.repository.AnagraficaCandidatoRepository;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoDTO;
import it.laziocrea.jemoloapp.service.mapper.AnagraficaCandidatoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AnagraficaCandidato}.
 */
@Service
@Transactional
public class AnagraficaCandidatoServiceImpl implements AnagraficaCandidatoService {

    private final Logger log = LoggerFactory.getLogger(AnagraficaCandidatoServiceImpl.class);

    private final AnagraficaCandidatoRepository anagraficaCandidatoRepository;

    private final AnagraficaCandidatoMapper anagraficaCandidatoMapper;

    public AnagraficaCandidatoServiceImpl(AnagraficaCandidatoRepository anagraficaCandidatoRepository, AnagraficaCandidatoMapper anagraficaCandidatoMapper) {
        this.anagraficaCandidatoRepository = anagraficaCandidatoRepository;
        this.anagraficaCandidatoMapper = anagraficaCandidatoMapper;
    }

    @Override
    public AnagraficaCandidatoDTO save(AnagraficaCandidatoDTO anagraficaCandidatoDTO) {
        log.debug("Request to save AnagraficaCandidato : {}", anagraficaCandidatoDTO);
        AnagraficaCandidato anagraficaCandidato = anagraficaCandidatoMapper.toEntity(anagraficaCandidatoDTO);
        anagraficaCandidato = anagraficaCandidatoRepository.save(anagraficaCandidato);
        return anagraficaCandidatoMapper.toDto(anagraficaCandidato);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnagraficaCandidatoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnagraficaCandidatoes");
        return anagraficaCandidatoRepository.findAll(pageable)
            .map(anagraficaCandidatoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AnagraficaCandidatoDTO> findOne(Long id) {
        log.debug("Request to get AnagraficaCandidato : {}", id);
        return anagraficaCandidatoRepository.findById(id)
            .map(anagraficaCandidatoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnagraficaCandidato : {}", id);
        anagraficaCandidatoRepository.deleteById(id);
    }
}
