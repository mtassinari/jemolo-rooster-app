package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompetenzaMapperTest {

    private CompetenzaMapper competenzaMapper;

    @BeforeEach
    public void setUp() {
        competenzaMapper = new CompetenzaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(competenzaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(competenzaMapper.fromId(null)).isNull();
    }
}
