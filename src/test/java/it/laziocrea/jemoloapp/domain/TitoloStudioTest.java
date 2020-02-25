package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class TitoloStudioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TitoloStudio.class);
        TitoloStudio titoloStudio1 = new TitoloStudio();
        titoloStudio1.setId(1L);
        TitoloStudio titoloStudio2 = new TitoloStudio();
        titoloStudio2.setId(titoloStudio1.getId());
        assertThat(titoloStudio1).isEqualTo(titoloStudio2);
        titoloStudio2.setId(2L);
        assertThat(titoloStudio1).isNotEqualTo(titoloStudio2);
        titoloStudio1.setId(null);
        assertThat(titoloStudio1).isNotEqualTo(titoloStudio2);
    }
}
