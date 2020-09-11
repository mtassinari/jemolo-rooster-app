package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.AmbitoCompetenza;
import it.laziocrea.jemoloapp.repository.AmbitoCompetenzaRepository;
import it.laziocrea.jemoloapp.service.AmbitoCompetenzaService;
import it.laziocrea.jemoloapp.service.dto.AmbitoCompetenzaDTO;
import it.laziocrea.jemoloapp.service.mapper.AmbitoCompetenzaMapper;

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
 * Integration tests for the {@link AmbitoCompetenzaResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AmbitoCompetenzaResourceIT {

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private AmbitoCompetenzaRepository ambitoCompetenzaRepository;

    @Autowired
    private AmbitoCompetenzaMapper ambitoCompetenzaMapper;

    @Autowired
    private AmbitoCompetenzaService ambitoCompetenzaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAmbitoCompetenzaMockMvc;

    private AmbitoCompetenza ambitoCompetenza;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AmbitoCompetenza createEntity(EntityManager em) {
        AmbitoCompetenza ambitoCompetenza = new AmbitoCompetenza()
            .descrizione(DEFAULT_DESCRIZIONE)
            .tipo(DEFAULT_TIPO);
        return ambitoCompetenza;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AmbitoCompetenza createUpdatedEntity(EntityManager em) {
        AmbitoCompetenza ambitoCompetenza = new AmbitoCompetenza()
            .descrizione(UPDATED_DESCRIZIONE)
            .tipo(UPDATED_TIPO);
        return ambitoCompetenza;
    }

    @BeforeEach
    public void initTest() {
        ambitoCompetenza = createEntity(em);
    }

    @Test
    @Transactional
    public void createAmbitoCompetenza() throws Exception {
        int databaseSizeBeforeCreate = ambitoCompetenzaRepository.findAll().size();
        // Create the AmbitoCompetenza
        AmbitoCompetenzaDTO ambitoCompetenzaDTO = ambitoCompetenzaMapper.toDto(ambitoCompetenza);
        restAmbitoCompetenzaMockMvc.perform(post("/api/ambito-competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ambitoCompetenzaDTO)))
            .andExpect(status().isCreated());

        // Validate the AmbitoCompetenza in the database
        List<AmbitoCompetenza> ambitoCompetenzaList = ambitoCompetenzaRepository.findAll();
        assertThat(ambitoCompetenzaList).hasSize(databaseSizeBeforeCreate + 1);
        AmbitoCompetenza testAmbitoCompetenza = ambitoCompetenzaList.get(ambitoCompetenzaList.size() - 1);
        assertThat(testAmbitoCompetenza.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testAmbitoCompetenza.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createAmbitoCompetenzaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ambitoCompetenzaRepository.findAll().size();

        // Create the AmbitoCompetenza with an existing ID
        ambitoCompetenza.setId(1L);
        AmbitoCompetenzaDTO ambitoCompetenzaDTO = ambitoCompetenzaMapper.toDto(ambitoCompetenza);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAmbitoCompetenzaMockMvc.perform(post("/api/ambito-competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ambitoCompetenzaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AmbitoCompetenza in the database
        List<AmbitoCompetenza> ambitoCompetenzaList = ambitoCompetenzaRepository.findAll();
        assertThat(ambitoCompetenzaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescrizioneIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambitoCompetenzaRepository.findAll().size();
        // set the field null
        ambitoCompetenza.setDescrizione(null);

        // Create the AmbitoCompetenza, which fails.
        AmbitoCompetenzaDTO ambitoCompetenzaDTO = ambitoCompetenzaMapper.toDto(ambitoCompetenza);


        restAmbitoCompetenzaMockMvc.perform(post("/api/ambito-competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ambitoCompetenzaDTO)))
            .andExpect(status().isBadRequest());

        List<AmbitoCompetenza> ambitoCompetenzaList = ambitoCompetenzaRepository.findAll();
        assertThat(ambitoCompetenzaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAmbitoCompetenzas() throws Exception {
        // Initialize the database
        ambitoCompetenzaRepository.saveAndFlush(ambitoCompetenza);

        // Get all the ambitoCompetenzaList
        restAmbitoCompetenzaMockMvc.perform(get("/api/ambito-competenzas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ambitoCompetenza.getId().intValue())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
    
    @Test
    @Transactional
    public void getAmbitoCompetenza() throws Exception {
        // Initialize the database
        ambitoCompetenzaRepository.saveAndFlush(ambitoCompetenza);

        // Get the ambitoCompetenza
        restAmbitoCompetenzaMockMvc.perform(get("/api/ambito-competenzas/{id}", ambitoCompetenza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ambitoCompetenza.getId().intValue()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO));
    }
    @Test
    @Transactional
    public void getNonExistingAmbitoCompetenza() throws Exception {
        // Get the ambitoCompetenza
        restAmbitoCompetenzaMockMvc.perform(get("/api/ambito-competenzas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAmbitoCompetenza() throws Exception {
        // Initialize the database
        ambitoCompetenzaRepository.saveAndFlush(ambitoCompetenza);

        int databaseSizeBeforeUpdate = ambitoCompetenzaRepository.findAll().size();

        // Update the ambitoCompetenza
        AmbitoCompetenza updatedAmbitoCompetenza = ambitoCompetenzaRepository.findById(ambitoCompetenza.getId()).get();
        // Disconnect from session so that the updates on updatedAmbitoCompetenza are not directly saved in db
        em.detach(updatedAmbitoCompetenza);
        updatedAmbitoCompetenza
            .descrizione(UPDATED_DESCRIZIONE)
            .tipo(UPDATED_TIPO);
        AmbitoCompetenzaDTO ambitoCompetenzaDTO = ambitoCompetenzaMapper.toDto(updatedAmbitoCompetenza);

        restAmbitoCompetenzaMockMvc.perform(put("/api/ambito-competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ambitoCompetenzaDTO)))
            .andExpect(status().isOk());

        // Validate the AmbitoCompetenza in the database
        List<AmbitoCompetenza> ambitoCompetenzaList = ambitoCompetenzaRepository.findAll();
        assertThat(ambitoCompetenzaList).hasSize(databaseSizeBeforeUpdate);
        AmbitoCompetenza testAmbitoCompetenza = ambitoCompetenzaList.get(ambitoCompetenzaList.size() - 1);
        assertThat(testAmbitoCompetenza.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testAmbitoCompetenza.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingAmbitoCompetenza() throws Exception {
        int databaseSizeBeforeUpdate = ambitoCompetenzaRepository.findAll().size();

        // Create the AmbitoCompetenza
        AmbitoCompetenzaDTO ambitoCompetenzaDTO = ambitoCompetenzaMapper.toDto(ambitoCompetenza);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAmbitoCompetenzaMockMvc.perform(put("/api/ambito-competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ambitoCompetenzaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AmbitoCompetenza in the database
        List<AmbitoCompetenza> ambitoCompetenzaList = ambitoCompetenzaRepository.findAll();
        assertThat(ambitoCompetenzaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAmbitoCompetenza() throws Exception {
        // Initialize the database
        ambitoCompetenzaRepository.saveAndFlush(ambitoCompetenza);

        int databaseSizeBeforeDelete = ambitoCompetenzaRepository.findAll().size();

        // Delete the ambitoCompetenza
        restAmbitoCompetenzaMockMvc.perform(delete("/api/ambito-competenzas/{id}", ambitoCompetenza.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AmbitoCompetenza> ambitoCompetenzaList = ambitoCompetenzaRepository.findAll();
        assertThat(ambitoCompetenzaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
