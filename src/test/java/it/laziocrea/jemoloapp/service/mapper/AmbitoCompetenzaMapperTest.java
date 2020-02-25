package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AmbitoCompetenzaMapperTest {

    private AmbitoCompetenzaMapper ambitoCompetenzaMapper;

    @BeforeEach
    public void setUp() {
        ambitoCompetenzaMapper = new AmbitoCompetenzaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ambitoCompetenzaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ambitoCompetenzaMapper.fromId(null)).isNull();
    }
}
