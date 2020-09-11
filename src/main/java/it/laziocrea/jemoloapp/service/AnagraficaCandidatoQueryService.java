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

import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.domain.*; // for static metamodels
import it.laziocrea.jemoloapp.repository.AnagraficaCandidatoRepository;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoCriteria;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoDTO;
import it.laziocrea.jemoloapp.service.mapper.AnagraficaCandidatoMapper;

/**
 * Service for executing complex queries for {@link AnagraficaCandidato} entities in the database.
 * The main input is a {@link AnagraficaCandidatoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnagraficaCandidatoDTO} or a {@link Page} of {@link AnagraficaCandidatoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnagraficaCandidatoQueryService extends QueryService<AnagraficaCandidato> {

    private final Logger log = LoggerFactory.getLogger(AnagraficaCandidatoQueryService.class);

    private final AnagraficaCandidatoRepository anagraficaCandidatoRepository;

    private final AnagraficaCandidatoMapper anagraficaCandidatoMapper;

    public AnagraficaCandidatoQueryService(AnagraficaCandidatoRepository anagraficaCandidatoRepository, AnagraficaCandidatoMapper anagraficaCandidatoMapper) {
        this.anagraficaCandidatoRepository = anagraficaCandidatoRepository;
        this.anagraficaCandidatoMapper = anagraficaCandidatoMapper;
    }

    /**
     * Return a {@link List} of {@link AnagraficaCandidatoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnagraficaCandidatoDTO> findByCriteria(AnagraficaCandidatoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AnagraficaCandidato> specification = createSpecification(criteria);
        return anagraficaCandidatoMapper.toDto(anagraficaCandidatoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AnagraficaCandidatoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnagraficaCandidatoDTO> findByCriteria(AnagraficaCandidatoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AnagraficaCandidato> specification = createSpecification(criteria);
        return anagraficaCandidatoRepository.findAll(specification, page)
            .map(anagraficaCandidatoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnagraficaCandidatoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AnagraficaCandidato> specification = createSpecification(criteria);
        return anagraficaCandidatoRepository.count(specification);
    }

    /**
     * Function to convert {@link AnagraficaCandidatoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AnagraficaCandidato> createSpecification(AnagraficaCandidatoCriteria criteria) {
        Specification<AnagraficaCandidato> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AnagraficaCandidato_.id));
            }
            if (criteria.getCognome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCognome(), AnagraficaCandidato_.cognome));
            }
            if (criteria.getNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNome(), AnagraficaCandidato_.nome));
            }
            if (criteria.getLuogoNascita() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLuogoNascita(), AnagraficaCandidato_.luogoNascita));
            }
            if (criteria.getDataNascita() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataNascita(), AnagraficaCandidato_.dataNascita));
            }
            if (criteria.getCodiceFiscale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodiceFiscale(), AnagraficaCandidato_.codiceFiscale));
            }
            if (criteria.getProfessione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProfessione(), AnagraficaCandidato_.professione));
            }
            if (criteria.getPartitaIva() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPartitaIva(), AnagraficaCandidato_.partitaIva));
            }
            if (criteria.getNumeroTelefonoFisso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroTelefonoFisso(), AnagraficaCandidato_.numeroTelefonoFisso));
            }
            if (criteria.getNumeroTelefonoCellulare() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroTelefonoCellulare(), AnagraficaCandidato_.numeroTelefonoCellulare));
            }
            if (criteria.geteMail() != null) {
                specification = specification.and(buildStringSpecification(criteria.geteMail(), AnagraficaCandidato_.eMail));
            }
            if (criteria.getIndirizzoPec() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIndirizzoPec(), AnagraficaCandidato_.indirizzoPec));
            }
            if (criteria.getIndirizzoResidenza() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIndirizzoResidenza(), AnagraficaCandidato_.indirizzoResidenza));
            }
            if (criteria.getCapResidenza() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCapResidenza(), AnagraficaCandidato_.capResidenza));
            }
            if (criteria.getComuneResidenza() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComuneResidenza(), AnagraficaCandidato_.comuneResidenza));
            }
            if (criteria.getProvinciaResidenza() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProvinciaResidenza(), AnagraficaCandidato_.provinciaResidenza));
            }
            if (criteria.getNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNote(), AnagraficaCandidato_.note));
            }
            if (criteria.getCompetenzeLngId() != null) {
                specification = specification.and(buildSpecification(criteria.getCompetenzeLngId(),
                    root -> root.join(AnagraficaCandidato_.competenzeLngs, JoinType.LEFT).get(CompetenzeLng_.id)));
            }
            if (criteria.getTitoloStudioId() != null) {
                specification = specification.and(buildSpecification(criteria.getTitoloStudioId(),
                    root -> root.join(AnagraficaCandidato_.titoloStudios, JoinType.LEFT).get(TitoloStudio_.id)));
            }
            if (criteria.getCurriculumId() != null) {
                specification = specification.and(buildSpecification(criteria.getCurriculumId(),
                    root -> root.join(AnagraficaCandidato_.curricula, JoinType.LEFT).get(Curriculum_.id)));
            }
            if (criteria.getCompetenzaId() != null) {
                specification = specification.and(buildSpecification(criteria.getCompetenzaId(),
                    root -> root.join(AnagraficaCandidato_.competenzas, JoinType.LEFT).get(Competenza_.id)));
            }
            if (criteria.getDichiarazioniId() != null) {
                specification = specification.and(buildSpecification(criteria.getDichiarazioniId(),
                    root -> root.join(AnagraficaCandidato_.dichiarazionis, JoinType.LEFT).get(DichiarazioniObligatorie_.id)));
            }
            if (criteria.getCandidatoId() != null) {
                specification = specification.and(buildSpecification(criteria.getCandidatoId(),
                    root -> root.join(AnagraficaCandidato_.candidato, JoinType.LEFT).get(Candidato_.id)));
            }
        }
        return specification;
    }
}
