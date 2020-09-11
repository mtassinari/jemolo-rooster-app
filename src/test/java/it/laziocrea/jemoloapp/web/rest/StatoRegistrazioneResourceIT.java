package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.StatoRegistrazione;
import it.laziocrea.jemoloapp.repository.StatoRegistrazioneRepository;
import it.laziocrea.jemoloapp.service.StatoRegistrazioneService;
import it.laziocrea.jemoloapp.service.dto.StatoRegistrazioneDTO;
import it.laziocrea.jemoloapp.service.mapper.StatoRegistrazioneMapper;

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
 * Integration tests for the {@link StatoRegistrazioneResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StatoRegistrazioneResourceIT {

    private static final String DEFAULT_STATO = "AAAAAAAAAA";
    private static final String UPDATED_STATO = "BBBBBBBBBB";

    @Autowired
    private StatoRegistrazioneRepository statoRegistrazioneRepository;

    @Autowired
    private StatoRegistrazioneMapper statoRegistrazioneMapper;

    @Autowired
    private StatoRegistrazioneService statoRegistrazioneService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatoRegistrazioneMockMvc;

    private StatoRegistrazione statoRegistrazione;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatoRegistrazione createEntity(EntityManager em) {
        StatoRegistrazione statoRegistrazione = new StatoRegistrazione()
            .stato(DEFAULT_STATO);
        return statoRegistrazione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatoRegistrazione createUpdatedEntity(EntityManager em) {
        StatoRegistrazione statoRegistrazione = new StatoRegistrazione()
            .stato(UPDATED_STATO);
        return statoRegistrazione;
    }

    @BeforeEach
    public void initTest() {
        statoRegistrazione = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatoRegistrazione() throws Exception {
        int databaseSizeBeforeCreate = statoRegistrazioneRepository.findAll().size();
        // Create the StatoRegistrazione
        StatoRegistrazioneDTO statoRegistrazioneDTO = statoRegistrazioneMapper.toDto(statoRegistrazione);
        restStatoRegistrazioneMockMvc.perform(post("/api/stato-registraziones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statoRegistrazioneDTO)))
            .andExpect(status().isCreated());

        // Validate the StatoRegistrazione in the database
        List<StatoRegistrazione> statoRegistrazioneList = statoRegistrazioneRepository.findAll();
        assertThat(statoRegistrazioneList).hasSize(databaseSizeBeforeCreate + 1);
        StatoRegistrazione testStatoRegistrazione = statoRegistrazioneList.get(statoRegistrazioneList.size() - 1);
        assertThat(testStatoRegistrazione.getStato()).isEqualTo(DEFAULT_STATO);
    }

    @Test
    @Transactional
    public void createStatoRegistrazioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statoRegistrazioneRepository.findAll().size();

        // Create the StatoRegistrazione with an existing ID
        statoRegistrazione.setId(1L);
        StatoRegistrazioneDTO statoRegistrazioneDTO = statoRegistrazioneMapper.toDto(statoRegistrazione);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatoRegistrazioneMockMvc.perform(post("/api/stato-registraziones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statoRegistrazioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatoRegistrazione in the database
        List<StatoRegistrazione> statoRegistrazioneList = statoRegistrazioneRepository.findAll();
        assertThat(statoRegistrazioneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatoIsRequired() throws Exception {
        int databaseSizeBeforeTest = statoRegistrazioneRepository.findAll().size();
        // set the field null
        statoRegistrazione.setStato(null);

        // Create the StatoRegistrazione, which fails.
        StatoRegistrazioneDTO statoRegistrazioneDTO = statoRegistrazioneMapper.toDto(statoRegistrazione);


        restStatoRegistrazioneMockMvc.perform(post("/api/stato-registraziones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statoRegistrazioneDTO)))
            .andExpect(status().isBadRequest());

        List<StatoRegistrazione> statoRegistrazioneList = statoRegistrazioneRepository.findAll();
        assertThat(statoRegistrazioneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStatoRegistraziones() throws Exception {
        // Initialize the database
        statoRegistrazioneRepository.saveAndFlush(statoRegistrazione);

        // Get all the statoRegistrazioneList
        restStatoRegistrazioneMockMvc.perform(get("/api/stato-registraziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statoRegistrazione.getId().intValue())))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO)));
    }
    
    @Test
    @Transactional
    public void getStatoRegistrazione() throws Exception {
        // Initialize the database
        statoRegistrazioneRepository.saveAndFlush(statoRegistrazione);

        // Get the statoRegistrazione
        restStatoRegistrazioneMockMvc.perform(get("/api/stato-registraziones/{id}", statoRegistrazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statoRegistrazione.getId().intValue()))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO));
    }
    @Test
    @Transactional
    public void getNonExistingStatoRegistrazione() throws Exception {
        // Get the statoRegistrazione
        restStatoRegistrazioneMockMvc.perform(get("/api/stato-registraziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatoRegistrazione() throws Exception {
        // Initialize the database
        statoRegistrazioneRepository.saveAndFlush(statoRegistrazione);

        int databaseSizeBeforeUpdate = statoRegistrazioneRepository.findAll().size();

        // Update the statoRegistrazione
        StatoRegistrazione updatedStatoRegistrazione = statoRegistrazioneRepository.findById(statoRegistrazione.getId()).get();
        // Disconnect from session so that the updates on updatedStatoRegistrazione are not directly saved in db
        em.detach(updatedStatoRegistrazione);
        updatedStatoRegistrazione
            .stato(UPDATED_STATO);
        StatoRegistrazioneDTO statoRegistrazioneDTO = statoRegistrazioneMapper.toDto(updatedStatoRegistrazione);

        restStatoRegistrazioneMockMvc.perform(put("/api/stato-registraziones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statoRegistrazioneDTO)))
            .andExpect(status().isOk());

        // Validate the StatoRegistrazione in the database
        List<StatoRegistrazione> statoRegistrazioneList = statoRegistrazioneRepository.findAll();
        assertThat(statoRegistrazioneList).hasSize(databaseSizeBeforeUpdate);
        StatoRegistrazione testStatoRegistrazione = statoRegistrazioneList.get(statoRegistrazioneList.size() - 1);
        assertThat(testStatoRegistrazione.getStato()).isEqualTo(UPDATED_STATO);
    }

    @Test
    @Transactional
    public void updateNonExistingStatoRegistrazione() throws Exception {
        int databaseSizeBeforeUpdate = statoRegistrazioneRepository.findAll().size();

        // Create the StatoRegistrazione
        StatoRegistrazioneDTO statoRegistrazioneDTO = statoRegistrazioneMapper.toDto(statoRegistrazione);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatoRegistrazioneMockMvc.perform(put("/api/stato-registraziones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statoRegistrazioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatoRegistrazione in the database
        List<StatoRegistrazione> statoRegistrazioneList = statoRegistrazioneRepository.findAll();
        assertThat(statoRegistrazioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatoRegistrazione() throws Exception {
        // Initialize the database
        statoRegistrazioneRepository.saveAndFlush(statoRegistrazione);

        int databaseSizeBeforeDelete = statoRegistrazioneRepository.findAll().size();

        // Delete the statoRegistrazione
        restStatoRegistrazioneMockMvc.perform(delete("/api/stato-registraziones/{id}", statoRegistrazione.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StatoRegistrazione> statoRegistrazioneList = statoRegistrazioneRepository.findAll();
        assertThat(statoRegistrazioneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
