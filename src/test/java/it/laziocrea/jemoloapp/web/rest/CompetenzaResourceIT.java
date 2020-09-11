package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.Competenza;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.domain.AmbitoCompetenza;
import it.laziocrea.jemoloapp.repository.CompetenzaRepository;
import it.laziocrea.jemoloapp.service.CompetenzaService;
import it.laziocrea.jemoloapp.service.dto.CompetenzaDTO;
import it.laziocrea.jemoloapp.service.mapper.CompetenzaMapper;

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
 * Integration tests for the {@link CompetenzaResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CompetenzaResourceIT {

    private static final Integer DEFAULT_ANNI = 1;
    private static final Integer UPDATED_ANNI = 2;

    @Autowired
    private CompetenzaRepository competenzaRepository;

    @Autowired
    private CompetenzaMapper competenzaMapper;

    @Autowired
    private CompetenzaService competenzaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompetenzaMockMvc;

    private Competenza competenza;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Competenza createEntity(EntityManager em) {
        Competenza competenza = new Competenza()
            .anni(DEFAULT_ANNI);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        competenza.setAnagrafica(anagraficaCandidato);
        // Add required entity
        AmbitoCompetenza ambitoCompetenza;
        if (TestUtil.findAll(em, AmbitoCompetenza.class).isEmpty()) {
            ambitoCompetenza = AmbitoCompetenzaResourceIT.createEntity(em);
            em.persist(ambitoCompetenza);
            em.flush();
        } else {
            ambitoCompetenza = TestUtil.findAll(em, AmbitoCompetenza.class).get(0);
        }
        competenza.setAmbitoComp(ambitoCompetenza);
        return competenza;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Competenza createUpdatedEntity(EntityManager em) {
        Competenza competenza = new Competenza()
            .anni(UPDATED_ANNI);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createUpdatedEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        competenza.setAnagrafica(anagraficaCandidato);
        // Add required entity
        AmbitoCompetenza ambitoCompetenza;
        if (TestUtil.findAll(em, AmbitoCompetenza.class).isEmpty()) {
            ambitoCompetenza = AmbitoCompetenzaResourceIT.createUpdatedEntity(em);
            em.persist(ambitoCompetenza);
            em.flush();
        } else {
            ambitoCompetenza = TestUtil.findAll(em, AmbitoCompetenza.class).get(0);
        }
        competenza.setAmbitoComp(ambitoCompetenza);
        return competenza;
    }

    @BeforeEach
    public void initTest() {
        competenza = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompetenza() throws Exception {
        int databaseSizeBeforeCreate = competenzaRepository.findAll().size();
        // Create the Competenza
        CompetenzaDTO competenzaDTO = competenzaMapper.toDto(competenza);
        restCompetenzaMockMvc.perform(post("/api/competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzaDTO)))
            .andExpect(status().isCreated());

        // Validate the Competenza in the database
        List<Competenza> competenzaList = competenzaRepository.findAll();
        assertThat(competenzaList).hasSize(databaseSizeBeforeCreate + 1);
        Competenza testCompetenza = competenzaList.get(competenzaList.size() - 1);
        assertThat(testCompetenza.getAnni()).isEqualTo(DEFAULT_ANNI);
    }

    @Test
    @Transactional
    public void createCompetenzaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = competenzaRepository.findAll().size();

        // Create the Competenza with an existing ID
        competenza.setId(1L);
        CompetenzaDTO competenzaDTO = competenzaMapper.toDto(competenza);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetenzaMockMvc.perform(post("/api/competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Competenza in the database
        List<Competenza> competenzaList = competenzaRepository.findAll();
        assertThat(competenzaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAnniIsRequired() throws Exception {
        int databaseSizeBeforeTest = competenzaRepository.findAll().size();
        // set the field null
        competenza.setAnni(null);

        // Create the Competenza, which fails.
        CompetenzaDTO competenzaDTO = competenzaMapper.toDto(competenza);


        restCompetenzaMockMvc.perform(post("/api/competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzaDTO)))
            .andExpect(status().isBadRequest());

        List<Competenza> competenzaList = competenzaRepository.findAll();
        assertThat(competenzaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompetenzas() throws Exception {
        // Initialize the database
        competenzaRepository.saveAndFlush(competenza);

        // Get all the competenzaList
        restCompetenzaMockMvc.perform(get("/api/competenzas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competenza.getId().intValue())))
            .andExpect(jsonPath("$.[*].anni").value(hasItem(DEFAULT_ANNI)));
    }
    
    @Test
    @Transactional
    public void getCompetenza() throws Exception {
        // Initialize the database
        competenzaRepository.saveAndFlush(competenza);

        // Get the competenza
        restCompetenzaMockMvc.perform(get("/api/competenzas/{id}", competenza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(competenza.getId().intValue()))
            .andExpect(jsonPath("$.anni").value(DEFAULT_ANNI));
    }
    @Test
    @Transactional
    public void getNonExistingCompetenza() throws Exception {
        // Get the competenza
        restCompetenzaMockMvc.perform(get("/api/competenzas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompetenza() throws Exception {
        // Initialize the database
        competenzaRepository.saveAndFlush(competenza);

        int databaseSizeBeforeUpdate = competenzaRepository.findAll().size();

        // Update the competenza
        Competenza updatedCompetenza = competenzaRepository.findById(competenza.getId()).get();
        // Disconnect from session so that the updates on updatedCompetenza are not directly saved in db
        em.detach(updatedCompetenza);
        updatedCompetenza
            .anni(UPDATED_ANNI);
        CompetenzaDTO competenzaDTO = competenzaMapper.toDto(updatedCompetenza);

        restCompetenzaMockMvc.perform(put("/api/competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzaDTO)))
            .andExpect(status().isOk());

        // Validate the Competenza in the database
        List<Competenza> competenzaList = competenzaRepository.findAll();
        assertThat(competenzaList).hasSize(databaseSizeBeforeUpdate);
        Competenza testCompetenza = competenzaList.get(competenzaList.size() - 1);
        assertThat(testCompetenza.getAnni()).isEqualTo(UPDATED_ANNI);
    }

    @Test
    @Transactional
    public void updateNonExistingCompetenza() throws Exception {
        int databaseSizeBeforeUpdate = competenzaRepository.findAll().size();

        // Create the Competenza
        CompetenzaDTO competenzaDTO = competenzaMapper.toDto(competenza);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetenzaMockMvc.perform(put("/api/competenzas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Competenza in the database
        List<Competenza> competenzaList = competenzaRepository.findAll();
        assertThat(competenzaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompetenza() throws Exception {
        // Initialize the database
        competenzaRepository.saveAndFlush(competenza);

        int databaseSizeBeforeDelete = competenzaRepository.findAll().size();

        // Delete the competenza
        restCompetenzaMockMvc.perform(delete("/api/competenzas/{id}", competenza.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Competenza> competenzaList = competenzaRepository.findAll();
        assertThat(competenzaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
