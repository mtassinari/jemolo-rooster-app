package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class DichiarazioniObligatorieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DichiarazioniObligatorie.class);
        DichiarazioniObligatorie dichiarazioniObligatorie1 = new DichiarazioniObligatorie();
        dichiarazioniObligatorie1.setId(1L);
        DichiarazioniObligatorie dichiarazioniObligatorie2 = new DichiarazioniObligatorie();
        dichiarazioniObligatorie2.setId(dichiarazioniObligatorie1.getId());
        assertThat(dichiarazioniObligatorie1).isEqualTo(dichiarazioniObligatorie2);
        dichiarazioniObligatorie2.setId(2L);
        assertThat(dichiarazioniObligatorie1).isNotEqualTo(dichiarazioniObligatorie2);
        dichiarazioniObligatorie1.setId(null);
        assertThat(dichiarazioniObligatorie1).isNotEqualTo(dichiarazioniObligatorie2);
    }
}
