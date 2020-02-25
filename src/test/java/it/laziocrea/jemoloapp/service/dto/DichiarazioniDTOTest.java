package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class DichiarazioniDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DichiarazioniDTO.class);
        DichiarazioniDTO dichiarazioniDTO1 = new DichiarazioniDTO();
        dichiarazioniDTO1.setId(1L);
        DichiarazioniDTO dichiarazioniDTO2 = new DichiarazioniDTO();
        assertThat(dichiarazioniDTO1).isNotEqualTo(dichiarazioniDTO2);
        dichiarazioniDTO2.setId(dichiarazioniDTO1.getId());
        assertThat(dichiarazioniDTO1).isEqualTo(dichiarazioniDTO2);
        dichiarazioniDTO2.setId(2L);
        assertThat(dichiarazioniDTO1).isNotEqualTo(dichiarazioniDTO2);
        dichiarazioniDTO1.setId(null);
        assertThat(dichiarazioniDTO1).isNotEqualTo(dichiarazioniDTO2);
    }
}
