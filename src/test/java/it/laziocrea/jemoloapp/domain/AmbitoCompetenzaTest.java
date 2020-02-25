package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class AmbitoCompetenzaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AmbitoCompetenza.class);
        AmbitoCompetenza ambitoCompetenza1 = new AmbitoCompetenza();
        ambitoCompetenza1.setId(1L);
        AmbitoCompetenza ambitoCompetenza2 = new AmbitoCompetenza();
        ambitoCompetenza2.setId(ambitoCompetenza1.getId());
        assertThat(ambitoCompetenza1).isEqualTo(ambitoCompetenza2);
        ambitoCompetenza2.setId(2L);
        assertThat(ambitoCompetenza1).isNotEqualTo(ambitoCompetenza2);
        ambitoCompetenza1.setId(null);
        assertThat(ambitoCompetenza1).isNotEqualTo(ambitoCompetenza2);
    }
}
