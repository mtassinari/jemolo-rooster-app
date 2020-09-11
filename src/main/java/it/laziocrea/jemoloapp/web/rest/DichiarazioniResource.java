package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.domain.Dichiarazioni;
import it.laziocrea.jemoloapp.repository.DichiarazioniRepository;
import it.laziocrea.jemoloapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.laziocrea.jemoloapp.domain.Dichiarazioni}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DichiarazioniResource {

    private final Logger log = LoggerFactory.getLogger(DichiarazioniResource.class);

    private static final String ENTITY_NAME = "dichiarazioni";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DichiarazioniRepository dichiarazioniRepository;

    public DichiarazioniResource(DichiarazioniRepository dichiarazioniRepository) {
        this.dichiarazioniRepository = dichiarazioniRepository;
    }

    /**
     * {@code POST  /dichiarazionis} : Create a new dichiarazioni.
     *
     * @param dichiarazioni the dichiarazioni to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dichiarazioni, or with status {@code 400 (Bad Request)} if the dichiarazioni has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dichiarazionis")
    public ResponseEntity<Dichiarazioni> createDichiarazioni(@Valid @RequestBody Dichiarazioni dichiarazioni) throws URISyntaxException {
        log.debug("REST request to save Dichiarazioni : {}", dichiarazioni);
        if (dichiarazioni.getId() != null) {
            throw new BadRequestAlertException("A new dichiarazioni cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Dichiarazioni result = dichiarazioniRepository.save(dichiarazioni);
        return ResponseEntity.created(new URI("/api/dichiarazionis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dichiarazionis} : Updates an existing dichiarazioni.
     *
     * @param dichiarazioni the dichiarazioni to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dichiarazioni,
     * or with status {@code 400 (Bad Request)} if the dichiarazioni is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dichiarazioni couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dichiarazionis")
    public ResponseEntity<Dichiarazioni> updateDichiarazioni(@Valid @RequestBody Dichiarazioni dichiarazioni) throws URISyntaxException {
        log.debug("REST request to update Dichiarazioni : {}", dichiarazioni);
        if (dichiarazioni.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dichiarazioni result = dichiarazioniRepository.save(dichiarazioni);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dichiarazioni.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dichiarazionis} : get all the dichiarazionis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dichiarazionis in body.
     */
    @GetMapping("/dichiarazionis")
    public List<Dichiarazioni> getAllDichiarazionis() {
        log.debug("REST request to get all Dichiarazionis");
        return dichiarazioniRepository.findAll();
    }

    /**
     * {@code GET  /dichiarazionis/:id} : get the "id" dichiarazioni.
     *
     * @param id the id of the dichiarazioni to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dichiarazioni, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dichiarazionis/{id}")
    public ResponseEntity<Dichiarazioni> getDichiarazioni(@PathVariable Long id) {
        log.debug("REST request to get Dichiarazioni : {}", id);
        Optional<Dichiarazioni> dichiarazioni = dichiarazioniRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dichiarazioni);
    }

    /**
     * {@code DELETE  /dichiarazionis/:id} : delete the "id" dichiarazioni.
     *
     * @param id the id of the dichiarazioni to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dichiarazionis/{id}")
    public ResponseEntity<Void> deleteDichiarazioni(@PathVariable Long id) {
        log.debug("REST request to delete Dichiarazioni : {}", id);
        dichiarazioniRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
