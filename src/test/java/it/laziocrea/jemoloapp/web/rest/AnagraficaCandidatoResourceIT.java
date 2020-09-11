package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.domain.CompetenzeLng;
import it.laziocrea.jemoloapp.domain.TitoloStudio;
import it.laziocrea.jemoloapp.domain.Curriculum;
import it.laziocrea.jemoloapp.domain.Competenza;
import it.laziocrea.jemoloapp.domain.DichiarazioniObligatorie;
import it.laziocrea.jemoloapp.domain.Candidato;
import it.laziocrea.jemoloapp.repository.AnagraficaCandidatoRepository;
import it.laziocrea.jemoloapp.service.AnagraficaCandidatoService;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoDTO;
import it.laziocrea.jemoloapp.service.mapper.AnagraficaCandidatoMapper;
import it.laziocrea.jemoloapp.service.dto.AnagraficaCandidatoCriteria;
import it.laziocrea.jemoloapp.service.AnagraficaCandidatoQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AnagraficaCandidatoResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AnagraficaCandidatoResourceIT {

    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_LUOGO_NASCITA = "AAAAAAAAAA";
    private static final String UPDATED_LUOGO_NASCITA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_NASCITA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCITA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATA_NASCITA = LocalDate.ofEpochDay(-1L);

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
    private AnagraficaCandidatoQueryService anagraficaCandidatoQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnagraficaCandidatoMockMvc;

    private AnagraficaCandidato anagraficaCandidato;

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
        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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
        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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


        restAnagraficaCandidatoMockMvc.perform(post("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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
    public void getAnagraficaCandidatoesByIdFiltering() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        Long id = anagraficaCandidato.getId();

        defaultAnagraficaCandidatoShouldBeFound("id.equals=" + id);
        defaultAnagraficaCandidatoShouldNotBeFound("id.notEquals=" + id);

        defaultAnagraficaCandidatoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAnagraficaCandidatoShouldNotBeFound("id.greaterThan=" + id);

        defaultAnagraficaCandidatoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAnagraficaCandidatoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCognomeIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where cognome equals to DEFAULT_COGNOME
        defaultAnagraficaCandidatoShouldBeFound("cognome.equals=" + DEFAULT_COGNOME);

        // Get all the anagraficaCandidatoList where cognome equals to UPDATED_COGNOME
        defaultAnagraficaCandidatoShouldNotBeFound("cognome.equals=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCognomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where cognome not equals to DEFAULT_COGNOME
        defaultAnagraficaCandidatoShouldNotBeFound("cognome.notEquals=" + DEFAULT_COGNOME);

        // Get all the anagraficaCandidatoList where cognome not equals to UPDATED_COGNOME
        defaultAnagraficaCandidatoShouldBeFound("cognome.notEquals=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCognomeIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where cognome in DEFAULT_COGNOME or UPDATED_COGNOME
        defaultAnagraficaCandidatoShouldBeFound("cognome.in=" + DEFAULT_COGNOME + "," + UPDATED_COGNOME);

        // Get all the anagraficaCandidatoList where cognome equals to UPDATED_COGNOME
        defaultAnagraficaCandidatoShouldNotBeFound("cognome.in=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCognomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where cognome is not null
        defaultAnagraficaCandidatoShouldBeFound("cognome.specified=true");

        // Get all the anagraficaCandidatoList where cognome is null
        defaultAnagraficaCandidatoShouldNotBeFound("cognome.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCognomeContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where cognome contains DEFAULT_COGNOME
        defaultAnagraficaCandidatoShouldBeFound("cognome.contains=" + DEFAULT_COGNOME);

        // Get all the anagraficaCandidatoList where cognome contains UPDATED_COGNOME
        defaultAnagraficaCandidatoShouldNotBeFound("cognome.contains=" + UPDATED_COGNOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCognomeNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where cognome does not contain DEFAULT_COGNOME
        defaultAnagraficaCandidatoShouldNotBeFound("cognome.doesNotContain=" + DEFAULT_COGNOME);

        // Get all the anagraficaCandidatoList where cognome does not contain UPDATED_COGNOME
        defaultAnagraficaCandidatoShouldBeFound("cognome.doesNotContain=" + UPDATED_COGNOME);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where nome equals to DEFAULT_NOME
        defaultAnagraficaCandidatoShouldBeFound("nome.equals=" + DEFAULT_NOME);

        // Get all the anagraficaCandidatoList where nome equals to UPDATED_NOME
        defaultAnagraficaCandidatoShouldNotBeFound("nome.equals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where nome not equals to DEFAULT_NOME
        defaultAnagraficaCandidatoShouldNotBeFound("nome.notEquals=" + DEFAULT_NOME);

        // Get all the anagraficaCandidatoList where nome not equals to UPDATED_NOME
        defaultAnagraficaCandidatoShouldBeFound("nome.notEquals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNomeIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where nome in DEFAULT_NOME or UPDATED_NOME
        defaultAnagraficaCandidatoShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);

        // Get all the anagraficaCandidatoList where nome equals to UPDATED_NOME
        defaultAnagraficaCandidatoShouldNotBeFound("nome.in=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where nome is not null
        defaultAnagraficaCandidatoShouldBeFound("nome.specified=true");

        // Get all the anagraficaCandidatoList where nome is null
        defaultAnagraficaCandidatoShouldNotBeFound("nome.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNomeContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where nome contains DEFAULT_NOME
        defaultAnagraficaCandidatoShouldBeFound("nome.contains=" + DEFAULT_NOME);

        // Get all the anagraficaCandidatoList where nome contains UPDATED_NOME
        defaultAnagraficaCandidatoShouldNotBeFound("nome.contains=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNomeNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where nome does not contain DEFAULT_NOME
        defaultAnagraficaCandidatoShouldNotBeFound("nome.doesNotContain=" + DEFAULT_NOME);

        // Get all the anagraficaCandidatoList where nome does not contain UPDATED_NOME
        defaultAnagraficaCandidatoShouldBeFound("nome.doesNotContain=" + UPDATED_NOME);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByLuogoNascitaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where luogoNascita equals to DEFAULT_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("luogoNascita.equals=" + DEFAULT_LUOGO_NASCITA);

        // Get all the anagraficaCandidatoList where luogoNascita equals to UPDATED_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("luogoNascita.equals=" + UPDATED_LUOGO_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByLuogoNascitaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where luogoNascita not equals to DEFAULT_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("luogoNascita.notEquals=" + DEFAULT_LUOGO_NASCITA);

        // Get all the anagraficaCandidatoList where luogoNascita not equals to UPDATED_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("luogoNascita.notEquals=" + UPDATED_LUOGO_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByLuogoNascitaIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where luogoNascita in DEFAULT_LUOGO_NASCITA or UPDATED_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("luogoNascita.in=" + DEFAULT_LUOGO_NASCITA + "," + UPDATED_LUOGO_NASCITA);

        // Get all the anagraficaCandidatoList where luogoNascita equals to UPDATED_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("luogoNascita.in=" + UPDATED_LUOGO_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByLuogoNascitaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where luogoNascita is not null
        defaultAnagraficaCandidatoShouldBeFound("luogoNascita.specified=true");

        // Get all the anagraficaCandidatoList where luogoNascita is null
        defaultAnagraficaCandidatoShouldNotBeFound("luogoNascita.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByLuogoNascitaContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where luogoNascita contains DEFAULT_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("luogoNascita.contains=" + DEFAULT_LUOGO_NASCITA);

        // Get all the anagraficaCandidatoList where luogoNascita contains UPDATED_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("luogoNascita.contains=" + UPDATED_LUOGO_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByLuogoNascitaNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where luogoNascita does not contain DEFAULT_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("luogoNascita.doesNotContain=" + DEFAULT_LUOGO_NASCITA);

        // Get all the anagraficaCandidatoList where luogoNascita does not contain UPDATED_LUOGO_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("luogoNascita.doesNotContain=" + UPDATED_LUOGO_NASCITA);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita equals to DEFAULT_DATA_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.equals=" + DEFAULT_DATA_NASCITA);

        // Get all the anagraficaCandidatoList where dataNascita equals to UPDATED_DATA_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.equals=" + UPDATED_DATA_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita not equals to DEFAULT_DATA_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.notEquals=" + DEFAULT_DATA_NASCITA);

        // Get all the anagraficaCandidatoList where dataNascita not equals to UPDATED_DATA_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.notEquals=" + UPDATED_DATA_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita in DEFAULT_DATA_NASCITA or UPDATED_DATA_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.in=" + DEFAULT_DATA_NASCITA + "," + UPDATED_DATA_NASCITA);

        // Get all the anagraficaCandidatoList where dataNascita equals to UPDATED_DATA_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.in=" + UPDATED_DATA_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita is not null
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.specified=true");

        // Get all the anagraficaCandidatoList where dataNascita is null
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita is greater than or equal to DEFAULT_DATA_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.greaterThanOrEqual=" + DEFAULT_DATA_NASCITA);

        // Get all the anagraficaCandidatoList where dataNascita is greater than or equal to UPDATED_DATA_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.greaterThanOrEqual=" + UPDATED_DATA_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita is less than or equal to DEFAULT_DATA_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.lessThanOrEqual=" + DEFAULT_DATA_NASCITA);

        // Get all the anagraficaCandidatoList where dataNascita is less than or equal to SMALLER_DATA_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.lessThanOrEqual=" + SMALLER_DATA_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsLessThanSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita is less than DEFAULT_DATA_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.lessThan=" + DEFAULT_DATA_NASCITA);

        // Get all the anagraficaCandidatoList where dataNascita is less than UPDATED_DATA_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.lessThan=" + UPDATED_DATA_NASCITA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDataNascitaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where dataNascita is greater than DEFAULT_DATA_NASCITA
        defaultAnagraficaCandidatoShouldNotBeFound("dataNascita.greaterThan=" + DEFAULT_DATA_NASCITA);

        // Get all the anagraficaCandidatoList where dataNascita is greater than SMALLER_DATA_NASCITA
        defaultAnagraficaCandidatoShouldBeFound("dataNascita.greaterThan=" + SMALLER_DATA_NASCITA);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCodiceFiscaleIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where codiceFiscale equals to DEFAULT_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldBeFound("codiceFiscale.equals=" + DEFAULT_CODICE_FISCALE);

        // Get all the anagraficaCandidatoList where codiceFiscale equals to UPDATED_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldNotBeFound("codiceFiscale.equals=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCodiceFiscaleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where codiceFiscale not equals to DEFAULT_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldNotBeFound("codiceFiscale.notEquals=" + DEFAULT_CODICE_FISCALE);

        // Get all the anagraficaCandidatoList where codiceFiscale not equals to UPDATED_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldBeFound("codiceFiscale.notEquals=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCodiceFiscaleIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where codiceFiscale in DEFAULT_CODICE_FISCALE or UPDATED_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldBeFound("codiceFiscale.in=" + DEFAULT_CODICE_FISCALE + "," + UPDATED_CODICE_FISCALE);

        // Get all the anagraficaCandidatoList where codiceFiscale equals to UPDATED_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldNotBeFound("codiceFiscale.in=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCodiceFiscaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where codiceFiscale is not null
        defaultAnagraficaCandidatoShouldBeFound("codiceFiscale.specified=true");

        // Get all the anagraficaCandidatoList where codiceFiscale is null
        defaultAnagraficaCandidatoShouldNotBeFound("codiceFiscale.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCodiceFiscaleContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where codiceFiscale contains DEFAULT_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldBeFound("codiceFiscale.contains=" + DEFAULT_CODICE_FISCALE);

        // Get all the anagraficaCandidatoList where codiceFiscale contains UPDATED_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldNotBeFound("codiceFiscale.contains=" + UPDATED_CODICE_FISCALE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCodiceFiscaleNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where codiceFiscale does not contain DEFAULT_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldNotBeFound("codiceFiscale.doesNotContain=" + DEFAULT_CODICE_FISCALE);

        // Get all the anagraficaCandidatoList where codiceFiscale does not contain UPDATED_CODICE_FISCALE
        defaultAnagraficaCandidatoShouldBeFound("codiceFiscale.doesNotContain=" + UPDATED_CODICE_FISCALE);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProfessioneIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where professione equals to DEFAULT_PROFESSIONE
        defaultAnagraficaCandidatoShouldBeFound("professione.equals=" + DEFAULT_PROFESSIONE);

        // Get all the anagraficaCandidatoList where professione equals to UPDATED_PROFESSIONE
        defaultAnagraficaCandidatoShouldNotBeFound("professione.equals=" + UPDATED_PROFESSIONE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProfessioneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where professione not equals to DEFAULT_PROFESSIONE
        defaultAnagraficaCandidatoShouldNotBeFound("professione.notEquals=" + DEFAULT_PROFESSIONE);

        // Get all the anagraficaCandidatoList where professione not equals to UPDATED_PROFESSIONE
        defaultAnagraficaCandidatoShouldBeFound("professione.notEquals=" + UPDATED_PROFESSIONE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProfessioneIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where professione in DEFAULT_PROFESSIONE or UPDATED_PROFESSIONE
        defaultAnagraficaCandidatoShouldBeFound("professione.in=" + DEFAULT_PROFESSIONE + "," + UPDATED_PROFESSIONE);

        // Get all the anagraficaCandidatoList where professione equals to UPDATED_PROFESSIONE
        defaultAnagraficaCandidatoShouldNotBeFound("professione.in=" + UPDATED_PROFESSIONE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProfessioneIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where professione is not null
        defaultAnagraficaCandidatoShouldBeFound("professione.specified=true");

        // Get all the anagraficaCandidatoList where professione is null
        defaultAnagraficaCandidatoShouldNotBeFound("professione.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProfessioneContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where professione contains DEFAULT_PROFESSIONE
        defaultAnagraficaCandidatoShouldBeFound("professione.contains=" + DEFAULT_PROFESSIONE);

        // Get all the anagraficaCandidatoList where professione contains UPDATED_PROFESSIONE
        defaultAnagraficaCandidatoShouldNotBeFound("professione.contains=" + UPDATED_PROFESSIONE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProfessioneNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where professione does not contain DEFAULT_PROFESSIONE
        defaultAnagraficaCandidatoShouldNotBeFound("professione.doesNotContain=" + DEFAULT_PROFESSIONE);

        // Get all the anagraficaCandidatoList where professione does not contain UPDATED_PROFESSIONE
        defaultAnagraficaCandidatoShouldBeFound("professione.doesNotContain=" + UPDATED_PROFESSIONE);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByPartitaIvaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where partitaIva equals to DEFAULT_PARTITA_IVA
        defaultAnagraficaCandidatoShouldBeFound("partitaIva.equals=" + DEFAULT_PARTITA_IVA);

        // Get all the anagraficaCandidatoList where partitaIva equals to UPDATED_PARTITA_IVA
        defaultAnagraficaCandidatoShouldNotBeFound("partitaIva.equals=" + UPDATED_PARTITA_IVA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByPartitaIvaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where partitaIva not equals to DEFAULT_PARTITA_IVA
        defaultAnagraficaCandidatoShouldNotBeFound("partitaIva.notEquals=" + DEFAULT_PARTITA_IVA);

        // Get all the anagraficaCandidatoList where partitaIva not equals to UPDATED_PARTITA_IVA
        defaultAnagraficaCandidatoShouldBeFound("partitaIva.notEquals=" + UPDATED_PARTITA_IVA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByPartitaIvaIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where partitaIva in DEFAULT_PARTITA_IVA or UPDATED_PARTITA_IVA
        defaultAnagraficaCandidatoShouldBeFound("partitaIva.in=" + DEFAULT_PARTITA_IVA + "," + UPDATED_PARTITA_IVA);

        // Get all the anagraficaCandidatoList where partitaIva equals to UPDATED_PARTITA_IVA
        defaultAnagraficaCandidatoShouldNotBeFound("partitaIva.in=" + UPDATED_PARTITA_IVA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByPartitaIvaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where partitaIva is not null
        defaultAnagraficaCandidatoShouldBeFound("partitaIva.specified=true");

        // Get all the anagraficaCandidatoList where partitaIva is null
        defaultAnagraficaCandidatoShouldNotBeFound("partitaIva.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByPartitaIvaContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where partitaIva contains DEFAULT_PARTITA_IVA
        defaultAnagraficaCandidatoShouldBeFound("partitaIva.contains=" + DEFAULT_PARTITA_IVA);

        // Get all the anagraficaCandidatoList where partitaIva contains UPDATED_PARTITA_IVA
        defaultAnagraficaCandidatoShouldNotBeFound("partitaIva.contains=" + UPDATED_PARTITA_IVA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByPartitaIvaNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where partitaIva does not contain DEFAULT_PARTITA_IVA
        defaultAnagraficaCandidatoShouldNotBeFound("partitaIva.doesNotContain=" + DEFAULT_PARTITA_IVA);

        // Get all the anagraficaCandidatoList where partitaIva does not contain UPDATED_PARTITA_IVA
        defaultAnagraficaCandidatoShouldBeFound("partitaIva.doesNotContain=" + UPDATED_PARTITA_IVA);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoFissoIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso equals to DEFAULT_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoFisso.equals=" + DEFAULT_NUMERO_TELEFONO_FISSO);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso equals to UPDATED_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoFisso.equals=" + UPDATED_NUMERO_TELEFONO_FISSO);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoFissoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso not equals to DEFAULT_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoFisso.notEquals=" + DEFAULT_NUMERO_TELEFONO_FISSO);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso not equals to UPDATED_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoFisso.notEquals=" + UPDATED_NUMERO_TELEFONO_FISSO);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoFissoIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso in DEFAULT_NUMERO_TELEFONO_FISSO or UPDATED_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoFisso.in=" + DEFAULT_NUMERO_TELEFONO_FISSO + "," + UPDATED_NUMERO_TELEFONO_FISSO);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso equals to UPDATED_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoFisso.in=" + UPDATED_NUMERO_TELEFONO_FISSO);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoFissoIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso is not null
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoFisso.specified=true");

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso is null
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoFisso.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoFissoContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso contains DEFAULT_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoFisso.contains=" + DEFAULT_NUMERO_TELEFONO_FISSO);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso contains UPDATED_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoFisso.contains=" + UPDATED_NUMERO_TELEFONO_FISSO);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoFissoNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso does not contain DEFAULT_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoFisso.doesNotContain=" + DEFAULT_NUMERO_TELEFONO_FISSO);

        // Get all the anagraficaCandidatoList where numeroTelefonoFisso does not contain UPDATED_NUMERO_TELEFONO_FISSO
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoFisso.doesNotContain=" + UPDATED_NUMERO_TELEFONO_FISSO);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoCellulareIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare equals to DEFAULT_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoCellulare.equals=" + DEFAULT_NUMERO_TELEFONO_CELLULARE);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare equals to UPDATED_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoCellulare.equals=" + UPDATED_NUMERO_TELEFONO_CELLULARE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoCellulareIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare not equals to DEFAULT_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoCellulare.notEquals=" + DEFAULT_NUMERO_TELEFONO_CELLULARE);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare not equals to UPDATED_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoCellulare.notEquals=" + UPDATED_NUMERO_TELEFONO_CELLULARE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoCellulareIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare in DEFAULT_NUMERO_TELEFONO_CELLULARE or UPDATED_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoCellulare.in=" + DEFAULT_NUMERO_TELEFONO_CELLULARE + "," + UPDATED_NUMERO_TELEFONO_CELLULARE);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare equals to UPDATED_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoCellulare.in=" + UPDATED_NUMERO_TELEFONO_CELLULARE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoCellulareIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare is not null
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoCellulare.specified=true");

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare is null
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoCellulare.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoCellulareContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare contains DEFAULT_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoCellulare.contains=" + DEFAULT_NUMERO_TELEFONO_CELLULARE);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare contains UPDATED_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoCellulare.contains=" + UPDATED_NUMERO_TELEFONO_CELLULARE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNumeroTelefonoCellulareNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare does not contain DEFAULT_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldNotBeFound("numeroTelefonoCellulare.doesNotContain=" + DEFAULT_NUMERO_TELEFONO_CELLULARE);

        // Get all the anagraficaCandidatoList where numeroTelefonoCellulare does not contain UPDATED_NUMERO_TELEFONO_CELLULARE
        defaultAnagraficaCandidatoShouldBeFound("numeroTelefonoCellulare.doesNotContain=" + UPDATED_NUMERO_TELEFONO_CELLULARE);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByeMailIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where eMail equals to DEFAULT_E_MAIL
        defaultAnagraficaCandidatoShouldBeFound("eMail.equals=" + DEFAULT_E_MAIL);

        // Get all the anagraficaCandidatoList where eMail equals to UPDATED_E_MAIL
        defaultAnagraficaCandidatoShouldNotBeFound("eMail.equals=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByeMailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where eMail not equals to DEFAULT_E_MAIL
        defaultAnagraficaCandidatoShouldNotBeFound("eMail.notEquals=" + DEFAULT_E_MAIL);

        // Get all the anagraficaCandidatoList where eMail not equals to UPDATED_E_MAIL
        defaultAnagraficaCandidatoShouldBeFound("eMail.notEquals=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByeMailIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where eMail in DEFAULT_E_MAIL or UPDATED_E_MAIL
        defaultAnagraficaCandidatoShouldBeFound("eMail.in=" + DEFAULT_E_MAIL + "," + UPDATED_E_MAIL);

        // Get all the anagraficaCandidatoList where eMail equals to UPDATED_E_MAIL
        defaultAnagraficaCandidatoShouldNotBeFound("eMail.in=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByeMailIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where eMail is not null
        defaultAnagraficaCandidatoShouldBeFound("eMail.specified=true");

        // Get all the anagraficaCandidatoList where eMail is null
        defaultAnagraficaCandidatoShouldNotBeFound("eMail.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByeMailContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where eMail contains DEFAULT_E_MAIL
        defaultAnagraficaCandidatoShouldBeFound("eMail.contains=" + DEFAULT_E_MAIL);

        // Get all the anagraficaCandidatoList where eMail contains UPDATED_E_MAIL
        defaultAnagraficaCandidatoShouldNotBeFound("eMail.contains=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByeMailNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where eMail does not contain DEFAULT_E_MAIL
        defaultAnagraficaCandidatoShouldNotBeFound("eMail.doesNotContain=" + DEFAULT_E_MAIL);

        // Get all the anagraficaCandidatoList where eMail does not contain UPDATED_E_MAIL
        defaultAnagraficaCandidatoShouldBeFound("eMail.doesNotContain=" + UPDATED_E_MAIL);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoPecIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoPec equals to DEFAULT_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldBeFound("indirizzoPec.equals=" + DEFAULT_INDIRIZZO_PEC);

        // Get all the anagraficaCandidatoList where indirizzoPec equals to UPDATED_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoPec.equals=" + UPDATED_INDIRIZZO_PEC);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoPecIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoPec not equals to DEFAULT_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoPec.notEquals=" + DEFAULT_INDIRIZZO_PEC);

        // Get all the anagraficaCandidatoList where indirizzoPec not equals to UPDATED_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldBeFound("indirizzoPec.notEquals=" + UPDATED_INDIRIZZO_PEC);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoPecIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoPec in DEFAULT_INDIRIZZO_PEC or UPDATED_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldBeFound("indirizzoPec.in=" + DEFAULT_INDIRIZZO_PEC + "," + UPDATED_INDIRIZZO_PEC);

        // Get all the anagraficaCandidatoList where indirizzoPec equals to UPDATED_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoPec.in=" + UPDATED_INDIRIZZO_PEC);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoPecIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoPec is not null
        defaultAnagraficaCandidatoShouldBeFound("indirizzoPec.specified=true");

        // Get all the anagraficaCandidatoList where indirizzoPec is null
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoPec.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoPecContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoPec contains DEFAULT_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldBeFound("indirizzoPec.contains=" + DEFAULT_INDIRIZZO_PEC);

        // Get all the anagraficaCandidatoList where indirizzoPec contains UPDATED_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoPec.contains=" + UPDATED_INDIRIZZO_PEC);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoPecNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoPec does not contain DEFAULT_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoPec.doesNotContain=" + DEFAULT_INDIRIZZO_PEC);

        // Get all the anagraficaCandidatoList where indirizzoPec does not contain UPDATED_INDIRIZZO_PEC
        defaultAnagraficaCandidatoShouldBeFound("indirizzoPec.doesNotContain=" + UPDATED_INDIRIZZO_PEC);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoResidenzaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoResidenza equals to DEFAULT_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("indirizzoResidenza.equals=" + DEFAULT_INDIRIZZO_RESIDENZA);

        // Get all the anagraficaCandidatoList where indirizzoResidenza equals to UPDATED_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoResidenza.equals=" + UPDATED_INDIRIZZO_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoResidenzaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoResidenza not equals to DEFAULT_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoResidenza.notEquals=" + DEFAULT_INDIRIZZO_RESIDENZA);

        // Get all the anagraficaCandidatoList where indirizzoResidenza not equals to UPDATED_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("indirizzoResidenza.notEquals=" + UPDATED_INDIRIZZO_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoResidenzaIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoResidenza in DEFAULT_INDIRIZZO_RESIDENZA or UPDATED_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("indirizzoResidenza.in=" + DEFAULT_INDIRIZZO_RESIDENZA + "," + UPDATED_INDIRIZZO_RESIDENZA);

        // Get all the anagraficaCandidatoList where indirizzoResidenza equals to UPDATED_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoResidenza.in=" + UPDATED_INDIRIZZO_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoResidenzaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoResidenza is not null
        defaultAnagraficaCandidatoShouldBeFound("indirizzoResidenza.specified=true");

        // Get all the anagraficaCandidatoList where indirizzoResidenza is null
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoResidenza.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoResidenzaContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoResidenza contains DEFAULT_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("indirizzoResidenza.contains=" + DEFAULT_INDIRIZZO_RESIDENZA);

        // Get all the anagraficaCandidatoList where indirizzoResidenza contains UPDATED_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoResidenza.contains=" + UPDATED_INDIRIZZO_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByIndirizzoResidenzaNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where indirizzoResidenza does not contain DEFAULT_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("indirizzoResidenza.doesNotContain=" + DEFAULT_INDIRIZZO_RESIDENZA);

        // Get all the anagraficaCandidatoList where indirizzoResidenza does not contain UPDATED_INDIRIZZO_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("indirizzoResidenza.doesNotContain=" + UPDATED_INDIRIZZO_RESIDENZA);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCapResidenzaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where capResidenza equals to DEFAULT_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("capResidenza.equals=" + DEFAULT_CAP_RESIDENZA);

        // Get all the anagraficaCandidatoList where capResidenza equals to UPDATED_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("capResidenza.equals=" + UPDATED_CAP_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCapResidenzaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where capResidenza not equals to DEFAULT_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("capResidenza.notEquals=" + DEFAULT_CAP_RESIDENZA);

        // Get all the anagraficaCandidatoList where capResidenza not equals to UPDATED_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("capResidenza.notEquals=" + UPDATED_CAP_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCapResidenzaIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where capResidenza in DEFAULT_CAP_RESIDENZA or UPDATED_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("capResidenza.in=" + DEFAULT_CAP_RESIDENZA + "," + UPDATED_CAP_RESIDENZA);

        // Get all the anagraficaCandidatoList where capResidenza equals to UPDATED_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("capResidenza.in=" + UPDATED_CAP_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCapResidenzaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where capResidenza is not null
        defaultAnagraficaCandidatoShouldBeFound("capResidenza.specified=true");

        // Get all the anagraficaCandidatoList where capResidenza is null
        defaultAnagraficaCandidatoShouldNotBeFound("capResidenza.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCapResidenzaContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where capResidenza contains DEFAULT_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("capResidenza.contains=" + DEFAULT_CAP_RESIDENZA);

        // Get all the anagraficaCandidatoList where capResidenza contains UPDATED_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("capResidenza.contains=" + UPDATED_CAP_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCapResidenzaNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where capResidenza does not contain DEFAULT_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("capResidenza.doesNotContain=" + DEFAULT_CAP_RESIDENZA);

        // Get all the anagraficaCandidatoList where capResidenza does not contain UPDATED_CAP_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("capResidenza.doesNotContain=" + UPDATED_CAP_RESIDENZA);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByComuneResidenzaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where comuneResidenza equals to DEFAULT_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("comuneResidenza.equals=" + DEFAULT_COMUNE_RESIDENZA);

        // Get all the anagraficaCandidatoList where comuneResidenza equals to UPDATED_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("comuneResidenza.equals=" + UPDATED_COMUNE_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByComuneResidenzaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where comuneResidenza not equals to DEFAULT_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("comuneResidenza.notEquals=" + DEFAULT_COMUNE_RESIDENZA);

        // Get all the anagraficaCandidatoList where comuneResidenza not equals to UPDATED_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("comuneResidenza.notEquals=" + UPDATED_COMUNE_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByComuneResidenzaIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where comuneResidenza in DEFAULT_COMUNE_RESIDENZA or UPDATED_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("comuneResidenza.in=" + DEFAULT_COMUNE_RESIDENZA + "," + UPDATED_COMUNE_RESIDENZA);

        // Get all the anagraficaCandidatoList where comuneResidenza equals to UPDATED_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("comuneResidenza.in=" + UPDATED_COMUNE_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByComuneResidenzaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where comuneResidenza is not null
        defaultAnagraficaCandidatoShouldBeFound("comuneResidenza.specified=true");

        // Get all the anagraficaCandidatoList where comuneResidenza is null
        defaultAnagraficaCandidatoShouldNotBeFound("comuneResidenza.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByComuneResidenzaContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where comuneResidenza contains DEFAULT_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("comuneResidenza.contains=" + DEFAULT_COMUNE_RESIDENZA);

        // Get all the anagraficaCandidatoList where comuneResidenza contains UPDATED_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("comuneResidenza.contains=" + UPDATED_COMUNE_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByComuneResidenzaNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where comuneResidenza does not contain DEFAULT_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("comuneResidenza.doesNotContain=" + DEFAULT_COMUNE_RESIDENZA);

        // Get all the anagraficaCandidatoList where comuneResidenza does not contain UPDATED_COMUNE_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("comuneResidenza.doesNotContain=" + UPDATED_COMUNE_RESIDENZA);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProvinciaResidenzaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where provinciaResidenza equals to DEFAULT_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("provinciaResidenza.equals=" + DEFAULT_PROVINCIA_RESIDENZA);

        // Get all the anagraficaCandidatoList where provinciaResidenza equals to UPDATED_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("provinciaResidenza.equals=" + UPDATED_PROVINCIA_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProvinciaResidenzaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where provinciaResidenza not equals to DEFAULT_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("provinciaResidenza.notEquals=" + DEFAULT_PROVINCIA_RESIDENZA);

        // Get all the anagraficaCandidatoList where provinciaResidenza not equals to UPDATED_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("provinciaResidenza.notEquals=" + UPDATED_PROVINCIA_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProvinciaResidenzaIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where provinciaResidenza in DEFAULT_PROVINCIA_RESIDENZA or UPDATED_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("provinciaResidenza.in=" + DEFAULT_PROVINCIA_RESIDENZA + "," + UPDATED_PROVINCIA_RESIDENZA);

        // Get all the anagraficaCandidatoList where provinciaResidenza equals to UPDATED_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("provinciaResidenza.in=" + UPDATED_PROVINCIA_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProvinciaResidenzaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where provinciaResidenza is not null
        defaultAnagraficaCandidatoShouldBeFound("provinciaResidenza.specified=true");

        // Get all the anagraficaCandidatoList where provinciaResidenza is null
        defaultAnagraficaCandidatoShouldNotBeFound("provinciaResidenza.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProvinciaResidenzaContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where provinciaResidenza contains DEFAULT_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("provinciaResidenza.contains=" + DEFAULT_PROVINCIA_RESIDENZA);

        // Get all the anagraficaCandidatoList where provinciaResidenza contains UPDATED_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("provinciaResidenza.contains=" + UPDATED_PROVINCIA_RESIDENZA);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByProvinciaResidenzaNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where provinciaResidenza does not contain DEFAULT_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldNotBeFound("provinciaResidenza.doesNotContain=" + DEFAULT_PROVINCIA_RESIDENZA);

        // Get all the anagraficaCandidatoList where provinciaResidenza does not contain UPDATED_PROVINCIA_RESIDENZA
        defaultAnagraficaCandidatoShouldBeFound("provinciaResidenza.doesNotContain=" + UPDATED_PROVINCIA_RESIDENZA);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNoteIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where note equals to DEFAULT_NOTE
        defaultAnagraficaCandidatoShouldBeFound("note.equals=" + DEFAULT_NOTE);

        // Get all the anagraficaCandidatoList where note equals to UPDATED_NOTE
        defaultAnagraficaCandidatoShouldNotBeFound("note.equals=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNoteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where note not equals to DEFAULT_NOTE
        defaultAnagraficaCandidatoShouldNotBeFound("note.notEquals=" + DEFAULT_NOTE);

        // Get all the anagraficaCandidatoList where note not equals to UPDATED_NOTE
        defaultAnagraficaCandidatoShouldBeFound("note.notEquals=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNoteIsInShouldWork() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where note in DEFAULT_NOTE or UPDATED_NOTE
        defaultAnagraficaCandidatoShouldBeFound("note.in=" + DEFAULT_NOTE + "," + UPDATED_NOTE);

        // Get all the anagraficaCandidatoList where note equals to UPDATED_NOTE
        defaultAnagraficaCandidatoShouldNotBeFound("note.in=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where note is not null
        defaultAnagraficaCandidatoShouldBeFound("note.specified=true");

        // Get all the anagraficaCandidatoList where note is null
        defaultAnagraficaCandidatoShouldNotBeFound("note.specified=false");
    }
                @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNoteContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where note contains DEFAULT_NOTE
        defaultAnagraficaCandidatoShouldBeFound("note.contains=" + DEFAULT_NOTE);

        // Get all the anagraficaCandidatoList where note contains UPDATED_NOTE
        defaultAnagraficaCandidatoShouldNotBeFound("note.contains=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByNoteNotContainsSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);

        // Get all the anagraficaCandidatoList where note does not contain DEFAULT_NOTE
        defaultAnagraficaCandidatoShouldNotBeFound("note.doesNotContain=" + DEFAULT_NOTE);

        // Get all the anagraficaCandidatoList where note does not contain UPDATED_NOTE
        defaultAnagraficaCandidatoShouldBeFound("note.doesNotContain=" + UPDATED_NOTE);
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCompetenzeLngIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        CompetenzeLng competenzeLng = CompetenzeLngResourceIT.createEntity(em);
        em.persist(competenzeLng);
        em.flush();
        anagraficaCandidato.addCompetenzeLng(competenzeLng);
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Long competenzeLngId = competenzeLng.getId();

        // Get all the anagraficaCandidatoList where competenzeLng equals to competenzeLngId
        defaultAnagraficaCandidatoShouldBeFound("competenzeLngId.equals=" + competenzeLngId);

        // Get all the anagraficaCandidatoList where competenzeLng equals to competenzeLngId + 1
        defaultAnagraficaCandidatoShouldNotBeFound("competenzeLngId.equals=" + (competenzeLngId + 1));
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByTitoloStudioIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        TitoloStudio titoloStudio = TitoloStudioResourceIT.createEntity(em);
        em.persist(titoloStudio);
        em.flush();
        anagraficaCandidato.addTitoloStudio(titoloStudio);
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Long titoloStudioId = titoloStudio.getId();

        // Get all the anagraficaCandidatoList where titoloStudio equals to titoloStudioId
        defaultAnagraficaCandidatoShouldBeFound("titoloStudioId.equals=" + titoloStudioId);

        // Get all the anagraficaCandidatoList where titoloStudio equals to titoloStudioId + 1
        defaultAnagraficaCandidatoShouldNotBeFound("titoloStudioId.equals=" + (titoloStudioId + 1));
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCurriculumIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Curriculum curriculum = CurriculumResourceIT.createEntity(em);
        em.persist(curriculum);
        em.flush();
        anagraficaCandidato.addCurriculum(curriculum);
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Long curriculumId = curriculum.getId();

        // Get all the anagraficaCandidatoList where curriculum equals to curriculumId
        defaultAnagraficaCandidatoShouldBeFound("curriculumId.equals=" + curriculumId);

        // Get all the anagraficaCandidatoList where curriculum equals to curriculumId + 1
        defaultAnagraficaCandidatoShouldNotBeFound("curriculumId.equals=" + (curriculumId + 1));
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCompetenzaIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Competenza competenza = CompetenzaResourceIT.createEntity(em);
        em.persist(competenza);
        em.flush();
        anagraficaCandidato.addCompetenza(competenza);
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Long competenzaId = competenza.getId();

        // Get all the anagraficaCandidatoList where competenza equals to competenzaId
        defaultAnagraficaCandidatoShouldBeFound("competenzaId.equals=" + competenzaId);

        // Get all the anagraficaCandidatoList where competenza equals to competenzaId + 1
        defaultAnagraficaCandidatoShouldNotBeFound("competenzaId.equals=" + (competenzaId + 1));
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByDichiarazioniIsEqualToSomething() throws Exception {
        // Initialize the database
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        DichiarazioniObligatorie dichiarazioni = DichiarazioniObligatorieResourceIT.createEntity(em);
        em.persist(dichiarazioni);
        em.flush();
        anagraficaCandidato.addDichiarazioni(dichiarazioni);
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Long dichiarazioniId = dichiarazioni.getId();

        // Get all the anagraficaCandidatoList where dichiarazioni equals to dichiarazioniId
        defaultAnagraficaCandidatoShouldBeFound("dichiarazioniId.equals=" + dichiarazioniId);

        // Get all the anagraficaCandidatoList where dichiarazioni equals to dichiarazioniId + 1
        defaultAnagraficaCandidatoShouldNotBeFound("dichiarazioniId.equals=" + (dichiarazioniId + 1));
    }


    @Test
    @Transactional
    public void getAllAnagraficaCandidatoesByCandidatoIsEqualToSomething() throws Exception {
        // Get already existing entity
        Candidato candidato = anagraficaCandidato.getCandidato();
        anagraficaCandidatoRepository.saveAndFlush(anagraficaCandidato);
        Long candidatoId = candidato.getId();

        // Get all the anagraficaCandidatoList where candidato equals to candidatoId
        defaultAnagraficaCandidatoShouldBeFound("candidatoId.equals=" + candidatoId);

        // Get all the anagraficaCandidatoList where candidato equals to candidatoId + 1
        defaultAnagraficaCandidatoShouldNotBeFound("candidatoId.equals=" + (candidatoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAnagraficaCandidatoShouldBeFound(String filter) throws Exception {
        restAnagraficaCandidatoMockMvc.perform(get("/api/anagrafica-candidatoes?sort=id,desc&" + filter))
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

        // Check, that the count call also returns 1
        restAnagraficaCandidatoMockMvc.perform(get("/api/anagrafica-candidatoes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAnagraficaCandidatoShouldNotBeFound(String filter) throws Exception {
        restAnagraficaCandidatoMockMvc.perform(get("/api/anagrafica-candidatoes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAnagraficaCandidatoMockMvc.perform(get("/api/anagrafica-candidatoes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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

        restAnagraficaCandidatoMockMvc.perform(put("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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
        restAnagraficaCandidatoMockMvc.perform(put("/api/anagrafica-candidatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
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
        restAnagraficaCandidatoMockMvc.perform(delete("/api/anagrafica-candidatoes/{id}", anagraficaCandidato.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnagraficaCandidato> anagraficaCandidatoList = anagraficaCandidatoRepository.findAll();
        assertThat(anagraficaCandidatoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
