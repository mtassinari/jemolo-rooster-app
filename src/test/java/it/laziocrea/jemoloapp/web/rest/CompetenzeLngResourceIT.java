package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.CompetenzeLng;
import it.laziocrea.jemoloapp.domain.Lingua;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.repository.CompetenzeLngRepository;
import it.laziocrea.jemoloapp.service.CompetenzeLngService;
import it.laziocrea.jemoloapp.service.dto.CompetenzeLngDTO;
import it.laziocrea.jemoloapp.service.mapper.CompetenzeLngMapper;
import it.laziocrea.jemoloapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static it.laziocrea.jemoloapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CompetenzeLngResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
public class CompetenzeLngResourceIT {

    private static final Integer DEFAULT_LIVELLO = 1;
    private static final Integer UPDATED_LIVELLO = 2;

    @Autowired
    private CompetenzeLngRepository competenzeLngRepository;

    @Autowired
    private CompetenzeLngMapper competenzeLngMapper;

    @Autowired
    private CompetenzeLngService competenzeLngService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCompetenzeLngMockMvc;

    private CompetenzeLng competenzeLng;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompetenzeLngResource competenzeLngResource = new CompetenzeLngResource(competenzeLngService);
        this.restCompetenzeLngMockMvc = MockMvcBuilders.standaloneSetup(competenzeLngResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompetenzeLng createEntity(EntityManager em) {
        CompetenzeLng competenzeLng = new CompetenzeLng()
            .livello(DEFAULT_LIVELLO);
        // Add required entity
        Lingua lingua;
        if (TestUtil.findAll(em, Lingua.class).isEmpty()) {
            lingua = LinguaResourceIT.createEntity(em);
            em.persist(lingua);
            em.flush();
        } else {
            lingua = TestUtil.findAll(em, Lingua.class).get(0);
        }
        competenzeLng.setLingua(lingua);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        competenzeLng.setAnagrafica(anagraficaCandidato);
        return competenzeLng;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompetenzeLng createUpdatedEntity(EntityManager em) {
        CompetenzeLng competenzeLng = new CompetenzeLng()
            .livello(UPDATED_LIVELLO);
        // Add required entity
        Lingua lingua;
        if (TestUtil.findAll(em, Lingua.class).isEmpty()) {
            lingua = LinguaResourceIT.createUpdatedEntity(em);
            em.persist(lingua);
            em.flush();
        } else {
            lingua = TestUtil.findAll(em, Lingua.class).get(0);
        }
        competenzeLng.setLingua(lingua);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createUpdatedEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        competenzeLng.setAnagrafica(anagraficaCandidato);
        return competenzeLng;
    }

    @BeforeEach
    public void initTest() {
        competenzeLng = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompetenzeLng() throws Exception {
        int databaseSizeBeforeCreate = competenzeLngRepository.findAll().size();

        // Create the CompetenzeLng
        CompetenzeLngDTO competenzeLngDTO = competenzeLngMapper.toDto(competenzeLng);
        restCompetenzeLngMockMvc.perform(post("/api/competenze-lngs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzeLngDTO)))
            .andExpect(status().isCreated());

        // Validate the CompetenzeLng in the database
        List<CompetenzeLng> competenzeLngList = competenzeLngRepository.findAll();
        assertThat(competenzeLngList).hasSize(databaseSizeBeforeCreate + 1);
        CompetenzeLng testCompetenzeLng = competenzeLngList.get(competenzeLngList.size() - 1);
        assertThat(testCompetenzeLng.getLivello()).isEqualTo(DEFAULT_LIVELLO);
    }

    @Test
    @Transactional
    public void createCompetenzeLngWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = competenzeLngRepository.findAll().size();

        // Create the CompetenzeLng with an existing ID
        competenzeLng.setId(1L);
        CompetenzeLngDTO competenzeLngDTO = competenzeLngMapper.toDto(competenzeLng);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetenzeLngMockMvc.perform(post("/api/competenze-lngs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzeLngDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompetenzeLng in the database
        List<CompetenzeLng> competenzeLngList = competenzeLngRepository.findAll();
        assertThat(competenzeLngList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLivelloIsRequired() throws Exception {
        int databaseSizeBeforeTest = competenzeLngRepository.findAll().size();
        // set the field null
        competenzeLng.setLivello(null);

        // Create the CompetenzeLng, which fails.
        CompetenzeLngDTO competenzeLngDTO = competenzeLngMapper.toDto(competenzeLng);

        restCompetenzeLngMockMvc.perform(post("/api/competenze-lngs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzeLngDTO)))
            .andExpect(status().isBadRequest());

        List<CompetenzeLng> competenzeLngList = competenzeLngRepository.findAll();
        assertThat(competenzeLngList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompetenzeLngs() throws Exception {
        // Initialize the database
        competenzeLngRepository.saveAndFlush(competenzeLng);

        // Get all the competenzeLngList
        restCompetenzeLngMockMvc.perform(get("/api/competenze-lngs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competenzeLng.getId().intValue())))
            .andExpect(jsonPath("$.[*].livello").value(hasItem(DEFAULT_LIVELLO)));
    }
    
    @Test
    @Transactional
    public void getCompetenzeLng() throws Exception {
        // Initialize the database
        competenzeLngRepository.saveAndFlush(competenzeLng);

        // Get the competenzeLng
        restCompetenzeLngMockMvc.perform(get("/api/competenze-lngs/{id}", competenzeLng.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(competenzeLng.getId().intValue()))
            .andExpect(jsonPath("$.livello").value(DEFAULT_LIVELLO));
    }

    @Test
    @Transactional
    public void getNonExistingCompetenzeLng() throws Exception {
        // Get the competenzeLng
        restCompetenzeLngMockMvc.perform(get("/api/competenze-lngs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompetenzeLng() throws Exception {
        // Initialize the database
        competenzeLngRepository.saveAndFlush(competenzeLng);

        int databaseSizeBeforeUpdate = competenzeLngRepository.findAll().size();

        // Update the competenzeLng
        CompetenzeLng updatedCompetenzeLng = competenzeLngRepository.findById(competenzeLng.getId()).get();
        // Disconnect from session so that the updates on updatedCompetenzeLng are not directly saved in db
        em.detach(updatedCompetenzeLng);
        updatedCompetenzeLng
            .livello(UPDATED_LIVELLO);
        CompetenzeLngDTO competenzeLngDTO = competenzeLngMapper.toDto(updatedCompetenzeLng);

        restCompetenzeLngMockMvc.perform(put("/api/competenze-lngs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzeLngDTO)))
            .andExpect(status().isOk());

        // Validate the CompetenzeLng in the database
        List<CompetenzeLng> competenzeLngList = competenzeLngRepository.findAll();
        assertThat(competenzeLngList).hasSize(databaseSizeBeforeUpdate);
        CompetenzeLng testCompetenzeLng = competenzeLngList.get(competenzeLngList.size() - 1);
        assertThat(testCompetenzeLng.getLivello()).isEqualTo(UPDATED_LIVELLO);
    }

    @Test
    @Transactional
    public void updateNonExistingCompetenzeLng() throws Exception {
        int databaseSizeBeforeUpdate = competenzeLngRepository.findAll().size();

        // Create the CompetenzeLng
        CompetenzeLngDTO competenzeLngDTO = competenzeLngMapper.toDto(competenzeLng);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetenzeLngMockMvc.perform(put("/api/competenze-lngs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competenzeLngDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompetenzeLng in the database
        List<CompetenzeLng> competenzeLngList = competenzeLngRepository.findAll();
        assertThat(competenzeLngList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompetenzeLng() throws Exception {
        // Initialize the database
        competenzeLngRepository.saveAndFlush(competenzeLng);

        int databaseSizeBeforeDelete = competenzeLngRepository.findAll().size();

        // Delete the competenzeLng
        restCompetenzeLngMockMvc.perform(delete("/api/competenze-lngs/{id}", competenzeLng.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CompetenzeLng> competenzeLngList = competenzeLngRepository.findAll();
        assertThat(competenzeLngList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
