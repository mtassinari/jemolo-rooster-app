package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.TitoloStudio;
import it.laziocrea.jemoloapp.domain.AnagraficaCandidato;
import it.laziocrea.jemoloapp.repository.TitoloStudioRepository;
import it.laziocrea.jemoloapp.service.TitoloStudioService;
import it.laziocrea.jemoloapp.service.dto.TitoloStudioDTO;
import it.laziocrea.jemoloapp.service.mapper.TitoloStudioMapper;

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
 * Integration tests for the {@link TitoloStudioResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TitoloStudioResourceIT {

    private static final String DEFAULT_TIPOLOGIA = "AAAAAAAAAA";
    private static final String UPDATED_TIPOLOGIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_CONSEGUIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_CONSEGUIMENTO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNO = 1;
    private static final Integer UPDATED_ANNO = 2;

    private static final String DEFAULT_VOTO = "AAAAAAAAAA";
    private static final String UPDATED_VOTO = "BBBBBBBBBB";

    @Autowired
    private TitoloStudioRepository titoloStudioRepository;

    @Autowired
    private TitoloStudioMapper titoloStudioMapper;

    @Autowired
    private TitoloStudioService titoloStudioService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTitoloStudioMockMvc;

    private TitoloStudio titoloStudio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TitoloStudio createEntity(EntityManager em) {
        TitoloStudio titoloStudio = new TitoloStudio()
            .tipologia(DEFAULT_TIPOLOGIA)
            .descrizione(DEFAULT_DESCRIZIONE)
            .conseguimento(DEFAULT_CONSEGUIMENTO)
            .anno(DEFAULT_ANNO)
            .voto(DEFAULT_VOTO);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        titoloStudio.setAnagrafica(anagraficaCandidato);
        return titoloStudio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TitoloStudio createUpdatedEntity(EntityManager em) {
        TitoloStudio titoloStudio = new TitoloStudio()
            .tipologia(UPDATED_TIPOLOGIA)
            .descrizione(UPDATED_DESCRIZIONE)
            .conseguimento(UPDATED_CONSEGUIMENTO)
            .anno(UPDATED_ANNO)
            .voto(UPDATED_VOTO);
        // Add required entity
        AnagraficaCandidato anagraficaCandidato;
        if (TestUtil.findAll(em, AnagraficaCandidato.class).isEmpty()) {
            anagraficaCandidato = AnagraficaCandidatoResourceIT.createUpdatedEntity(em);
            em.persist(anagraficaCandidato);
            em.flush();
        } else {
            anagraficaCandidato = TestUtil.findAll(em, AnagraficaCandidato.class).get(0);
        }
        titoloStudio.setAnagrafica(anagraficaCandidato);
        return titoloStudio;
    }

    @BeforeEach
    public void initTest() {
        titoloStudio = createEntity(em);
    }

    @Test
    @Transactional
    public void createTitoloStudio() throws Exception {
        int databaseSizeBeforeCreate = titoloStudioRepository.findAll().size();
        // Create the TitoloStudio
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);
        restTitoloStudioMockMvc.perform(post("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isCreated());

        // Validate the TitoloStudio in the database
        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeCreate + 1);
        TitoloStudio testTitoloStudio = titoloStudioList.get(titoloStudioList.size() - 1);
        assertThat(testTitoloStudio.getTipologia()).isEqualTo(DEFAULT_TIPOLOGIA);
        assertThat(testTitoloStudio.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testTitoloStudio.getConseguimento()).isEqualTo(DEFAULT_CONSEGUIMENTO);
        assertThat(testTitoloStudio.getAnno()).isEqualTo(DEFAULT_ANNO);
        assertThat(testTitoloStudio.getVoto()).isEqualTo(DEFAULT_VOTO);
    }

    @Test
    @Transactional
    public void createTitoloStudioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = titoloStudioRepository.findAll().size();

        // Create the TitoloStudio with an existing ID
        titoloStudio.setId(1L);
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTitoloStudioMockMvc.perform(post("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TitoloStudio in the database
        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTipologiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = titoloStudioRepository.findAll().size();
        // set the field null
        titoloStudio.setTipologia(null);

        // Create the TitoloStudio, which fails.
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);


        restTitoloStudioMockMvc.perform(post("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isBadRequest());

        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescrizioneIsRequired() throws Exception {
        int databaseSizeBeforeTest = titoloStudioRepository.findAll().size();
        // set the field null
        titoloStudio.setDescrizione(null);

        // Create the TitoloStudio, which fails.
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);


        restTitoloStudioMockMvc.perform(post("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isBadRequest());

        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConseguimentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = titoloStudioRepository.findAll().size();
        // set the field null
        titoloStudio.setConseguimento(null);

        // Create the TitoloStudio, which fails.
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);


        restTitoloStudioMockMvc.perform(post("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isBadRequest());

        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = titoloStudioRepository.findAll().size();
        // set the field null
        titoloStudio.setAnno(null);

        // Create the TitoloStudio, which fails.
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);


        restTitoloStudioMockMvc.perform(post("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isBadRequest());

        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVotoIsRequired() throws Exception {
        int databaseSizeBeforeTest = titoloStudioRepository.findAll().size();
        // set the field null
        titoloStudio.setVoto(null);

        // Create the TitoloStudio, which fails.
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);


        restTitoloStudioMockMvc.perform(post("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isBadRequest());

        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTitoloStudios() throws Exception {
        // Initialize the database
        titoloStudioRepository.saveAndFlush(titoloStudio);

        // Get all the titoloStudioList
        restTitoloStudioMockMvc.perform(get("/api/titolo-studios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(titoloStudio.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipologia").value(hasItem(DEFAULT_TIPOLOGIA)))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE)))
            .andExpect(jsonPath("$.[*].conseguimento").value(hasItem(DEFAULT_CONSEGUIMENTO)))
            .andExpect(jsonPath("$.[*].anno").value(hasItem(DEFAULT_ANNO)))
            .andExpect(jsonPath("$.[*].voto").value(hasItem(DEFAULT_VOTO)));
    }
    
    @Test
    @Transactional
    public void getTitoloStudio() throws Exception {
        // Initialize the database
        titoloStudioRepository.saveAndFlush(titoloStudio);

        // Get the titoloStudio
        restTitoloStudioMockMvc.perform(get("/api/titolo-studios/{id}", titoloStudio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(titoloStudio.getId().intValue()))
            .andExpect(jsonPath("$.tipologia").value(DEFAULT_TIPOLOGIA))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE))
            .andExpect(jsonPath("$.conseguimento").value(DEFAULT_CONSEGUIMENTO))
            .andExpect(jsonPath("$.anno").value(DEFAULT_ANNO))
            .andExpect(jsonPath("$.voto").value(DEFAULT_VOTO));
    }
    @Test
    @Transactional
    public void getNonExistingTitoloStudio() throws Exception {
        // Get the titoloStudio
        restTitoloStudioMockMvc.perform(get("/api/titolo-studios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTitoloStudio() throws Exception {
        // Initialize the database
        titoloStudioRepository.saveAndFlush(titoloStudio);

        int databaseSizeBeforeUpdate = titoloStudioRepository.findAll().size();

        // Update the titoloStudio
        TitoloStudio updatedTitoloStudio = titoloStudioRepository.findById(titoloStudio.getId()).get();
        // Disconnect from session so that the updates on updatedTitoloStudio are not directly saved in db
        em.detach(updatedTitoloStudio);
        updatedTitoloStudio
            .tipologia(UPDATED_TIPOLOGIA)
            .descrizione(UPDATED_DESCRIZIONE)
            .conseguimento(UPDATED_CONSEGUIMENTO)
            .anno(UPDATED_ANNO)
            .voto(UPDATED_VOTO);
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(updatedTitoloStudio);

        restTitoloStudioMockMvc.perform(put("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isOk());

        // Validate the TitoloStudio in the database
        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeUpdate);
        TitoloStudio testTitoloStudio = titoloStudioList.get(titoloStudioList.size() - 1);
        assertThat(testTitoloStudio.getTipologia()).isEqualTo(UPDATED_TIPOLOGIA);
        assertThat(testTitoloStudio.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testTitoloStudio.getConseguimento()).isEqualTo(UPDATED_CONSEGUIMENTO);
        assertThat(testTitoloStudio.getAnno()).isEqualTo(UPDATED_ANNO);
        assertThat(testTitoloStudio.getVoto()).isEqualTo(UPDATED_VOTO);
    }

    @Test
    @Transactional
    public void updateNonExistingTitoloStudio() throws Exception {
        int databaseSizeBeforeUpdate = titoloStudioRepository.findAll().size();

        // Create the TitoloStudio
        TitoloStudioDTO titoloStudioDTO = titoloStudioMapper.toDto(titoloStudio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTitoloStudioMockMvc.perform(put("/api/titolo-studios").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(titoloStudioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TitoloStudio in the database
        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTitoloStudio() throws Exception {
        // Initialize the database
        titoloStudioRepository.saveAndFlush(titoloStudio);

        int databaseSizeBeforeDelete = titoloStudioRepository.findAll().size();

        // Delete the titoloStudio
        restTitoloStudioMockMvc.perform(delete("/api/titolo-studios/{id}", titoloStudio.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TitoloStudio> titoloStudioList = titoloStudioRepository.findAll();
        assertThat(titoloStudioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
