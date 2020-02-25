package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class AllegatoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Allegato.class);
        Allegato allegato1 = new Allegato();
        allegato1.setId(1L);
        Allegato allegato2 = new Allegato();
        allegato2.setId(allegato1.getId());
        assertThat(allegato1).isEqualTo(allegato2);
        allegato2.setId(2L);
        assertThat(allegato1).isNotEqualTo(allegato2);
        allegato1.setId(null);
        assertThat(allegato1).isNotEqualTo(allegato2);
    }
}
