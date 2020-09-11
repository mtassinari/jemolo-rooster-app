package it.laziocrea.jemoloapp.service;

import it.laziocrea.jemoloapp.service.dto.CompetenzaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link it.laziocrea.jemoloapp.domain.Competenza}.
 */
public interface CompetenzaService {

    /**
     * Save a competenza.
     *
     * @param competenzaDTO the entity to save.
     * @return the persisted entity.
     */
    CompetenzaDTO save(CompetenzaDTO competenzaDTO);

    /**
     * Get all the competenzas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CompetenzaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" competenza.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompetenzaDTO> findOne(Long id);

    /**
     * Delete the "id" competenza.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
