package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TitoloStudioMapperTest {

    private TitoloStudioMapper titoloStudioMapper;

    @BeforeEach
    public void setUp() {
        titoloStudioMapper = new TitoloStudioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(titoloStudioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(titoloStudioMapper.fromId(null)).isNull();
    }
}
