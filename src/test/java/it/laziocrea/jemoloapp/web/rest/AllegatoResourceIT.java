package it.laziocrea.jemoloapp.web.rest;

import it.laziocrea.jemoloapp.JemoloRoosterApp;
import it.laziocrea.jemoloapp.domain.Allegato;
import it.laziocrea.jemoloapp.domain.Curriculum;
import it.laziocrea.jemoloapp.repository.AllegatoRepository;
import it.laziocrea.jemoloapp.service.AllegatoService;
import it.laziocrea.jemoloapp.service.dto.AllegatoDTO;
import it.laziocrea.jemoloapp.service.mapper.AllegatoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AllegatoResource} REST controller.
 */
@SpringBootTest(classes = JemoloRoosterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AllegatoResourceIT {

    private static final byte[] DEFAULT_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DATA_CONTENT_TYPE = "image/png";

    @Autowired
    private AllegatoRepository allegatoRepository;

    @Autowired
    private AllegatoMapper allegatoMapper;

    @Autowired
    private AllegatoService allegatoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAllegatoMockMvc;

    private Allegato allegato;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Allegato createEntity(EntityManager em) {
        Allegato allegato = new Allegato()
            .data(DEFAULT_DATA)
            .dataContentType(DEFAULT_DATA_CONTENT_TYPE);
        // Add required entity
        Curriculum curriculum;
        if (TestUtil.findAll(em, Curriculum.class).isEmpty()) {
            curriculum = CurriculumResourceIT.createEntity(em);
            em.persist(curriculum);
            em.flush();
        } else {
            curriculum = TestUtil.findAll(em, Curriculum.class).get(0);
        }
        allegato.setCurriculum(curriculum);
        return allegato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Allegato createUpdatedEntity(EntityManager em) {
        Allegato allegato = new Allegato()
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);
        // Add required entity
        Curriculum curriculum;
        if (TestUtil.findAll(em, Curriculum.class).isEmpty()) {
            curriculum = CurriculumResourceIT.createUpdatedEntity(em);
            em.persist(curriculum);
            em.flush();
        } else {
            curriculum = TestUtil.findAll(em, Curriculum.class).get(0);
        }
        allegato.setCurriculum(curriculum);
        return allegato;
    }

    @BeforeEach
    public void initTest() {
        allegato = createEntity(em);
    }

    @Test
    @Transactional
    public void createAllegato() throws Exception {
        int databaseSizeBeforeCreate = allegatoRepository.findAll().size();
        // Create the Allegato
        AllegatoDTO allegatoDTO = allegatoMapper.toDto(allegato);
        restAllegatoMockMvc.perform(post("/api/allegatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoDTO)))
            .andExpect(status().isCreated());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeCreate + 1);
        Allegato testAllegato = allegatoList.get(allegatoList.size() - 1);
        assertThat(testAllegato.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAllegato.getDataContentType()).isEqualTo(DEFAULT_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAllegatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = allegatoRepository.findAll().size();

        // Create the Allegato with an existing ID
        allegato.setId(1L);
        AllegatoDTO allegatoDTO = allegatoMapper.toDto(allegato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAllegatoMockMvc.perform(post("/api/allegatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAllegatoes() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        // Get all the allegatoList
        restAllegatoMockMvc.perform(get("/api/allegatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allegato.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataContentType").value(hasItem(DEFAULT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATA))));
    }
    
    @Test
    @Transactional
    public void getAllegato() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        // Get the allegato
        restAllegatoMockMvc.perform(get("/api/allegatoes/{id}", allegato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(allegato.getId().intValue()))
            .andExpect(jsonPath("$.dataContentType").value(DEFAULT_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.data").value(Base64Utils.encodeToString(DEFAULT_DATA)));
    }
    @Test
    @Transactional
    public void getNonExistingAllegato() throws Exception {
        // Get the allegato
        restAllegatoMockMvc.perform(get("/api/allegatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAllegato() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        int databaseSizeBeforeUpdate = allegatoRepository.findAll().size();

        // Update the allegato
        Allegato updatedAllegato = allegatoRepository.findById(allegato.getId()).get();
        // Disconnect from session so that the updates on updatedAllegato are not directly saved in db
        em.detach(updatedAllegato);
        updatedAllegato
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);
        AllegatoDTO allegatoDTO = allegatoMapper.toDto(updatedAllegato);

        restAllegatoMockMvc.perform(put("/api/allegatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoDTO)))
            .andExpect(status().isOk());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeUpdate);
        Allegato testAllegato = allegatoList.get(allegatoList.size() - 1);
        assertThat(testAllegato.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAllegato.getDataContentType()).isEqualTo(UPDATED_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAllegato() throws Exception {
        int databaseSizeBeforeUpdate = allegatoRepository.findAll().size();

        // Create the Allegato
        AllegatoDTO allegatoDTO = allegatoMapper.toDto(allegato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllegatoMockMvc.perform(put("/api/allegatoes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAllegato() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        int databaseSizeBeforeDelete = allegatoRepository.findAll().size();

        // Delete the allegato
        restAllegatoMockMvc.perform(delete("/api/allegatoes/{id}", allegato.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
