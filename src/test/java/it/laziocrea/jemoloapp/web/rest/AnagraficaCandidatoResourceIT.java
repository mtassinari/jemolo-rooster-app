package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.domain.Candidato;
import it.laziocrea.jemoloapp.repository.AnagraficaCandidatoRepository;
import it.laziocrea.jemoloapp.service.AnagraficaCandidatoService;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoDTO;
import it.laziocrea.jemoloapp.service.mapper.AnagraficaCandidatoMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static it.laziocrea.jemoloapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AnagraficaCandidatoResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
public class AnagraficaCandidatoResourceIT {

    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_LUOGO_NASCITA = "AAAAAAAAAA";
    private static final String UPDATED_LUOGO_NASCITA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_NASCITA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCITA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CODICE_FISCALE = "TZBuDL72R92S163g";
    private static final String UPDATED_CODICE_FISCALE = "NWlVlX32P31p554R";

    private static final String DEFAULT_PROFESSIONE = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSIONE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTITA_IVA = "25134662475";
    private static final String UPDATED_PARTITA_IVA = "00585901261";

    private static final String DEFAULT_NUMERO_TELEFONO_FISSO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_TELEFONO_FISSO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_TELEFONO_CELLULARE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_TELEFONO_CELLULARE = "BBBBBBBBBB";

    private static final String DEFAULT_E_MAIL = "X[2@IT9VO[";
    private static final String UPDATED_E_MAIL = "mBl^a@04KE";

    private static final String DEFAULT_INDIRIZZO_PEC = "kqV@CMr]HqkxC";
    private static final String UPDATED_INDIRIZZO_PEC = "S`@B9Oknm";

    private static final String DEFAULT_INDIRIZZO_RESIDENZA = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO_RESIDENZA = "BBBBBBBBBB";

    private static final String DEFAULT_CAP_RESIDENZA = "AAAAAAAAAA";
    private static final String UPDATED_CAP_RESIDENZA = "BBBBBBBBBB";

    private static final String DEFAULT_COMUNE_RESIDENZA = "AAAAAAAAAA";
    private static final String UPDATED_COMUNE_RESIDENZA = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIA_RESIDENZA = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA_RESIDENZA = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private AnagraficaCandidatoRepository anagraficaCandidatoRepository;

    @Autowired
    private AnagraficaCandidatoMapper anagraficaCandidatoMapper;

    @Autowired
    private AnagraficaCandidatoService anagraficaCandidatoService;

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

    private MockMvc restAnagraficaCandidatoMockMvc;

    private AnagraficaCandidato anagraficaCandidato;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnagraficaCandidatoResource anagraficaCandidatoResource = new AnagraficaCandidatoResource(anagraficaCandidatoService);
        this.restAnagraficaCandidatoMockMvc = MockMvcBuilders.standaloneSetup(anagraficaCandidatoResource)
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
    public static AnagraficaCandidato createEntity(EntityManager em) {
        AnagraficaCandidato anagraficaCandidato = new AnagraficaCandidato()
            .cognome(DEFAULT_COGNOME)
            .nome(DEFAULT_NOME)
            .luogoNascita(DEFAULT_LUOGO_NASCITA)
            .dataNascita(DEFAULT_DATA_NASCITA)
            .codiceFiscale(DEFAULT_CODICE_FISCALE)
            .professione(DEFAULT_PROFESSIONE)
            .partitaIva(DEFAULT_PARTITA_IVA)
            .numeroTelefonoFisso(DEFAULT_NUMERO_TELEFONO_FISSO)
            .numeroTelefonoCellulare(DEFAULT_NUMERO_TELEFONO_CELLULARE)
            .eMail(DEFAULT_E_MAIL)
            .indirizzoPec(DEFAULT_INDIRIZZO_PEC)
            .indirizzoResidenza(DEFAULT_INDIRIZZO_RESIDENZA)
            .capResidenza(DEFAULT_CAP_RESIDENZA)
            .comuneResidenza(DEFAULT_COMUNE_RESIDENZA)
            .provinciaResidenza(DEFAULT_PROVINCIA_RESIDENZA)
            .note(DEFAULT_NOTE);
        // Add required entity
        Candidato candidato;
        if (TestUtil.findAll(em, Candidato.class).isEmpty()) {
            candidato = CandidatoResourceIT.createEntity(em);
            em.persist(candidato);
            em.flush();
        } else {
            candidato = TestUtil.findAll(em, Candidato.class).get(0);
        }
        anagraficaCandidato.setCandidato(candidato);
        return anagraficaCandidato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnagraficaCandidato createUpdatedEntity(EntityManager em) {
        AnagraficaCandidato anagraficaCandidato = new AnagraficaCandidato()
            .cognome(UPDATED_COGNOME)
            .nome(UPDATED_NOME)
            .luogoNascita(UPDATED_LUOGO_NASCITA)
            .dataNascita(UPDATED_DATA_NASCITA)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .professione(UPDATED_PROFESSIONE)
            .partitaIva(UPDATED_PARTITA_IVA)
            .numeroTelefonoFisso(UPDATED_NUMERO_TELEFONO_FISSO)
            .numeroTelefonoCellulare(UPDATED_NUMERO_TELEFONO_CELLULARE)
            .eMail(UPDATED_E_MAIL)
            .indirizzoPec(UPDATED_INDIRIZZO_PEC)
            .indirizzoResidenza(UPDATED_INDIRIZZO_RESIDENZA)
            .capResidenza(UPDATED_CAP_RESIDENZA)
            .comuneResidenza(UPDATED_COMUNE_RESIDENZA)
            .provinciaResidenza(UPDATED_PROVINCIA_RESIDENZA)
            .note(UPDATED_NOTE);
        // Add required entity
        Candidato candidato;
        if (TestUtil.findAll(em, Candidato.class).isEmpty()) {
            candidato = CandidatoResourceIT.createUpdatedEntity(em);
            em.persist(candidato);
            em.flush();
        } else {
            candidato = TestUtil.findAll(em, Candidato.class).get(0);
        }
        anagraficaCandidato.setCandidato(candidato);
        return anagraficaCandidato;
    }

    @BeforeEach
    public void initTest() {
        anagraficaCandidato = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnagraficaCandidato() throws Exception {
        int databaseSizeBeforeCreate = anagraficaCandidatoRepository.findAll().size();

        // Create the AnagraficaCandidato
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);
        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isCreated());

        // Validate the AnagraficaCandidato in the database
        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeCreate + 1);
        AnagraficaCandidato testAnagraficaCandidato = anagraficaCandidatoList.get(anagraficaCandidatoList.size() - 1);
        assertThat(testAnagraficaCandidato.getCognome()).isEqualTo(DEFAULT_COGNOME);
        assertThat(testAnagraficaCandidato.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAnagraficaCandidato.getLuogoNascita()).isEqualTo(DEFAULT_LUOGO_NASCITA);
        assertThat(testAnagraficaCandidato.getDataNascita()).isEqualTo(DEFAULT_DATA_NASCITA);
        assertThat(testAnagraficaCandidato.getCodiceFiscale()).isEqualTo(DEFAULT_CODICE_FISCALE);
        assertThat(testAnagraficaCandidato.getProfessione()).isEqualTo(DEFAULT_PROFESSIONE);
        assertThat(testAnagraficaCandidato.getPartitaIva()).isEqualTo(DEFAULT_PARTITA_IVA);
        assertThat(testAnagraficaCandidato.getNumeroTelefonoFisso()).isEqualTo(DEFAULT_NUMERO_TELEFONO_FISSO);
        assertThat(testAnagraficaCandidato.getNumeroTelefonoCellulare()).isEqualTo(DEFAULT_NUMERO_TELEFONO_CELLULARE);
        assertThat(testAnagraficaCandidato.geteMail()).isEqualTo(DEFAULT_E_MAIL);
        assertThat(testAnagraficaCandidato.getIndirizzoPec()).isEqualTo(DEFAULT_INDIRIZZO_PEC);
        assertThat(testAnagraficaCandidato.getIndirizzoResidenza()).isEqualTo(DEFAULT_INDIRIZZO_RESIDENZA);
        assertThat(testAnagraficaCandidato.getCapResidenza()).isEqualTo(DEFAULT_CAP_RESIDENZA);
        assertThat(testAnagraficaCandidato.getComuneResidenza()).isEqualTo(DEFAULT_COMUNE_RESIDENZA);
        assertThat(testAnagraficaCandidato.getProvinciaResidenza()).isEqualTo(DEFAULT_PROVINCIA_RESIDENZA);
        assertThat(testAnagraficaCandidato.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createAnagraficaCandidatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = anagraficaCandidatoRepository.findAll().size();

        // Create the AnagraficaCandidato with an existing ID
        anagraficaCandidato.setId(1L);
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnagraficaCandidato in the database
        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCognomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setCognome(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setNome(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLuogoNascitaIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setLuogoNascita(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataNascitaIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setDataNascita(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodiceFiscaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setCodiceFiscale(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProfessioneIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setProfessione(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroTelefonoCellulareIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setNumeroTelefonoCellulare(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkeMailIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.seteMail(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIndirizzoResidenzaIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setIndirizzoResidenza(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCapResidenzaIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setCapResidenza(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkComuneResidenzaIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setComuneResidenza(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProvinciaResidenzaIsRequired() throws Exception {
        int databaseSizeBeforeTest = anagraficaCandidatoRepository.findAll().size();
        // set the field null
        anagraficaCandidato.setProvinciaResidenza(null);

        // Create the AnagraficaCandidato, which fails.
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoes() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList
        restAnagraficaCandidatoMockMvc.perform(get("/api/anagrafica-candidatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anagraficaCandidato.getId().intValue())))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].luogoNascita").value(hasItem(DEFAULT_LUOGO_NASCITA)))
            .andExpect(jsonPath("$.[*].dataNascita").value(hasItem(DEFAULT_DATA_NASCITA.toString())))
            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE)))
            .andExpect(jsonPath("$.[*].professione").value(hasItem(DEFAULT_PROFESSIONE)))
            .andExpect(jsonPath("$.[*].partitaIva").value(hasItem(DEFAULT_PARTITA_IVA)))
            .andExpect(jsonPath("$.[*].numeroTelefonoFisso").value(hasItem(DEFAULT_NUMERO_TELEFONO_FISSO)))
            .andExpect(jsonPath("$.[*].numeroTelefonoCellulare").value(hasItem(DEFAULT_NUMERO_TELEFONO_CELLULARE)))
            .andExpect(jsonPath("$.[*].eMail").value(hasItem(DEFAULT_E_MAIL)))
            .andExpect(jsonPath("$.[*].indirizzoPec").value(hasItem(DEFAULT_INDIRIZZO_PEC)))
            .andExpect(jsonPath("$.[*].indirizzoResidenza").value(hasItem(DEFAULT_INDIRIZZO_RESIDENZA)))
            .andExpect(jsonPath("$.[*].capResidenza").value(hasItem(DEFAULT_CAP_RESIDENZA)))
            .andExpect(jsonPath("$.[*].comuneResidenza").value(hasItem(DEFAULT_COMUNE_RESIDENZA)))
            .andExpect(jsonPath("$.[*].provinciaResidenza").value(hasItem(DEFAULT_PROVINCIA_RESIDENZA)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }
    
    @Test
    @Transactional
    public void getAnagraficaCandidato() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get the anagraficaCandidato
        restAnagraficaCandidatoMockMvc.perform(get("/api/anagrafica-candidatoes/{id}", anagraficaCandidato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(anagraficaCandidato.getId().intValue()))
            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.luogoNascita").value(DEFAULT_LUOGO_NASCITA))
            .andExpect(jsonPath("$.dataNascita").value(DEFAULT_DATA_NASCITA.toString()))
            .andExpect(jsonPath("$.codiceFiscale").value(DEFAULT_CODICE_FISCALE))
            .andExpect(jsonPath("$.professione").value(DEFAULT_PROFESSIONE))
            .andExpect(jsonPath("$.partitaIva").value(DEFAULT_PARTITA_IVA))
            .andExpect(jsonPath("$.numeroTelefonoFisso").value(DEFAULT_NUMERO_TELEFONO_FISSO))
            .andExpect(jsonPath("$.numeroTelefonoCellulare").value(DEFAULT_NUMERO_TELEFONO_CELLULARE))
            .andExpect(jsonPath("$.eMail").value(DEFAULT_E_MAIL))
            .andExpect(jsonPath("$.indirizzoPec").value(DEFAULT_INDIRIZZO_PEC))
            .andExpect(jsonPath("$.indirizzoResidenza").value(DEFAULT_INDIRIZZO_RESIDENZA))
            .andExpect(jsonPath("$.capResidenza").value(DEFAULT_CAP_RESIDENZA))
            .andExpect(jsonPath("$.comuneResidenza").value(DEFAULT_COMUNE_RESIDENZA))
            .andExpect(jsonPath("$.provinciaResidenza").value(DEFAULT_PROVINCIA_RESIDENZA))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    public void getNonExistingAnagraficaCandidato() throws Exception {
        // Get the anagraficaCandidato
        restAnagraficaCandidatoMockMvc.perform(get("/api/anagrafica-candidatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnagraficaCandidato() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        int databaseSizeBeforeUpdate = anagraficaCandidatoRepository.findAll().size();

        // Update the anagraficaCandidato
        AnagraficaCandidato updatedAnagraficaCandidato = anagraficaCandidatoRepository.findById(anagraficaCandidato.getId()).get();
        // Disconnect from session so that the updates on updatedAnagraficaCandidato are not directly saved in db
        em.detach(updatedAnagraficaCandidato);
        updatedAnagraficaCandidato
            .cognome(UPDATED_COGNOME)
            .nome(UPDATED_NOME)
            .luogoNascita(UPDATED_LUOGO_NASCITA)
            .dataNascita(UPDATED_DATA_NASCITA)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .professione(UPDATED_PROFESSIONE)
            .partitaIva(UPDATED_PARTITA_IVA)
            .numeroTelefonoFisso(UPDATED_NUMERO_TELEFONO_FISSO)
            .numeroTelefonoCellulare(UPDATED_NUMERO_TELEFONO_CELLULARE)
            .eMail(UPDATED_E_MAIL)
            .indirizzoPec(UPDATED_INDIRIZZO_PEC)
            .indirizzoResidenza(UPDATED_INDIRIZZO_RESIDENZA)
            .capResidenza(UPDATED_CAP_RESIDENZA)
            .comuneResidenza(UPDATED_COMUNE_RESIDENZA)
            .provinciaResidenza(UPDATED_PROVINCIA_RESIDENZA)
            .note(UPDATED_NOTE);
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(updatedAnagraficaCandidato);

        restAnagraficaCandidatoMockMvc.perform(put("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isOk());

        // Validate the AnagraficaCandidato in the database
        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeUpdate);
        AnagraficaCandidato testAnagraficaCandidato = anagraficaCandidatoList.get(anagraficaCandidatoList.size() - 1);
        assertThat(testAnagraficaCandidato.getCognome()).isEqualTo(UPDATED_COGNOME);
        assertThat(testAnagraficaCandidato.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAnagraficaCandidato.getLuogoNascita()).isEqualTo(UPDATED_LUOGO_NASCITA);
        assertThat(testAnagraficaCandidato.getDataNascita()).isEqualTo(UPDATED_DATA_NASCITA);
        assertThat(testAnagraficaCandidato.getCodiceFiscale()).isEqualTo(UPDATED_CODICE_FISCALE);
        assertThat(testAnagraficaCandidato.getProfessione()).isEqualTo(UPDATED_PROFESSIONE);
        assertThat(testAnagraficaCandidato.getPartitaIva()).isEqualTo(UPDATED_PARTITA_IVA);
        assertThat(testAnagraficaCandidato.getNumeroTelefonoFisso()).isEqualTo(UPDATED_NUMERO_TELEFONO_FISSO);
        assertThat(testAnagraficaCandidato.getNumeroTelefonoCellulare()).isEqualTo(UPDATED_NUMERO_TELEFONO_CELLULARE);
        assertThat(testAnagraficaCandidato.geteMail()).isEqualTo(UPDATED_E_MAIL);
        assertThat(testAnagraficaCandidato.getIndirizzoPec()).isEqualTo(UPDATED_INDIRIZZO_PEC);
        assertThat(testAnagraficaCandidato.getIndirizzoResidenza()).isEqualTo(UPDATED_INDIRIZZO_RESIDENZA);
        assertThat(testAnagraficaCandidato.getCapResidenza()).isEqualTo(UPDATED_CAP_RESIDENZA);
        assertThat(testAnagraficaCandidato.getComuneResidenza()).isEqualTo(UPDATED_COMUNE_RESIDENZA);
        assertThat(testAnagraficaCandidato.getProvinciaResidenza()).isEqualTo(UPDATED_PROVINCIA_RESIDENZA);
        assertThat(testAnagraficaCandidato.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingAnagraficaCandidato() throws Exception {
        int databaseSizeBeforeUpdate = anagraficaCandidatoRepository.findAll().size();

        // Create the AnagraficaCandidato
        AnagraficaCandidatoDTO anagraficaCandidatoDTO = anagraficaCandidatoMapper.toDto(anagraficaCandidato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnagraficaCandidatoMockMvc.perform(put("/api/anagrafica-candidatoes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(anagraficaCandidatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnagraficaCandidato in the database
        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnagraficaCandidato() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        int databaseSizeBeforeDelete = anagraficaCandidatoRepository.findAll().size();

        // Delete the anagraficaCandidato
        restAnagraficaCandidatoMockMvc.perform(delete("/api/anagrafica-candidatoes/{id}", anagraficaCandidato.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
