package it.laziocrea.jemoloapp.service;

import it.laziocrea.jemoloapp.domain.CompetenzeLng;
import it.laziocrea.jemoloapp.repository.CompetenzeLngRepository;
import it.laziocrea.jemoloapp.service.dto.CompetenzeLngDTO;
import it.laziocrea.jemoloapp.service.mapper.CompetenzeLngMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CompetenzeLng}.
 */
@Service
@Transactional
public class CompetenzeLngService {

    private final Logger log = LoggerFactory.getLogger(CompetenzeLngService.class);

    private final CompetenzeLngRepository competenzeLngRepository;

    private final CompetenzeLngMapper competenzeLngMapper;

    public CompetenzeLngService(CompetenzeLngRepository competenzeLngRepository, CompetenzeLngMapper competenzeLngMapper) {
        this.competenzeLngRepository = competenzeLngRepository;
        this.competenzeLngMapper = competenzeLngMapper;
    }

    /**
     * Save a competenzeLng.
     *
     * @param competenzeLngDTO the entity to save.
     * @return the persisted entity.
     */
    public CompetenzeLngDTO save(CompetenzeLngDTO competenzeLngDTO) {
        log.debug("Request to save CompetenzeLng : {}", competenzeLngDTO);
        CompetenzeLng competenzeLng = competenzeLngMapper.toEntity(competenzeLngDTO);
        competenzeLng = competenzeLngRepository.save(competenzeLng);
        return competenzeLngMapper.toDto(competenzeLng);
    }

    /**
     * Get all the competenzeLngs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CompetenzeLngDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CompetenzeLngs");
        return competenzeLngRepository.findAll(pageable)
            .map(competenzeLngMapper::toDto);
    }


    /**
     * Get one competenzeLng by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CompetenzeLngDTO> findOne(Long id) {
        log.debug("Request to get CompetenzeLng : {}", id);
        return competenzeLngRepository.findById(id)
            .map(competenzeLngMapper::toDto);
    }

    /**
     * Delete the competenzeLng by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CompetenzeLng : {}", id);
        competenzeLngRepository.deleteById(id);
    }
}
