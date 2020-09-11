package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatoRegistrazioneMapperTest {

    private StatoRegistrazioneMapper statoRegistrazioneMapper;

    @BeforeEach
    public void setUp() {
        statoRegistrazioneMapper = new StatoRegistrazioneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(statoRegistrazioneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(statoRegistrazioneMapper.fromId(null)).isNull();
    }
}
