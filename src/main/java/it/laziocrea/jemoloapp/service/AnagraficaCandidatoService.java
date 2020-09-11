package it.laziocrea.jemoloapp.service;

import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link it.laziocrea.jemoloapp.domain.AnagraficaCandidato}.
 */
public interface AnagraficaCandidatoService {

    /**
     * Save a anagraficaCandidato.
     *
     * @param anagraficaCandidatoDTO the entity to save.
     * @return the persisted entity.
     */
    AnagraficaCandidatoDTO save(AnagraficaCandidatoDTO anagraficaCandidatoDTO);

    /**
     * Get all the anagraficaCandidatoes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnagraficaCandidatoDTO> findAll(Pageable pageable);
    /**
     * Get all the AnagraficaCandidatoDTO where Candidato is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<AnagraficaCandidatoDTO> findAllWhereCandidatoIsNull();


    /**
     * Get the "id" anagraficaCandidato.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnagraficaCandidatoDTO> findOne(Long id);

    /**
     * Delete the "id" anagraficaCandidato.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
