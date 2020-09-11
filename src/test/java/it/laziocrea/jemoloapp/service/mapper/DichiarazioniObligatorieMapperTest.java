package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DichiarazioniObligatorieMapperTest {

    private DichiarazioniObligatorieMapper dichiarazioniObligatorieMapper;

    @BeforeEach
    public void setUp() {
        dichiarazioniObligatorieMapper = new DichiarazioniObligatorieMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dichiarazioniObligatorieMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dichiarazioniObligatorieMapper.fromId(null)).isNull();
    }
}
