package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class CurriculumDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurriculumDTO.class);
        CurriculumDTO curriculumDTO1 = new CurriculumDTO();
        curriculumDTO1.setId(1L);
        CurriculumDTO curriculumDTO2 = new CurriculumDTO();
        assertThat(curriculumDTO1).isNotEqualTo(curriculumDTO2);
        curriculumDTO2.setId(curriculumDTO1.getId());
        assertThat(curriculumDTO1).isEqualTo(curriculumDTO2);
        curriculumDTO2.setId(2L);
        assertThat(curriculumDTO1).isNotEqualTo(curriculumDTO2);
        curriculumDTO1.setId(null);
        assertThat(curriculumDTO1).isNotEqualTo(curriculumDTO2);
    }
}
