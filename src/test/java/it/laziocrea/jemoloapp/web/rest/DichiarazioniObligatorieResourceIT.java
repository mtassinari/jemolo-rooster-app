package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.DichiarazioniObligatorie;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.repository.DichiarazioniObligatorieRepository;
import it.laziocrea.jemoloapp.service.DichiarazioniObligatorieService;
import it.laziocrea.jemoloapp.service.dto.DichiarazioniObligatorieDTO;
import it.laziocrea.jemoloapp.service.mapper.DichiarazioniObligatorieMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DichiarazioniObligatorieResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DichiarazioniObligatorieResourceIT {

    private static final Boolean DEFAULT_STATO = false;
    private static final Boolean UPDATED_STATO = true;

    @Autowired
    private DichiarazioniObligatorieRepository dichiarazioniObligatorieRepository;

    @Autowired
    private DichiarazioniObligatorieMapper dichiarazioniObligatorieMapper;

    @Autowired
    private DichiarazioniObligatorieService dichiarazioniObligatorieService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDichiarazioniObligatorieMockMvc;

    private DichiarazioniObligatorie dichiarazioniObligatorie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DichiarazioniObligatorie createEntity(EntityManager em) {
        DichiarazioniObligatorie dichiarazioniObligatorie = new DichiarazioniObligatorie()
            .stato(DEFAULT_STATO);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        dichiarazioniObligatorie.setAnagrafica(anagraficaCandidato);
        return dichiarazioniObligatorie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DichiarazioniObligatorie createUpdatedEntity(EntityManager em) {
        DichiarazioniObligatorie dichiarazioniObligatorie = new DichiarazioniObligatorie()
            .stato(UPDATED_STATO);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createUpdatedEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        dichiarazioniObligatorie.setAnagrafica(anagraficaCandidato);
        return dichiarazioniObligatorie;
    }

    @BeforeEach
    public void initTest() {
        dichiarazioniObligatorie = createEntity(em);
    }

    @Test
    @Transactional
    public void createDichiarazioniObligatorie() throws Exception {
        int databaseSizeBeforeCreate = dichiarazioniObligatorieRepository.findAll().size();
        // Create the DichiarazioniObligatorie
        DichiarazioniObligatorieDTO dichiarazioniObligatorieDTO = dichiarazioniObligatorieMapper.toDto(dichiarazioniObligatorie);
        restDichiarazioniObligatorieMockMvc.perform(post("/api/dichiarazioni-obligatories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniObligatorieDTO)))
            .andExpect(status().isCreated());

        // Validate the DichiarazioniObligatorie in the database
        List<DichiarazioniObligatorie> dichiarazioniObligatorieList = dichiarazioniObligatorieRepository.findAll();
        assertThat(dichiarazioniObligatorieList).hasSize(databaseSizeBeforeCreate + 1);
        DichiarazioniObligatorie testDichiarazioniObligatorie = dichiarazioniObligatorieList.get(dichiarazioniObligatorieList.size() - 1);
        assertThat(testDichiarazioniObligatorie.isStato()).isEqualTo(DEFAULT_STATO);
    }

    @Test
    @Transactional
    public void createDichiarazioniObligatorieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dichiarazioniObligatorieRepository.findAll().size();

        // Create the DichiarazioniObligatorie with an existing ID
        dichiarazioniObligatorie.setId(1L);
        DichiarazioniObligatorieDTO dichiarazioniObligatorieDTO = dichiarazioniObligatorieMapper.toDto(dichiarazioniObligatorie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDichiarazioniObligatorieMockMvc.perform(post("/api/dichiarazioni-obligatories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniObligatorieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DichiarazioniObligatorie in the database
        List<DichiarazioniObligatorie> dichiarazioniObligatorieList = dichiarazioniObligatorieRepository.findAll();
        assertThat(dichiarazioniObligatorieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dichiarazioniObligatorieRepository.findAll().size();
        // set the field null
        dichiarazioniObligatorie.setStato(null);

        // Create the DichiarazioniObligatorie, which fails.
        DichiarazioniObligatorieDTO dichiarazioniObligatorieDTO = dichiarazioniObligatorieMapper.toDto(dichiarazioniObligatorie);


        restDichiarazioniObligatorieMockMvc.perform(post("/api/dichiarazioni-obligatories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniObligatorieDTO)))
            .andExpect(status().isBadRequest());

        List<DichiarazioniObligatorie> dichiarazioniObligatorieList = dichiarazioniObligatorieRepository.findAll();
        assertThat(dichiarazioniObligatorieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDichiarazioniObligatories() throws Exception {
        // Initialize the database
        dichiarazioniObligatorieRepository.saveAndFlush(dichiarazioniObligatorie);

        // Get all the dichiarazioniObligatorieList
        restDichiarazioniObligatorieMockMvc.perform(get("/api/dichiarazioni-obligatories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dichiarazioniObligatorie.getId().intValue())))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDichiarazioniObligatorie() throws Exception {
        // Initialize the database
        dichiarazioniObligatorieRepository.saveAndFlush(dichiarazioniObligatorie);

        // Get the dichiarazioniObligatorie
        restDichiarazioniObligatorieMockMvc.perform(get("/api/dichiarazioni-obligatories/{id}", dichiarazioniObligatorie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dichiarazioniObligatorie.getId().intValue()))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDichiarazioniObligatorie() throws Exception {
        // Get the dichiarazioniObligatorie
        restDichiarazioniObligatorieMockMvc.perform(get("/api/dichiarazioni-obligatories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDichiarazioniObligatorie() throws Exception {
        // Initialize the database
        dichiarazioniObligatorieRepository.saveAndFlush(dichiarazioniObligatorie);

        int databaseSizeBeforeUpdate = dichiarazioniObligatorieRepository.findAll().size();

        // Update the dichiarazioniObligatorie
        DichiarazioniObligatorie updatedDichiarazioniObligatorie = dichiarazioniObligatorieRepository.findById(dichiarazioniObligatorie.getId()).get();
        // Disconnect from session so that the updates on updatedDichiarazioniObligatorie are not directly saved in db
        em.detach(updatedDichiarazioniObligatorie);
        updatedDichiarazioniObligatorie
            .stato(UPDATED_STATO);
        DichiarazioniObligatorieDTO dichiarazioniObligatorieDTO = dichiarazioniObligatorieMapper.toDto(updatedDichiarazioniObligatorie);

        restDichiarazioniObligatorieMockMvc.perform(put("/api/dichiarazioni-obligatories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniObligatorieDTO)))
            .andExpect(status().isOk());

        // Validate the DichiarazioniObligatorie in the database
        List<DichiarazioniObligatorie> dichiarazioniObligatorieList = dichiarazioniObligatorieRepository.findAll();
        assertThat(dichiarazioniObligatorieList).hasSize(databaseSizeBeforeUpdate);
        DichiarazioniObligatorie testDichiarazioniObligatorie = dichiarazioniObligatorieList.get(dichiarazioniObligatorieList.size() - 1);
        assertThat(testDichiarazioniObligatorie.isStato()).isEqualTo(UPDATED_STATO);
    }

    @Test
    @Transactional
    public void updateNonExistingDichiarazioniObligatorie() throws Exception {
        int databaseSizeBeforeUpdate = dichiarazioniObligatorieRepository.findAll().size();

        // Create the DichiarazioniObligatorie
        DichiarazioniObligatorieDTO dichiarazioniObligatorieDTO = dichiarazioniObligatorieMapper.toDto(dichiarazioniObligatorie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDichiarazioniObligatorieMockMvc.perform(put("/api/dichiarazioni-obligatories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniObligatorieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DichiarazioniObligatorie in the database
        List<DichiarazioniObligatorie> dichiarazioniObligatorieList = dichiarazioniObligatorieRepository.findAll();
        assertThat(dichiarazioniObligatorieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDichiarazioniObligatorie() throws Exception {
        // Initialize the database
        dichiarazioniObligatorieRepository.saveAndFlush(dichiarazioniObligatorie);

        int databaseSizeBeforeDelete = dichiarazioniObligatorieRepository.findAll().size();

        // Delete the dichiarazioniObligatorie
        restDichiarazioniObligatorieMockMvc.perform(delete("/api/dichiarazioni-obligatories/{id}", dichiarazioniObligatorie.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DichiarazioniObligatorie> dichiarazioniObligatorieList = dichiarazioniObligatorieRepository.findAll();
        assertThat(dichiarazioniObligatorieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
