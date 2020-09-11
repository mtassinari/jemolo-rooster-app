package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class StatoRegistrazioneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatoRegistrazioneDTO.class);
        StatoRegistrazioneDTO statoRegistrazioneDTO1 = new StatoRegistrazioneDTO();
        statoRegistrazioneDTO1.setId(1L);
        StatoRegistrazioneDTO statoRegistrazioneDTO2 = new StatoRegistrazioneDTO();
        assertThat(statoRegistrazioneDTO1).isNotEqualTo(statoRegistrazioneDTO2);
        statoRegistrazioneDTO2.setId(statoRegistrazioneDTO1.getId());
        assertThat(statoRegistrazioneDTO1).isEqualTo(statoRegistrazioneDTO2);
        statoRegistrazioneDTO2.setId(2L);
        assertThat(statoRegistrazioneDTO1).isNotEqualTo(statoRegistrazioneDTO2);
        statoRegistrazioneDTO1.setId(null);
        assertThat(statoRegistrazioneDTO1).isNotEqualTo(statoRegistrazioneDTO2);
    }
}
