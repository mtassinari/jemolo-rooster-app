package it.laziocrea.jemoloapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CurriculumMapperTest {

    private CurriculumMapper curriculumMapper;

    @BeforeEach
    public void setUp() {
        curriculumMapper = new CurriculumMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(curriculumMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(curriculumMapper.fromId(null)).isNull();
    }
}
