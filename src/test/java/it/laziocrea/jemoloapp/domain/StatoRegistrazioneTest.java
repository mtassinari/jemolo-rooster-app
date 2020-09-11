package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class StatoRegistrazioneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatoRegistrazione.class);
        StatoRegistrazione statoRegistrazione1 = new StatoRegistrazione();
        statoRegistrazione1.setId(1L);
        StatoRegistrazione statoRegistrazione2 = new StatoRegistrazione();
        statoRegistrazione2.setId(statoRegistrazione1.getId());
        assertThat(statoRegistrazione1).isEqualTo(statoRegistrazione2);
        statoRegistrazione2.setId(2L);
        assertThat(statoRegistrazione1).isNotEqualTo(statoRegistrazione2);
        statoRegistrazione1.setId(null);
        assertThat(statoRegistrazione1).isNotEqualTo(statoRegistrazione2);
    }
}
