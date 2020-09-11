package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.Dichiarazioni;
import it.laziocrea.jemoloapp.repository.DichiarazioniRepository;
import it.laziocrea.jemoloapp.service.DichiarazioniService;
import it.laziocrea.jemoloapp.service.dto.DichiarazioniDTO;
import it.laziocrea.jemoloapp.service.mapper.DichiarazioniMapper;

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
 * Integration tests for the {@link DichiarazioniResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DichiarazioniResourceIT {

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    @Autowired
    private DichiarazioniRepository dichiarazioniRepository;

    @Autowired
    private DichiarazioniMapper dichiarazioniMapper;

    @Autowired
    private DichiarazioniService dichiarazioniService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDichiarazioniMockMvc;

    private Dichiarazioni dichiarazioni;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dichiarazioni createEntity(EntityManager em) {
        Dichiarazioni dichiarazioni = new Dichiarazioni()
            .descrizione(DEFAULT_DESCRIZIONE);
        return dichiarazioni;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dichiarazioni createUpdatedEntity(EntityManager em) {
        Dichiarazioni dichiarazioni = new Dichiarazioni()
            .descrizione(UPDATED_DESCRIZIONE);
        return dichiarazioni;
    }

    @BeforeEach
    public void initTest() {
        dichiarazioni = createEntity(em);
    }

    @Test
    @Transactional
    public void createDichiarazioni() throws Exception {
        int databaseSizeBeforeCreate = dichiarazioniRepository.findAll().size();
        // Create the Dichiarazioni
        DichiarazioniDTO dichiarazioniDTO = dichiarazioniMapper.toDto(dichiarazioni);
        restDichiarazioniMockMvc.perform(post("/api/dichiarazionis").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniDTO)))
            .andExpect(status().isCreated());

        // Validate the Dichiarazioni in the database
        List<Dichiarazioni> dichiarazioniList = dichiarazioniRepository.findAll();
        assertThat(dichiarazioniList).hasSize(databaseSizeBeforeCreate + 1);
        Dichiarazioni testDichiarazioni = dichiarazioniList.get(dichiarazioniList.size() - 1);
        assertThat(testDichiarazioni.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void createDichiarazioniWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dichiarazioniRepository.findAll().size();

        // Create the Dichiarazioni with an existing ID
        dichiarazioni.setId(1L);
        DichiarazioniDTO dichiarazioniDTO = dichiarazioniMapper.toDto(dichiarazioni);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDichiarazioniMockMvc.perform(post("/api/dichiarazionis").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dichiarazioni in the database
        List<Dichiarazioni> dichiarazioniList = dichiarazioniRepository.findAll();
        assertThat(dichiarazioniList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescrizioneIsRequired() throws Exception {
        int databaseSizeBeforeTest = dichiarazioniRepository.findAll().size();
        // set the field null
        dichiarazioni.setDescrizione(null);

        // Create the Dichiarazioni, which fails.
        DichiarazioniDTO dichiarazioniDTO = dichiarazioniMapper.toDto(dichiarazioni);


        restDichiarazioniMockMvc.perform(post("/api/dichiarazionis").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniDTO)))
            .andExpect(status().isBadRequest());

        List<Dichiarazioni> dichiarazioniList = dichiarazioniRepository.findAll();
        assertThat(dichiarazioniList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDichiarazionis() throws Exception {
        // Initialize the database
        dichiarazioniRepository.saveAndFlush(dichiarazioni);

        // Get all the dichiarazioniList
        restDichiarazioniMockMvc.perform(get("/api/dichiarazionis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dichiarazioni.getId().intValue())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE)));
    }
    
    @Test
    @Transactional
    public void getDichiarazioni() throws Exception {
        // Initialize the database
        dichiarazioniRepository.saveAndFlush(dichiarazioni);

        // Get the dichiarazioni
        restDichiarazioniMockMvc.perform(get("/api/dichiarazionis/{id}", dichiarazioni.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dichiarazioni.getId().intValue()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE));
    }
    @Test
    @Transactional
    public void getNonExistingDichiarazioni() throws Exception {
        // Get the dichiarazioni
        restDichiarazioniMockMvc.perform(get("/api/dichiarazionis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDichiarazioni() throws Exception {
        // Initialize the database
        dichiarazioniRepository.saveAndFlush(dichiarazioni);

        int databaseSizeBeforeUpdate = dichiarazioniRepository.findAll().size();

        // Update the dichiarazioni
        Dichiarazioni updatedDichiarazioni = dichiarazioniRepository.findById(dichiarazioni.getId()).get();
        // Disconnect from session so that the updates on updatedDichiarazioni are not directly saved in db
        em.detach(updatedDichiarazioni);
        updatedDichiarazioni
            .descrizione(UPDATED_DESCRIZIONE);
        DichiarazioniDTO dichiarazioniDTO = dichiarazioniMapper.toDto(updatedDichiarazioni);

        restDichiarazioniMockMvc.perform(put("/api/dichiarazionis").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniDTO)))
            .andExpect(status().isOk());

        // Validate the Dichiarazioni in the database
        List<Dichiarazioni> dichiarazioniList = dichiarazioniRepository.findAll();
        assertThat(dichiarazioniList).hasSize(databaseSizeBeforeUpdate);
        Dichiarazioni testDichiarazioni = dichiarazioniList.get(dichiarazioniList.size() - 1);
        assertThat(testDichiarazioni.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingDichiarazioni() throws Exception {
        int databaseSizeBeforeUpdate = dichiarazioniRepository.findAll().size();

        // Create the Dichiarazioni
        DichiarazioniDTO dichiarazioniDTO = dichiarazioniMapper.toDto(dichiarazioni);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDichiarazioniMockMvc.perform(put("/api/dichiarazionis").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dichiarazioniDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dichiarazioni in the database
        List<Dichiarazioni> dichiarazioniList = dichiarazioniRepository.findAll();
        assertThat(dichiarazioniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDichiarazioni() throws Exception {
        // Initialize the database
        dichiarazioniRepository.saveAndFlush(dichiarazioni);

        int databaseSizeBeforeDelete = dichiarazioniRepository.findAll().size();

        // Delete the dichiarazioni
        restDichiarazioniMockMvc.perform(delete("/api/dichiarazionis/{id}", dichiarazioni.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dichiarazioni> dichiarazioniList = dichiarazioniRepository.findAll();
        assertThat(dichiarazioniList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
