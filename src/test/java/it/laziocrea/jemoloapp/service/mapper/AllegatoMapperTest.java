package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AllegatoMapperTest {

    private AllegatoMapper allegatoMapper;
    
    @BeforeEach
    public void setUp() {
        allegatoMapper = new AllegatoMapperImpl();
    }
    
    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(allegatoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(allegatoMapper.fromId(null)).isNull();
    }
}
