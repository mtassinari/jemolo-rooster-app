package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.Curriculum;
import it.laziocrea.jemoloapp.domain.Allegato;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.repository.CurriculumRepository;
import it.laziocrea.jemoloapp.service.CurriculumService;
import it.laziocrea.jemoloapp.service.dto.CurriculumDTO;
import it.laziocrea.jemoloapp.service.mapper.CurriculumMapper;
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
 * Integration tests for the {@link CurriculumResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
public class CurriculumResourceIT {

    private static final String DEFAULT_CV = "AAAAAAAAAA";
    private static final String UPDATED_CV = "BBBBBBBBBB";

    private static final Long DEFAULT_SIZE = 1L;
    private static final Long UPDATED_SIZE = 2L;

    private static final String DEFAULT_URL_ALLEGATO = "AAAAAAAAAA";
    private static final String UPDATED_URL_ALLEGATO = "BBBBBBBBBB";

    private static final String DEFAULT_MIME_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MIME_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Autowired
    private CurriculumService curriculumService;

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

    private MockMvc restCurriculumMockMvc;

    private Curriculum curriculum;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CurriculumResource curriculumResource = new CurriculumResource(curriculumService);
        this.restCurriculumMockMvc = MockMvcBuilders.standaloneSetup(curriculumResource)
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
    public static Curriculum createEntity(EntityManager em) {
        Curriculum curriculum = new Curriculum()
            .cv(DEFAULT_CV)
            .size(DEFAULT_SIZE)
            .urlAllegato(DEFAULT_URL_ALLEGATO)
            .mimeType(DEFAULT_MIME_TYPE)
            .note(DEFAULT_NOTE);
        // Add required entity
        Allegato allegato;
        if (TestUtil.findAll(em, Allegato.class).isEmpty()) {
            allegato = AllegatoResourceIT.createEntity(em);
            em.persist(allegato);
            em.flush();
        } else {
            allegato = TestUtil.findAll(em, Allegato.class).get(0);
        }
        curriculum.setAttach(allegato);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        curriculum.setAnagrafica(anagraficaCandidato);
        return curriculum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Curriculum createUpdatedEntity(EntityManager em) {
        Curriculum curriculum = new Curriculum()
            .cv(UPDATED_CV)
            .size(UPDATED_SIZE)
            .urlAllegato(UPDATED_URL_ALLEGATO)
            .mimeType(UPDATED_MIME_TYPE)
            .note(UPDATED_NOTE);
        // Add required entity
        Allegato allegato;
        if (TestUtil.findAll(em, Allegato.class).isEmpty()) {
            allegato = AllegatoResourceIT.createUpdatedEntity(em);
            em.persist(allegato);
            em.flush();
        } else {
            allegato = TestUtil.findAll(em, Allegato.class).get(0);
        }
        curriculum.setAttach(allegato);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createUpdatedEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        curriculum.setAnagrafica(anagraficaCandidato);
        return curriculum;
    }

    @BeforeEach
    public void initTest() {
        curriculum = createEntity(em);
    }

    @Test
    @Transactional
    public void createCurriculum() throws Exception {
        int databaseSizeBeforeCreate = curriculumRepository.findAll().size();

        // Create the Curriculum
        CurriculumDTO curriculumDTO = curriculumMapper.toDto(curriculum);
        restCurriculumMockMvc.perform(post("/api/curricula")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(curriculumDTO)))
            .andExpect(status().isCreated());

        // Validate the Curriculum in the database
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeCreate + 1);
        Curriculum testCurriculum = curriculumList.get(curriculumList.size() - 1);
        assertThat(testCurriculum.getCv()).isEqualTo(DEFAULT_CV);
        assertThat(testCurriculum.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testCurriculum.getUrlAllegato()).isEqualTo(DEFAULT_URL_ALLEGATO);
        assertThat(testCurriculum.getMimeType()).isEqualTo(DEFAULT_MIME_TYPE);
        assertThat(testCurriculum.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createCurriculumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = curriculumRepository.findAll().size();

        // Create the Curriculum with an existing ID
        curriculum.setId(1L);
        CurriculumDTO curriculumDTO = curriculumMapper.toDto(curriculum);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCurriculumMockMvc.perform(post("/api/curricula")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(curriculumDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Curriculum in the database
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCvIsRequired() throws Exception {
        int databaseSizeBeforeTest = curriculumRepository.findAll().size();
        // set the field null
        curriculum.setCv(null);

        // Create the Curriculum, which fails.
        CurriculumDTO curriculumDTO = curriculumMapper.toDto(curriculum);

        restCurriculumMockMvc.perform(post("/api/curricula")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(curriculumDTO)))
            .andExpect(status().isBadRequest());

        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = curriculumRepository.findAll().size();
        // set the field null
        curriculum.setSize(null);

        // Create the Curriculum, which fails.
        CurriculumDTO curriculumDTO = curriculumMapper.toDto(curriculum);

        restCurriculumMockMvc.perform(post("/api/curricula")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(curriculumDTO)))
            .andExpect(status().isBadRequest());

        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUrlAllegatoIsRequired() throws Exception {
        int databaseSizeBeforeTest = curriculumRepository.findAll().size();
        // set the field null
        curriculum.setUrlAllegato(null);

        // Create the Curriculum, which fails.
        CurriculumDTO curriculumDTO = curriculumMapper.toDto(curriculum);

        restCurriculumMockMvc.perform(post("/api/curricula")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(curriculumDTO)))
            .andExpect(status().isBadRequest());

        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCurricula() throws Exception {
        // Initialize the database
        curriculumRepository.saveAndFlush(curriculum);

        // Get all the curriculumList
        restCurriculumMockMvc.perform(get("/api/curricula?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(curriculum.getId().intValue())))
            .andExpect(jsonPath("$.[*].cv").value(hasItem(DEFAULT_CV)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].urlAllegato").value(hasItem(DEFAULT_URL_ALLEGATO)))
            .andExpect(jsonPath("$.[*].mimeType").value(hasItem(DEFAULT_MIME_TYPE)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }
    
    @Test
    @Transactional
    public void getCurriculum() throws Exception {
        // Initialize the database
        curriculumRepository.saveAndFlush(curriculum);

        // Get the curriculum
        restCurriculumMockMvc.perform(get("/api/curricula/{id}", curriculum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(curriculum.getId().intValue()))
            .andExpect(jsonPath("$.cv").value(DEFAULT_CV))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.intValue()))
            .andExpect(jsonPath("$.urlAllegato").value(DEFAULT_URL_ALLEGATO))
            .andExpect(jsonPath("$.mimeType").value(DEFAULT_MIME_TYPE))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    public void getNonExistingCurriculum() throws Exception {
        // Get the curriculum
        restCurriculumMockMvc.perform(get("/api/curricula/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurriculum() throws Exception {
        // Initialize the database
        curriculumRepository.saveAndFlush(curriculum);

        int databaseSizeBeforeUpdate = curriculumRepository.findAll().size();

        // Update the curriculum
        Curriculum updatedCurriculum = curriculumRepository.findById(curriculum.getId()).get();
        // Disconnect from session so that the updates on updatedCurriculum are not directly saved in db
        em.detach(updatedCurriculum);
        updatedCurriculum
            .cv(UPDATED_CV)
            .size(UPDATED_SIZE)
            .urlAllegato(UPDATED_URL_ALLEGATO)
            .mimeType(UPDATED_MIME_TYPE)
            .note(UPDATED_NOTE);
        CurriculumDTO curriculumDTO = curriculumMapper.toDto(updatedCurriculum);

        restCurriculumMockMvc.perform(put("/api/curricula")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(curriculumDTO)))
            .andExpect(status().isOk());

        // Validate the Curriculum in the database
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeUpdate);
        Curriculum testCurriculum = curriculumList.get(curriculumList.size() - 1);
        assertThat(testCurriculum.getCv()).isEqualTo(UPDATED_CV);
        assertThat(testCurriculum.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testCurriculum.getUrlAllegato()).isEqualTo(UPDATED_URL_ALLEGATO);
        assertThat(testCurriculum.getMimeType()).isEqualTo(UPDATED_MIME_TYPE);
        assertThat(testCurriculum.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingCurriculum() throws Exception {
        int databaseSizeBeforeUpdate = curriculumRepository.findAll().size();

        // Create the Curriculum
        CurriculumDTO curriculumDTO = curriculumMapper.toDto(curriculum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurriculumMockMvc.perform(put("/api/curricula")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(curriculumDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Curriculum in the database
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCurriculum() throws Exception {
        // Initialize the database
        curriculumRepository.saveAndFlush(curriculum);

        int databaseSizeBeforeDelete = curriculumRepository.findAll().size();

        // Delete the curriculum
        restCurriculumMockMvc.perform(delete("/api/curricula/{id}", curriculum.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        assertThat(curriculumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
