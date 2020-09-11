package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.Candidato;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.domain.StatoRegistrazione;
import it.laziocrea.jemoloapp.repository.CandidatoRepository;
import it.laziocrea.jemoloapp.service.CandidatoService;
import it.laziocrea.jemoloapp.service.dto.CandidatoDTO;
import it.laziocrea.jemoloapp.service.mapper.CandidatoMapper;
import it.laziocrea.jemoloapp.web.rest.errors.ExceptionTranslator;
import it.laziocrea.jemoloapp.service.dto.CandidatoCriteria;
import it.laziocrea.jemoloapp.service.CandidatoQueryService;

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
 * Integration tests for the {@link CandidatoResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
public class CandidatoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_FISCALE = "cmNwEX68H18H188o";
    private static final String UPDATED_CODICE_FISCALE = "RZwaAv32S72Y644N";

    private static final String DEFAULT_E_MAIL = "0aFt3@JCtShuy";
    private static final String UPDATED_E_MAIL = "Xb]jx@ztz\\";

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private CandidatoMapper candidatoMapper;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private CandidatoQueryService candidatoQueryService;

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

    private MockMvc restCandidatoMockMvc;

    private Candidato candidato;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CandidatoResource candidatoResource = new CandidatoResource(candidatoService, candidatoQueryService);
        this.restCandidatoMockMvc = MockMvcBuilders.standaloneSetup(candidatoResource)
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
    public static Candidato createEntity(EntityManager em) {
        Candidato candidato = new Candidato()
            .nome(DEFAULT_NOME)
            .cognome(DEFAULT_COGNOME)
            .codiceFiscale(DEFAULT_CODICE_FISCALE)
            .eMail(DEFAULT_E_MAIL);
        return candidato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidato createUpdatedEntity(EntityManager em) {
        Candidato candidato = new Candidato()
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .eMail(UPDATED_E_MAIL);
        return candidato;
    }

    @BeforeEach
    public void initTest() {
        candidato = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidato() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();

        // Create the Candidato
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isCreated());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate + 1);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCandidato.getCognome()).isEqualTo(DEFAULT_COGNOME);
        assertThat(testCandidato.getCodiceFiscale()).isEqualTo(DEFAULT_CODICE_FISCALE);
        assertThat(testCandidato.geteMail()).isEqualTo(DEFAULT_E_MAIL);
    }

    @Test
    @Transactional
    public void createCandidatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();

        // Create the Candidato with an existing ID
        candidato.setId(1L);
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidatoRepository.findAll().size();
        // set the field null
        candidato.setNome(null);

        // Create the Candidato, which fails.
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCognomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidatoRepository.findAll().size();
        // set the field null
        candidato.setCognome(null);

        // Create the Candidato, which fails.
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodiceFiscaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidatoRepository.findAll().size();
        // set the field null
        candidato.setCodiceFiscale(null);

        // Create the Candidato, which fails.
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkeMailIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidatoRepository.findAll().size();
        // set the field null
        candidato.seteMail(null);

        // Create the Candidato, which fails.
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCandidatoes() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList
        restCandidatoMockMvc.perform(get("/api/candidatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidato.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE)))
            .andExpect(jsonPath("$.[*].eMail").value(hasItem(DEFAULT_E_MAIL)));
    }
    
    @Test
    @Transactional
    public void getCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", candidato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(candidato.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME))
            .andExpect(jsonPath("$.codiceFiscale").value(DEFAULT_CODICE_FISCALE))
            .andExpect(jsonPath("$.eMail").value(DEFAULT_E_MAIL));
    }


    @Test
    @Transactional
    public void getCandidatoesByIdFiltering() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        Long id = candidato.getId();

        defaultCandidatoShouldBeFound("id.equals=" + id);
        defaultCandidatoShouldNotBeFound("id.notEquals=" + id);

        defaultCandidatoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCandidatoShouldNotBeFound("id.greaterThan=" + id);

        defaultCandidatoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCandidatoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCandidatoesByNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where nome equals to DEFAULT_NOME
        defaultCandidatoShouldBeFound("nome.equals=" + DEFAULT_NOME);

        // Get all the candidatoList where nome equals to UPDATED_NOME
        defaultCandidatoShouldNotBeFound("nome.equals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where nome not equals to DEFAULT_NOME
        defaultCandidatoShouldNotBeFound("nome.notEquals=" + DEFAULT_NOME);

        // Get all the candidatoList where nome not equals to UPDATED_NOME
        defaultCandidatoShouldBeFound("nome.notEquals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByNomeIsInShouldWork() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where nome in DEFAULT_NOME or UPDATED_NOME
        defaultCandidatoShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);

        // Get all the candidatoList where nome equals to UPDATED_NOME
        defaultCandidatoShouldNotBeFound("nome.in=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where nome is not null
        defaultCandidatoShouldBeFound("nome.specified=true");

        // Get all the candidatoList where nome is null
        defaultCandidatoShouldNotBeFound("nome.specified=false");
    }
                @Test
    @Transactional
    public void getAllCandidatoesByNomeContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where nome contains DEFAULT_NOME
        defaultCandidatoShouldBeFound("nome.contains=" + DEFAULT_NOME);

        // Get all the candidatoList where nome contains UPDATED_NOME
        defaultCandidatoShouldNotBeFound("nome.contains=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByNomeNotContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where nome does not contain DEFAULT_NOME
        defaultCandidatoShouldNotBeFound("nome.doesNotContain=" + DEFAULT_NOME);

        // Get all the candidatoList where nome does not contain UPDATED_NOME
        defaultCandidatoShouldBeFound("nome.doesNotContain=" + UPDATED_NOME);
    }


    @Test
    @Transactional
    public void getAllCandidatoesByCognomeIsEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where cognome equals to DEFAULT_COGNOME
        defaultCandidatoShouldBeFound("cognome.equals=" + DEFAULT_COGNOME);

        // Get all the candidatoList where cognome equals to UPDATED_COGNOME
        defaultCandidatoShouldNotBeFound("cognome.equals=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCognomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where cognome not equals to DEFAULT_COGNOME
        defaultCandidatoShouldNotBeFound("cognome.notEquals=" + DEFAULT_COGNOME);

        // Get all the candidatoList where cognome not equals to UPDATED_COGNOME
        defaultCandidatoShouldBeFound("cognome.notEquals=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCognomeIsInShouldWork() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where cognome in DEFAULT_COGNOME or UPDATED_COGNOME
        defaultCandidatoShouldBeFound("cognome.in=" + DEFAULT_COGNOME + "," + UPDATED_COGNOME);

        // Get all the candidatoList where cognome equals to UPDATED_COGNOME
        defaultCandidatoShouldNotBeFound("cognome.in=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCognomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where cognome is not null
        defaultCandidatoShouldBeFound("cognome.specified=true");

        // Get all the candidatoList where cognome is null
        defaultCandidatoShouldNotBeFound("cognome.specified=false");
    }
                @Test
    @Transactional
    public void getAllCandidatoesByCognomeContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where cognome contains DEFAULT_COGNOME
        defaultCandidatoShouldBeFound("cognome.contains=" + DEFAULT_COGNOME);

        // Get all the candidatoList where cognome contains UPDATED_COGNOME
        defaultCandidatoShouldNotBeFound("cognome.contains=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCognomeNotContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where cognome does not contain DEFAULT_COGNOME
        defaultCandidatoShouldNotBeFound("cognome.doesNotContain=" + DEFAULT_COGNOME);

        // Get all the candidatoList where cognome does not contain UPDATED_COGNOME
        defaultCandidatoShouldBeFound("cognome.doesNotContain=" + UPDATED_COGNOME);
    }


    @Test
    @Transactional
    public void getAllCandidatoesByCodiceFiscaleIsEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where codiceFiscale equals to DEFAULT_CODICE_FISCALE
        defaultCandidatoShouldBeFound("codiceFiscale.equals=" + DEFAULT_CODICE_FISCALE);

        // Get all the candidatoList where codiceFiscale equals to UPDATED_CODICE_FISCALE
        defaultCandidatoShouldNotBeFound("codiceFiscale.equals=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCodiceFiscaleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where codiceFiscale not equals to DEFAULT_CODICE_FISCALE
        defaultCandidatoShouldNotBeFound("codiceFiscale.notEquals=" + DEFAULT_CODICE_FISCALE);

        // Get all the candidatoList where codiceFiscale not equals to UPDATED_CODICE_FISCALE
        defaultCandidatoShouldBeFound("codiceFiscale.notEquals=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCodiceFiscaleIsInShouldWork() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where codiceFiscale in DEFAULT_CODICE_FISCALE or UPDATED_CODICE_FISCALE
        defaultCandidatoShouldBeFound("codiceFiscale.in=" + DEFAULT_CODICE_FISCALE + "," + UPDATED_CODICE_FISCALE);

        // Get all the candidatoList where codiceFiscale equals to UPDATED_CODICE_FISCALE
        defaultCandidatoShouldNotBeFound("codiceFiscale.in=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCodiceFiscaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where codiceFiscale is not null
        defaultCandidatoShouldBeFound("codiceFiscale.specified=true");

        // Get all the candidatoList where codiceFiscale is null
        defaultCandidatoShouldNotBeFound("codiceFiscale.specified=false");
    }
                @Test
    @Transactional
    public void getAllCandidatoesByCodiceFiscaleContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where codiceFiscale contains DEFAULT_CODICE_FISCALE
        defaultCandidatoShouldBeFound("codiceFiscale.contains=" + DEFAULT_CODICE_FISCALE);

        // Get all the candidatoList where codiceFiscale contains UPDATED_CODICE_FISCALE
        defaultCandidatoShouldNotBeFound("codiceFiscale.contains=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByCodiceFiscaleNotContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where codiceFiscale does not contain DEFAULT_CODICE_FISCALE
        defaultCandidatoShouldNotBeFound("codiceFiscale.doesNotContain=" + DEFAULT_CODICE_FISCALE);

        // Get all the candidatoList where codiceFiscale does not contain UPDATED_CODICE_FISCALE
        defaultCandidatoShouldBeFound("codiceFiscale.doesNotContain=" + UPDATED_CODICE_FISCALE);
    }


    @Test
    @Transactional
    public void getAllCandidatoesByeMailIsEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where eMail equals to DEFAULT_E_MAIL
        defaultCandidatoShouldBeFound("eMail.equals=" + DEFAULT_E_MAIL);

        // Get all the candidatoList where eMail equals to UPDATED_E_MAIL
        defaultCandidatoShouldNotBeFound("eMail.equals=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByeMailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where eMail not equals to DEFAULT_E_MAIL
        defaultCandidatoShouldNotBeFound("eMail.notEquals=" + DEFAULT_E_MAIL);

        // Get all the candidatoList where eMail not equals to UPDATED_E_MAIL
        defaultCandidatoShouldBeFound("eMail.notEquals=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByeMailIsInShouldWork() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where eMail in DEFAULT_E_MAIL or UPDATED_E_MAIL
        defaultCandidatoShouldBeFound("eMail.in=" + DEFAULT_E_MAIL + "," + UPDATED_E_MAIL);

        // Get all the candidatoList where eMail equals to UPDATED_E_MAIL
        defaultCandidatoShouldNotBeFound("eMail.in=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByeMailIsNullOrNotNull() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where eMail is not null
        defaultCandidatoShouldBeFound("eMail.specified=true");

        // Get all the candidatoList where eMail is null
        defaultCandidatoShouldNotBeFound("eMail.specified=false");
    }
                @Test
    @Transactional
    public void getAllCandidatoesByeMailContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where eMail contains DEFAULT_E_MAIL
        defaultCandidatoShouldBeFound("eMail.contains=" + DEFAULT_E_MAIL);

        // Get all the candidatoList where eMail contains UPDATED_E_MAIL
        defaultCandidatoShouldNotBeFound("eMail.contains=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllCandidatoesByeMailNotContainsSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList where eMail does not contain DEFAULT_E_MAIL
        defaultCandidatoShouldNotBeFound("eMail.doesNotContain=" + DEFAULT_E_MAIL);

        // Get all the candidatoList where eMail does not contain UPDATED_E_MAIL
        defaultCandidatoShouldBeFound("eMail.doesNotContain=" + UPDATED_E_MAIL);
    }


    @Test
    @Transactional
    public void getAllCandidatoesByAnagraficaCandidatoIsEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);
        AnagraficaCandidato anagraficaCandidato = AnagraficaCandidatoResourceIT.createEntity(em);
        em.persist(anagraficaCandidato);
        em.flush();
        candidato.setAnagraficaCandidato(anagraficaCandidato);
        anagraficaCandidato.setCandidato(candidato);
        candidatoRepository.saveAndFlush(candidato);
        Long anagraficaCandidatoId = anagraficaCandidato.getId();

        // Get all the candidatoList where anagraficaCandidato equals to anagraficaCandidatoId
        defaultCandidatoShouldBeFound("anagraficaCandidatoId.equals=" + anagraficaCandidatoId);

        // Get all the candidatoList where anagraficaCandidato equals to anagraficaCandidatoId + 1
        defaultCandidatoShouldNotBeFound("anagraficaCandidatoId.equals=" + (anagraficaCandidatoId + 1));
    }


    @Test
    @Transactional
    public void getAllCandidatoesByStatoRegistrazioneIsEqualToSomething() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);
        StatoRegistrazione statoRegistrazione = StatoRegistrazioneResourceIT.createEntity(em);
        em.persist(statoRegistrazione);
        em.flush();
        candidato.setStatoRegistrazione(statoRegistrazione);
        candidatoRepository.saveAndFlush(candidato);
        Long statoRegistrazioneId = statoRegistrazione.getId();

        // Get all the candidatoList where statoRegistrazione equals to statoRegistrazioneId
        defaultCandidatoShouldBeFound("statoRegistrazioneId.equals=" + statoRegistrazioneId);

        // Get all the candidatoList where statoRegistrazione equals to statoRegistrazioneId + 1
        defaultCandidatoShouldNotBeFound("statoRegistrazioneId.equals=" + (statoRegistrazioneId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCandidatoShouldBeFound(String filter) throws Exception {
        restCandidatoMockMvc.perform(get("/api/candidatoes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidato.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE)))
            .andExpect(jsonPath("$.[*].eMail").value(hasItem(DEFAULT_E_MAIL)));

        // Check, that the count call also returns 1
        restCandidatoMockMvc.perform(get("/api/candidatoes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCandidatoShouldNotBeFound(String filter) throws Exception {
        restCandidatoMockMvc.perform(get("/api/candidatoes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCandidatoMockMvc.perform(get("/api/candidatoes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCandidato() throws Exception {
        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // Update the candidato
        Candidato updatedCandidato = candidatoRepository.findById(candidato.getId()).get();
        // Disconnect from session so that the updates on updatedCandidato are not directly saved in db
        em.detach(updatedCandidato);
        updatedCandidato
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .eMail(UPDATED_E_MAIL);
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(updatedCandidato);

        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isOk());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCandidato.getCognome()).isEqualTo(UPDATED_COGNOME);
        assertThat(testCandidato.getCodiceFiscale()).isEqualTo(UPDATED_CODICE_FISCALE);
        assertThat(testCandidato.geteMail()).isEqualTo(UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidato() throws Exception {
        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // Create the Candidato
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        int databaseSizeBeforeDelete = candidatoRepository.findAll().size();

        // Delete the candidato
        restCandidatoMockMvc.perform(delete("/api/candidatoes/{id}", candidato.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
