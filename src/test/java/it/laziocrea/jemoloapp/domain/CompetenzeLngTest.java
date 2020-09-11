package it.laziocrea.jemoloapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CompetenzeLngTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetenzeLng.class);
        CompetenzeLng competenzeLng1 = new CompetenzeLng();
        competenzeLng1.setId(1L);
        CompetenzeLng competenzeLng2 = new CompetenzeLng();
        competenzeLng2.setId(competenzeLng1.getId());
        assertThat(competenzeLng1).isEqualTo(competenzeLng2);
        competenzeLng2.setId(2L);
        assertThat(competenzeLng1).isNotEqualTo(competenzeLng2);
        competenzeLng1.setId(null);
        assertThat(competenzeLng1).isNotEqualTo(competenzeLng2);
    }
}
