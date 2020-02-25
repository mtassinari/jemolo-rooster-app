package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class DichiarazioniTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dichiarazioni.class);
        Dichiarazioni dichiarazioni1 = new Dichiarazioni();
        dichiarazioni1.setId(1L);
        Dichiarazioni dichiarazioni2 = new Dichiarazioni();
        dichiarazioni2.setId(dichiarazioni1.getId());
        assertThat(dichiarazioni1).isEqualTo(dichiarazioni2);
        dichiarazioni2.setId(2L);
        assertThat(dichiarazioni1).isNotEqualTo(dichiarazioni2);
        dichiarazioni1.setId(null);
        assertThat(dichiarazioni1).isNotEqualTo(dichiarazioni2);
    }
}
