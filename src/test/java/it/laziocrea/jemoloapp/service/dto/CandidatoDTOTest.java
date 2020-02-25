package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CandidatoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidatoDTO.class);
        CandidatoDTO candidatoDTO1 = new CandidatoDTO();
        candidatoDTO1.setId(1L);
        CandidatoDTO candidatoDTO2 = new CandidatoDTO();
        assertThat(candidatoDTO1).isNotEqualTo(candidatoDTO2);
        candidatoDTO2.setId(candidatoDTO1.getId());
        assertThat(candidatoDTO1).isEqualTo(candidatoDTO2);
        candidatoDTO2.setId(2L);
        assertThat(candidatoDTO1).isNotEqualTo(candidatoDTO2);
        candidatoDTO1.setId(null);
        assertThat(candidatoDTO1).isNotEqualTo(candidatoDTO2);
    }
}
