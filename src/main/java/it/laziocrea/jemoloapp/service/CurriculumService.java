package it.laziocrea.jemoloapp.service;

import it.laziocrea.jemoloapp.service.dto.CurriculumDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link it.laziocrea.jemoloapp.domain.Curriculum}.
 */
public interface CurriculumService {

    /**
     * Save a curriculum.
     *
     * @param curriculumDTO the entity to save.
     * @return the persisted entity.
     */
    CurriculumDTO save(CurriculumDTO curriculumDTO);

    /**
     * Get all the curricula.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CurriculumDTO> findAll(Pageable pageable);

    /**
     * Get the "id" curriculum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CurriculumDTO> findOne(Long id);

    /**
     * Delete the "id" curriculum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
