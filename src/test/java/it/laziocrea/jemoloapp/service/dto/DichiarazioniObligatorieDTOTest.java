package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class DichiarazioniObligatorieDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DichiarazioniObligatorieDTO.class);
        DichiarazioniObligatorieDTO dichiarazioniObligatorieDTO1 = new DichiarazioniObligatorieDTO();
        dichiarazioniObligatorieDTO1.setId(1L);
        DichiarazioniObligatorieDTO dichiarazioniObligatorieDTO2 = new DichiarazioniObligatorieDTO();
        assertThat(dichiarazioniObligatorieDTO1).isNotEqualTo(dichiarazioniObligatorieDTO2);
        dichiarazioniObligatorieDTO2.setId(dichiarazioniObligatorieDTO1.getId());
        assertThat(dichiarazioniObligatorieDTO1).isEqualTo(dichiarazioniObligatorieDTO2);
        dichiarazioniObligatorieDTO2.setId(2L);
        assertThat(dichiarazioniObligatorieDTO1).isNotEqualTo(dichiarazioniObligatorieDTO2);
        dichiarazioniObligatorieDTO1.setId(null);
        assertThat(dichiarazioniObligatorieDTO1).isNotEqualTo(dichiarazioniObligatorieDTO2);
    }
}
