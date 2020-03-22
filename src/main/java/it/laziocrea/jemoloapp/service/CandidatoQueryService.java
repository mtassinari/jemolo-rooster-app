package it.laziocrea.jemoloapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import it.laziocrea.jemoloapp.domain.Candidato;
import it.laziocrea.jemoloapp.domain.*; // for static metamodels
import it.laziocrea.jemoloapp.repository.CandidatoRepository;
import it.laziocrea.jemoloapp.service.dto.CandidatoCriteria;
import it.laziocrea.jemoloapp.service.dto.CandidatoDTO;
import it.laziocrea.jemoloapp.service.mapper.CandidatoMapper;

/**
 * Service for executing complex queries for {@link Candidato} entities in the database.
 * The main input is a {@link CandidatoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CandidatoDTO} or a {@link Page} of {@link CandidatoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CandidatoQueryService extends QueryService<Candidato> {

    private final Logger log = LoggerFactory.getLogger(CandidatoQueryService.class);

    private final CandidatoRepository candidatoRepository;

    private final CandidatoMapper candidatoMapper;

    public CandidatoQueryService(CandidatoRepository candidatoRepository, CandidatoMapper candidatoMapper) {
        this.candidatoRepository = candidatoRepository;
        this.candidatoMapper = candidatoMapper;
    }

    /**
     * Return a {@link List} of {@link CandidatoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CandidatoDTO> findByCriteria(CandidatoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Candidato> specification = createSpecification(criteria);
        return candidatoMapper.toDto(candidatoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CandidatoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findByCriteria(CandidatoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Candidato> specification = createSpecification(criteria);
        return candidatoRepository.findAll(specification, page)
            .map(candidatoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CandidatoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Candidato> specification = createSpecification(criteria);
        return candidatoRepository.count(specification);
    }

    /**
     * Function to convert {@link CandidatoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Candidato> createSpecification(CandidatoCriteria criteria) {
        Specification<Candidato> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Candidato_.id));
            }
            if (criteria.getNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNome(), Candidato_.nome));
            }
            if (criteria.getCognome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCognome(), Candidato_.cognome));
            }
            if (criteria.getCodiceFiscale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodiceFiscale(), Candidato_.codiceFiscale));
            }
            if (criteria.geteMail() != null) {
                specification = specification.and(buildStringSpecification(criteria.geteMail(), Candidato_.eMail));
            }
            if (criteria.getAnagraficaCandidatoId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnagraficaCandidatoId(),
                    root -> root.join(Candidato_.anagraficaCandidato, JoinType.LEFT).get(AnagraficaCandidato_.id)));
            }
            if (criteria.getStatoRegistrazioneId() != null) {
                specification = specification.and(buildSpecification(criteria.getStatoRegistrazioneId(),
                    root -> root.join(Candidato_.statoRegistrazione, JoinType.LEFT).get(StatoRegistrazione_.id)));
            }
        }
        return specification;
    }
}
