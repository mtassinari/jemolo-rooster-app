package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.Comune;
import it.laziocrea.jemoloapp.repository.ComuneRepository;

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
 * Integration tests for the {@link ComuneResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComuneResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_PROVINCIA = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA_PROVINCIA = "BBBBBBBBBB";

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComuneMockMvc;

    private Comune comune;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comune createEntity(EntityManager em) {
        Comune comune = new Comune()
            .nome(DEFAULT_NOME)
            .nomeProvincia(DEFAULT_NOME_PROVINCIA)
            .siglaProvincia(DEFAULT_SIGLA_PROVINCIA);
        return comune;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comune createUpdatedEntity(EntityManager em) {
        Comune comune = new Comune()
            .nome(UPDATED_NOME)
            .nomeProvincia(UPDATED_NOME_PROVINCIA)
            .siglaProvincia(UPDATED_SIGLA_PROVINCIA);
        return comune;
    }

    @BeforeEach
    public void initTest() {
        comune = createEntity(em);
    }

    @Test
    @Transactional
    public void createComune() throws Exception {
        int databaseSizeBeforeCreate = comuneRepository.findAll().size();
        // Create the Comune
        restComuneMockMvc.perform(post("/api/comunes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comune)))
            .andExpect(status().isCreated());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeCreate + 1);
        Comune testComune = comuneList.get(comuneList.size() - 1);
        assertThat(testComune.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testComune.getNomeProvincia()).isEqualTo(DEFAULT_NOME_PROVINCIA);
        assertThat(testComune.getSiglaProvincia()).isEqualTo(DEFAULT_SIGLA_PROVINCIA);
    }

    @Test
    @Transactional
    public void createComuneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comuneRepository.findAll().size();

        // Create the Comune with an existing ID
        comune.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComuneMockMvc.perform(post("/api/comunes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comune)))
            .andExpect(status().isBadRequest());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = comuneRepository.findAll().size();
        // set the field null
        comune.setNome(null);

        // Create the Comune, which fails.


        restComuneMockMvc.perform(post("/api/comunes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comune)))
            .andExpect(status().isBadRequest());

        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeProvinciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = comuneRepository.findAll().size();
        // set the field null
        comune.setNomeProvincia(null);

        // Create the Comune, which fails.


        restComuneMockMvc.perform(post("/api/comunes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comune)))
            .andExpect(status().isBadRequest());

        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSiglaProvinciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = comuneRepository.findAll().size();
        // set the field null
        comune.setSiglaProvincia(null);

        // Create the Comune, which fails.


        restComuneMockMvc.perform(post("/api/comunes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comune)))
            .andExpect(status().isBadRequest());

        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllComunes() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        // Get all the comuneList
        restComuneMockMvc.perform(get("/api/comunes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comune.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].nomeProvincia").value(hasItem(DEFAULT_NOME_PROVINCIA)))
            .andExpect(jsonPath("$.[*].siglaProvincia").value(hasItem(DEFAULT_SIGLA_PROVINCIA)));
    }
    
    @Test
    @Transactional
    public void getComune() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        // Get the comune
        restComuneMockMvc.perform(get("/api/comunes/{id}", comune.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(comune.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.nomeProvincia").value(DEFAULT_NOME_PROVINCIA))
            .andExpect(jsonPath("$.siglaProvincia").value(DEFAULT_SIGLA_PROVINCIA));
    }
    @Test
    @Transactional
    public void getNonExistingComune() throws Exception {
        // Get the comune
        restComuneMockMvc.perform(get("/api/comunes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComune() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        int databaseSizeBeforeUpdate = comuneRepository.findAll().size();

        // Update the comune
        Comune updatedComune = comuneRepository.findById(comune.getId()).get();
        // Disconnect from session so that the updates on updatedComune are not directly saved in db
        em.detach(updatedComune);
        updatedComune
            .nome(UPDATED_NOME)
            .nomeProvincia(UPDATED_NOME_PROVINCIA)
            .siglaProvincia(UPDATED_SIGLA_PROVINCIA);

        restComuneMockMvc.perform(put("/api/comunes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedComune)))
            .andExpect(status().isOk());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeUpdate);
        Comune testComune = comuneList.get(comuneList.size() - 1);
        assertThat(testComune.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testComune.getNomeProvincia()).isEqualTo(UPDATED_NOME_PROVINCIA);
        assertThat(testComune.getSiglaProvincia()).isEqualTo(UPDATED_SIGLA_PROVINCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingComune() throws Exception {
        int databaseSizeBeforeUpdate = comuneRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComuneMockMvc.perform(put("/api/comunes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comune)))
            .andExpect(status().isBadRequest());

        // Validate the Comune in the database
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComune() throws Exception {
        // Initialize the database
        comuneRepository.saveAndFlush(comune);

        int databaseSizeBeforeDelete = comuneRepository.findAll().size();

        // Delete the comune
        restComuneMockMvc.perform(delete("/api/comunes/{id}", comune.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Comune> comuneList = comuneRepository.findAll();
        assertThat(comuneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
