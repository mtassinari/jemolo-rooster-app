package it.laziocrea.jemoloapp.service;

import it.laziocrea.jemoloapp.service.dto.CandidatoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link it.laziocrea.jemoloapp.domain.Candidato}.
 */
public interface CandidatoService {

    /**
     * Save a candidato.
     *
     * @param candidatoDTO the entity to save.
     * @return the persisted entity.
     */
    CandidatoDTO save(CandidatoDTO candidatoDTO);

    /**
     * Get all the candidatoes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CandidatoDTO> findAll(Pageable pageable);
    /**
     * Get all the CandidatoDTO where AnagraficaCandidato is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<CandidatoDTO> findAllWhereAnagraficaCandidatoIsNull();


    /**
     * Get the "id" candidato.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CandidatoDTO> findOne(Long id);

    /**
     * Delete the "id" candidato.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
