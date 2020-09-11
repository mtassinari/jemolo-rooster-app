package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CurriculumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Curriculum.class);
        Curriculum curriculum1 = new Curriculum();
        curriculum1.setId(1L);
        Curriculum curriculum2 = new Curriculum();
        curriculum2.setId(curriculum1.getId());
        assertThat(curriculum1).isEqualTo(curriculum2);
        curriculum2.setId(2L);
        assertThat(curriculum1).isNotEqualTo(curriculum2);
        curriculum1.setId(null);
        assertThat(curriculum1).isNotEqualTo(curriculum2);
    }
}
