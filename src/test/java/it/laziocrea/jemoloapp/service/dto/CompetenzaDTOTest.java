package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CompetenzaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetenzaDTO.class);
        CompetenzaDTO competenzaDTO1 = new CompetenzaDTO();
        competenzaDTO1.setId(1L);
        CompetenzaDTO competenzaDTO2 = new CompetenzaDTO();
        assertThat(competenzaDTO1).isNotEqualTo(competenzaDTO2);
        competenzaDTO2.setId(competenzaDTO1.getId());
        assertThat(competenzaDTO1).isEqualTo(competenzaDTO2);
        competenzaDTO2.setId(2L);
        assertThat(competenzaDTO1).isNotEqualTo(competenzaDTO2);
        competenzaDTO1.setId(null);
        assertThat(competenzaDTO1).isNotEqualTo(competenzaDTO2);
    }
}
