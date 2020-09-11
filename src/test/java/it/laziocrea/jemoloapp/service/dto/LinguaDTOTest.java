package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class LinguaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinguaDTO.class);
        LinguaDTO linguaDTO1 = new LinguaDTO();
        linguaDTO1.setId(1L);
        LinguaDTO linguaDTO2 = new LinguaDTO();
        assertThat(linguaDTO1).isNotEqualTo(linguaDTO2);
        linguaDTO2.setId(linguaDTO1.getId());
        assertThat(linguaDTO1).isEqualTo(linguaDTO2);
        linguaDTO2.setId(2L);
        assertThat(linguaDTO1).isNotEqualTo(linguaDTO2);
        linguaDTO1.setId(null);
        assertThat(linguaDTO1).isNotEqualTo(linguaDTO2);
    }
}
