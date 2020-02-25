package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LinguaMapperTest {

    private LinguaMapper linguaMapper;

    @BeforeEach
    public void setUp() {
        linguaMapper = new LinguaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(linguaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(linguaMapper.fromId(null)).isNull();
    }
}
