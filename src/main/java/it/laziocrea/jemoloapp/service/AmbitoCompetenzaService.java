package it.laziocrea.jemoloapp.service;

import it.laziocrea.jemoloapp.domain.AmbitoCompetenza;
import it.laziocrea.jemoloapp.repository.AmbitoCompetenzaRepository;
import it.laziocrea.jemoloapp.service.dto.AmbitoCompetenzaDTO;
import it.laziocrea.jemoloapp.service.mapper.AmbitoCompetenzaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AmbitoCompetenza}.
 */
@Service
@Transactional
public class AmbitoCompetenzaService {

    private final Logger log = LoggerFactory.getLogger(AmbitoCompetenzaService.class);

    private final AmbitoCompetenzaRepository ambitoCompetenzaRepository;

    private final AmbitoCompetenzaMapper ambitoCompetenzaMapper;

    public AmbitoCompetenzaService(AmbitoCompetenzaRepository ambitoCompetenzaRepository, AmbitoCompetenzaMapper ambitoCompetenzaMapper) {
        this.ambitoCompetenzaRepository = ambitoCompetenzaRepository;
        this.ambitoCompetenzaMapper = ambitoCompetenzaMapper;
    }

    /**
     * Save a ambitoCompetenza.
     *
     * @param ambitoCompetenzaDTO the entity to save.
     * @return the persisted entity.
     */
    public AmbitoCompetenzaDTO save(AmbitoCompetenzaDTO ambitoCompetenzaDTO) {
        log.debug("Request to save AmbitoCompetenza : {}", ambitoCompetenzaDTO);
        AmbitoCompetenza ambitoCompetenza = ambitoCompetenzaMapper.toEntity(ambitoCompetenzaDTO);
        ambitoCompetenza = ambitoCompetenzaRepository.save(ambitoCompetenza);
        return ambitoCompetenzaMapper.toDto(ambitoCompetenza);
    }

    /**
     * Get all the ambitoCompetenzas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AmbitoCompetenzaDTO> findAll() {
        log.debug("Request to get all AmbitoCompetenzas");
        return ambitoCompetenzaRepository.findAll().stream()
            .map(ambitoCompetenzaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ambitoCompetenza by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AmbitoCompetenzaDTO> findOne(Long id) {
        log.debug("Request to get AmbitoCompetenza : {}", id);
        return ambitoCompetenzaRepository.findById(id)
            .map(ambitoCompetenzaMapper::toDto);
    }

    /**
     * Delete the ambitoCompetenza by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AmbitoCompetenza : {}", id);
        ambitoCompetenzaRepository.deleteById(id);
    }
}
