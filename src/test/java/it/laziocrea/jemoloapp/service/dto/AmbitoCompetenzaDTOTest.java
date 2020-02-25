package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class AmbitoCompetenzaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AmbitoCompetenzaDTO.class);
        AmbitoCompetenzaDTO ambitoCompetenzaDTO1 = new AmbitoCompetenzaDTO();
        ambitoCompetenzaDTO1.setId(1L);
        AmbitoCompetenzaDTO ambitoCompetenzaDTO2 = new AmbitoCompetenzaDTO();
        assertThat(ambitoCompetenzaDTO1).isNotEqualTo(ambitoCompetenzaDTO2);
        ambitoCompetenzaDTO2.setId(ambitoCompetenzaDTO1.getId());
        assertThat(ambitoCompetenzaDTO1).isEqualTo(ambitoCompetenzaDTO2);
        ambitoCompetenzaDTO2.setId(2L);
        assertThat(ambitoCompetenzaDTO1).isNotEqualTo(ambitoCompetenzaDTO2);
        ambitoCompetenzaDTO1.setId(null);
        assertThat(ambitoCompetenzaDTO1).isNotEqualTo(ambitoCompetenzaDTO2);
    }
}
