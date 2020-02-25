package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnagraficaCandidatoMapperTest {

    private AnagraficaCandidatoMapper anagraficaCandidatoMapper;

    @BeforeEach
    public void setUp() {
        anagraficaCandidatoMapper = new AnagraficaCandidatoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(anagraficaCandidatoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(anagraficaCandidatoMapper.fromId(null)).isNull();
    }
}
