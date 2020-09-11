package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CompetenzaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Competenza.class);
        Competenza competenza1 = new Competenza();
        competenza1.setId(1L);
        Competenza competenza2 = new Competenza();
        competenza2.setId(competenza1.getId());
        assertThat(competenza1).isEqualTo(competenza2);
        competenza2.setId(2L);
        assertThat(competenza1).isNotEqualTo(competenza2);
        competenza1.setId(null);
        assertThat(competenza1).isNotEqualTo(competenza2);
    }
}
