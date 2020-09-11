package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompetenzeLngMapperTest {

    private CompetenzeLngMapper competenzeLngMapper;

    @BeforeEach
    public void setUp() {
        competenzeLngMapper = new CompetenzeLngMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(competenzeLngMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(competenzeLngMapper.fromId(null)).isNull();
    }
}
