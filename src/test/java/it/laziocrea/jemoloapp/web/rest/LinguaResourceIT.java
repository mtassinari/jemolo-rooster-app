package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.Lingua;
import it.laziocrea.jemoloapp.repository.LinguaRepository;
import it.laziocrea.jemoloapp.service.LinguaService;
import it.laziocrea.jemoloapp.service.dto.LinguaDTO;
import it.laziocrea.jemoloapp.service.mapper.LinguaMapper;

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
 * Integration tests for the {@link LinguaResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LinguaResourceIT {

    private static final String DEFAULT_LINGUA = "AAAAAAAAAA";
    private static final String UPDATED_LINGUA = "BBBBBBBBBB";

    @Autowired
    private LinguaRepository linguaRepository;

    @Autowired
    private LinguaMapper linguaMapper;

    @Autowired
    private LinguaService linguaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLinguaMockMvc;

    private Lingua lingua;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lingua createEntity(EntityManager em) {
        Lingua lingua = new Lingua()
            .lingua(DEFAULT_LINGUA);
        return lingua;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lingua createUpdatedEntity(EntityManager em) {
        Lingua lingua = new Lingua()
            .lingua(UPDATED_LINGUA);
        return lingua;
    }

    @BeforeEach
    public void initTest() {
        lingua = createEntity(em);
    }

    @Test
    @Transactional
    public void createLingua() throws Exception {
        int databaseSizeBeforeCreate = linguaRepository.findAll().size();
        // Create the Lingua
        LinguaDTO linguaDTO = linguaMapper.toDto(lingua);
        restLinguaMockMvc.perform(post("/api/linguas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(linguaDTO)))
            .andExpect(status().isCreated());

        // Validate the Lingua in the database
        List<Lingua> linguaList = linguaRepository.findAll();
        assertThat(linguaList).hasSize(databaseSizeBeforeCreate + 1);
        Lingua testLingua = linguaList.get(linguaList.size() - 1);
        assertThat(testLingua.getLingua()).isEqualTo(DEFAULT_LINGUA);
    }

    @Test
    @Transactional
    public void createLinguaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = linguaRepository.findAll().size();

        // Create the Lingua with an existing ID
        lingua.setId(1L);
        LinguaDTO linguaDTO = linguaMapper.toDto(lingua);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinguaMockMvc.perform(post("/api/linguas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(linguaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lingua in the database
        List<Lingua> linguaList = linguaRepository.findAll();
        assertThat(linguaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLinguaIsRequired() throws Exception {
        int databaseSizeBeforeTest = linguaRepository.findAll().size();
        // set the field null
        lingua.setLingua(null);

        // Create the Lingua, which fails.
        LinguaDTO linguaDTO = linguaMapper.toDto(lingua);


        restLinguaMockMvc.perform(post("/api/linguas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(linguaDTO)))
            .andExpect(status().isBadRequest());

        List<Lingua> linguaList = linguaRepository.findAll();
        assertThat(linguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLinguas() throws Exception {
        // Initialize the database
        linguaRepository.saveAndFlush(lingua);

        // Get all the linguaList
        restLinguaMockMvc.perform(get("/api/linguas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lingua.getId().intValue())))
            .andExpect(jsonPath("$.[*].lingua").value(hasItem(DEFAULT_LINGUA)));
    }
    
    @Test
    @Transactional
    public void getLingua() throws Exception {
        // Initialize the database
        linguaRepository.saveAndFlush(lingua);

        // Get the lingua
        restLinguaMockMvc.perform(get("/api/linguas/{id}", lingua.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lingua.getId().intValue()))
            .andExpect(jsonPath("$.lingua").value(DEFAULT_LINGUA));
    }
    @Test
    @Transactional
    public void getNonExistingLingua() throws Exception {
        // Get the lingua
        restLinguaMockMvc.perform(get("/api/linguas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLingua() throws Exception {
        // Initialize the database
        linguaRepository.saveAndFlush(lingua);

        int databaseSizeBeforeUpdate = linguaRepository.findAll().size();

        // Update the lingua
        Lingua updatedLingua = linguaRepository.findById(lingua.getId()).get();
        // Disconnect from session so that the updates on updatedLingua are not directly saved in db
        em.detach(updatedLingua);
        updatedLingua
            .lingua(UPDATED_LINGUA);
        LinguaDTO linguaDTO = linguaMapper.toDto(updatedLingua);

        restLinguaMockMvc.perform(put("/api/linguas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(linguaDTO)))
            .andExpect(status().isOk());

        // Validate the Lingua in the database
        List<Lingua> linguaList = linguaRepository.findAll();
        assertThat(linguaList).hasSize(databaseSizeBeforeUpdate);
        Lingua testLingua = linguaList.get(linguaList.size() - 1);
        assertThat(testLingua.getLingua()).isEqualTo(UPDATED_LINGUA);
    }

    @Test
    @Transactional
    public void updateNonExistingLingua() throws Exception {
        int databaseSizeBeforeUpdate = linguaRepository.findAll().size();

        // Create the Lingua
        LinguaDTO linguaDTO = linguaMapper.toDto(lingua);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinguaMockMvc.perform(put("/api/linguas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(linguaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lingua in the database
        List<Lingua> linguaList = linguaRepository.findAll();
        assertThat(linguaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLingua() throws Exception {
        // Initialize the database
        linguaRepository.saveAndFlush(lingua);

        int databaseSizeBeforeDelete = linguaRepository.findAll().size();

        // Delete the lingua
        restLinguaMockMvc.perform(delete("/api/linguas/{id}", lingua.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lingua> linguaList = linguaRepository.findAll();
        assertThat(linguaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
