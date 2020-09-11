package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.service.CandidatoService;
import it.laziocrea.jemoloapp.web.rest.errors.BadRequestAlertException;
import it.laziocrea.jemoloapp.service.dto.CandidatoDTO;
import it.laziocrea.jemoloapp.service.dto.CandidatoCriteria;
import it.laziocrea.jemoloapp.service.CandidatoQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.laziocrea.jemoloapp.domain.Candidato}.
 */
@RestController
@RequestMapping("/api")
public class CandidatoResource {

    private final Logger log = LoggerFactory.getLogger(CandidatoResource.class);

    private static final String ENTITY_NAME = "candidato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidatoService candidatoService;

    private final CandidatoQueryService candidatoQueryService;

    public CandidatoResource(CandidatoService candidatoService, CandidatoQueryService candidatoQueryService) {
        this.candidatoService = candidatoService;
        this.candidatoQueryService = candidatoQueryService;
    }

    /**
     * {@code POST  /candidatoes} : Create a new candidato.
     *
     * @param candidatoDTO the candidatoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidatoDTO, or with status {@code 400 (Bad Request)} if the candidato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/candidatoes")
    public ResponseEntity<CandidatoDTO> createCandidato(@Valid @RequestBody CandidatoDTO candidatoDTO) throws URISyntaxException {
        log.debug("REST request to save Candidato : {}", candidatoDTO);
        if (candidatoDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CandidatoDTO result = candidatoService.save(candidatoDTO);
        return ResponseEntity.created(new URI("/api/candidatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /candidatoes} : Updates an existing candidato.
     *
     * @param candidatoDTO the candidatoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidatoDTO,
     * or with status {@code 400 (Bad Request)} if the candidatoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidatoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/candidatoes")
    public ResponseEntity<CandidatoDTO> updateCandidato(@Valid @RequestBody CandidatoDTO candidatoDTO) throws URISyntaxException {
        log.debug("REST request to update Candidato : {}", candidatoDTO);
        if (candidatoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CandidatoDTO result = candidatoService.save(candidatoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, candidatoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /candidatoes} : get all the candidatoes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidatoes in body.
     */
    @GetMapping("/candidatoes")
    public ResponseEntity<List<CandidatoDTO>> getAllCandidatoes(CandidatoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Candidatoes by criteria: {}", criteria);
        Page<CandidatoDTO> page = candidatoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /candidatoes/count} : count all the candidatoes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/candidatoes/count")
    public ResponseEntity<Long> countCandidatoes(CandidatoCriteria criteria) {
        log.debug("REST request to count Candidatoes by criteria: {}", criteria);
        return ResponseEntity.ok().body(candidatoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /candidatoes/:id} : get the "id" candidato.
     *
     * @param id the id of the candidatoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidatoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidatoes/{id}")
    public ResponseEntity<CandidatoDTO> getCandidato(@PathVariable Long id) {
        log.debug("REST request to get Candidato : {}", id);
        Optional<CandidatoDTO> candidatoDTO = candidatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(candidatoDTO);
    }

    /**
     * {@code DELETE  /candidatoes/:id} : delete the "id" candidato.
     *
     * @param id the id of the candidatoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidatoes/{id}")
    public ResponseEntity<Void> deleteCandidato(@PathVariable Long id) {
        log.debug("REST request to delete Candidato : {}", id);
        candidatoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
