package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class LinguaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lingua.class);
        Lingua lingua1 = new Lingua();
        lingua1.setId(1L);
        Lingua lingua2 = new Lingua();
        lingua2.setId(lingua1.getId());
        assertThat(lingua1).isEqualTo(lingua2);
        lingua2.setId(2L);
        assertThat(lingua1).isNotEqualTo(lingua2);
        lingua1.setId(null);
        assertThat(lingua1).isNotEqualTo(lingua2);
    }
}
