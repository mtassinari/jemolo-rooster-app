package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DichiarazioniMapperTest {

    private DichiarazioniMapper dichiarazioniMapper;

    @BeforeEach
    public void setUp() {
        dichiarazioniMapper = new DichiarazioniMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dichiarazioniMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dichiarazioniMapper.fromId(null)).isNull();
    }
}
