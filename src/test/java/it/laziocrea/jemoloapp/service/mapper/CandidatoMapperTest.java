package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CandidatoMapperTest {

    private CandidatoMapper candidatoMapper;

    @BeforeEach
    public void setUp() {
        candidatoMapper = new CandidatoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(candidatoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(candidatoMapper.fromId(null)).isNull();
    }
}
