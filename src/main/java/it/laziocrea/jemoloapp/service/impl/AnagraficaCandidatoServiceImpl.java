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

    /**
     * Save a anagraficaCandidato.
     *
     * @param anagraficaCandidatoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AnagraficaCandidatoDTO save(AnagraficaCandidatoDTO anagraficaCandidatoDTO) {
        log.debug("Request to save AnagraficaCandidato : {}", anagraficaCandidatoDTO);
        AnagraficaCandidato anagraficaCandidato = anagraficaCandidatoMapper.toEntity(anagraficaCandidatoDTO);
        anagraficaCandidato = anagraficaCandidatoRepository.save(anagraficaCandidato);
        return anagraficaCandidatoMapper.toDto(anagraficaCandidato);
    }

    /**
     * Get all the anagraficaCandidatoes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AnagraficaCandidatoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnagraficaCandidatoes");
        return anagraficaCandidatoRepository.findAll(pageable)
            .map(anagraficaCandidatoMapper::toDto);
    }

    /**
     * Get one anagraficaCandidato by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AnagraficaCandidatoDTO> findOne(Long id) {
        log.debug("Request to get AnagraficaCandidato : {}", id);
        return anagraficaCandidatoRepository.findById(id)
            .map(anagraficaCandidatoMapper::toDto);
    }

    /**
     * Delete the anagraficaCandidato by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnagraficaCandidato : {}", id);
        anagraficaCandidatoRepository.deleteById(id);
    }
}
