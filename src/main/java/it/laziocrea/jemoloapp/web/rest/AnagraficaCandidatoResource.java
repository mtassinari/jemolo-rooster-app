package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.service.AnagraficaCandidatoService;
import it.laziocrea.jemoloapp.web.rest.errors.BadRequestAlertException;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoDTO;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoCriteria;
import it.laziocrea.jemoloapp.service.AnagraficaCandidatoQueryService;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link it.laziocrea.jemoloapp.domain.AnagraficaCandidato}.
 */
@RestController
@RequestMapping("/api")
public class AnagraficaCandidatoResource {

    private final Logger log = LoggerFactory.getLogger(AnagraficaCandidatoResource.class);

    private static final String ENTITY_NAME = "anagraficaCandidato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnagraficaCandidatoService anagraficaCandidatoService;

    private final AnagraficaCandidatoQueryService anagraficaCandidatoQueryService;

    public AnagraficaCandidatoResource(AnagraficaCandidatoService anagraficaCandidatoService, AnagraficaCandidatoQueryService anagraficaCandidatoQueryService) {
        this.anagraficaCandidatoService = anagraficaCandidatoService;
        this.anagraficaCandidatoQueryService = anagraficaCandidatoQueryService;
    }

    /**
     * {@code POST  /anagrafica-candidatoes} : Create a new anagraficaCandidato.
     *
     * @param anagraficaCandidatoDTO the anagraficaCandidatoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new anagraficaCandidatoDTO, or with status {@code 400 (Bad Request)} if the anagraficaCandidato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/anagrafica-candidatoes")
    public ResponseEntity<AnagraficaCandidatoDTO> createAnagraficaCandidato(@Valid @RequestBody AnagraficaCandidatoDTO anagraficaCandidatoDTO) throws URISyntaxException {
        log.debug("REST request to save AnagraficaCandidato : {}", anagraficaCandidatoDTO);
        if (anagraficaCandidatoDTO.getId() != null) {
            throw new BadRequestAlertException("A new anagraficaCandidato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnagraficaCandidatoDTO result = anagraficaCandidatoService.save(anagraficaCandidatoDTO);
        return ResponseEntity.created(new URI("/api/anagrafica-candidatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /anagrafica-candidatoes} : Updates an existing anagraficaCandidato.
     *
     * @param anagraficaCandidatoDTO the anagraficaCandidatoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anagraficaCandidatoDTO,
     * or with status {@code 400 (Bad Request)} if the anagraficaCandidatoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the anagraficaCandidatoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/anagrafica-candidatoes")
    public ResponseEntity<AnagraficaCandidatoDTO> updateAnagraficaCandidato(@Valid @RequestBody AnagraficaCandidatoDTO anagraficaCandidatoDTO) throws URISyntaxException {
        log.debug("REST request to update AnagraficaCandidato : {}", anagraficaCandidatoDTO);
        if (anagraficaCandidatoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnagraficaCandidatoDTO result = anagraficaCandidatoService.save(anagraficaCandidatoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, anagraficaCandidatoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /anagrafica-candidatoes} : get all the anagraficaCandidatoes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of anagraficaCandidatoes in body.
     */
    @GetMapping("/anagrafica-candidatoes")
    public ResponseEntity<List<AnagraficaCandidatoDTO>> getAllAnagraficaCandidatoes(AnagraficaCandidatoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AnagraficaCandidatoes by criteria: {}", criteria);
        Page<AnagraficaCandidatoDTO> page = anagraficaCandidatoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /anagrafica-candidatoes/count} : count all the anagraficaCandidatoes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/anagrafica-candidatoes/count")
    public ResponseEntity<Long> countAnagraficaCandidatoes(AnagraficaCandidatoCriteria criteria) {
        log.debug("REST request to count AnagraficaCandidatoes by criteria: {}", criteria);
        return ResponseEntity.ok().body(anagraficaCandidatoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /anagrafica-candidatoes/:id} : get the "id" anagraficaCandidato.
     *
     * @param id the id of the anagraficaCandidatoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the anagraficaCandidatoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/anagrafica-candidatoes/{id}")
    public ResponseEntity<AnagraficaCandidatoDTO> getAnagraficaCandidato(@PathVariable Long id) {
        log.debug("REST request to get AnagraficaCandidato : {}", id);
        Optional<AnagraficaCandidatoDTO> anagraficaCandidatoDTO = anagraficaCandidatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anagraficaCandidatoDTO);
    }

    /**
     * {@code DELETE  /anagrafica-candidatoes/:id} : delete the "id" anagraficaCandidato.
     *
     * @param id the id of the anagraficaCandidatoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/anagrafica-candidatoes/{id}")
    public ResponseEntity<Void> deleteAnagraficaCandidato(@PathVariable Long id) {
        log.debug("REST request to delete AnagraficaCandidato : {}", id);
        anagraficaCandidatoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
