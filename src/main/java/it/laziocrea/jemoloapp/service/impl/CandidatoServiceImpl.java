package it.laziocrea.jemoloapp.service.impl;

import it.laziocrea.jemoloapp.service.CandidatoService;
import it.laziocrea.jemoloapp.domain.Candidato;
import it.laziocrea.jemoloapp.repository.CandidatoRepository;
import it.laziocrea.jemoloapp.service.dto.CandidatoDTO;
import it.laziocrea.jemoloapp.service.mapper.CandidatoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Candidato}.
 */
@Service
@Transactional
public class CandidatoServiceImpl implements CandidatoService {

    private final Logger log = LoggerFactory.getLogger(CandidatoServiceImpl.class);

    private final CandidatoRepository candidatoRepository;

    private final CandidatoMapper candidatoMapper;

    public CandidatoServiceImpl(CandidatoRepository candidatoRepository, CandidatoMapper candidatoMapper) {
        this.candidatoRepository = candidatoRepository;
        this.candidatoMapper = candidatoMapper;
    }

    /**
     * Save a candidato.
     *
     * @param candidatoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CandidatoDTO save(CandidatoDTO candidatoDTO) {
        log.debug("Request to save Candidato : {}", candidatoDTO);
        Candidato candidato = candidatoMapper.toEntity(candidatoDTO);
        candidato = candidatoRepository.save(candidato);
        return candidatoMapper.toDto(candidato);
    }

    /**
     * Get all the candidatoes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Candidatoes");
        return candidatoRepository.findAll(pageable)
            .map(candidatoMapper::toDto);
    }


    /**
     *  Get all the candidatoes where AnagraficaCandidato is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<CandidatoDTO> findAllWhereAnagraficaCandidatoIsNull() {
        log.debug("Request to get all candidatoes where AnagraficaCandidato is null");
        return StreamSupport
            .stream(candidatoRepository.findAll().spliterator(), false)
            .filter(candidato -> candidato.getAnagraficaCandidato() == null)
            .map(candidatoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one candidato by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CandidatoDTO> findOne(Long id) {
        log.debug("Request to get Candidato : {}", id);
        return candidatoRepository.findById(id)
            .map(candidatoMapper::toDto);
    }

    /**
     * Delete the candidato by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Candidato : {}", id);
        candidatoRepository.deleteById(id);
    }
}
