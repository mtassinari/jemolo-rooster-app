package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CompetenzeLngDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetenzeLngDTO.class);
        CompetenzeLngDTO competenzeLngDTO1 = new CompetenzeLngDTO();
        competenzeLngDTO1.setId(1L);
        CompetenzeLngDTO competenzeLngDTO2 = new CompetenzeLngDTO();
        assertThat(competenzeLngDTO1).isNotEqualTo(competenzeLngDTO2);
        competenzeLngDTO2.setId(competenzeLngDTO1.getId());
        assertThat(competenzeLngDTO1).isEqualTo(competenzeLngDTO2);
        competenzeLngDTO2.setId(2L);
        assertThat(competenzeLngDTO1).isNotEqualTo(competenzeLngDTO2);
        competenzeLngDTO1.setId(null);
        assertThat(competenzeLngDTO1).isNotEqualTo(competenzeLngDTO2);
    }
}
