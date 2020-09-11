package it.laziocrea.jemoloapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.laziocrea.jemoloapp.web.rest.TestUtil;

public class AllegatoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AllegatoDTO.class);
        AllegatoDTO allegatoDTO1 = new AllegatoDTO();
        allegatoDTO1.setId(1L);
        AllegatoDTO allegatoDTO2 = new AllegatoDTO();
        assertThat(allegatoDTO1).isNotEqualTo(allegatoDTO2);
        allegatoDTO2.setId(allegatoDTO1.getId());
        assertThat(allegatoDTO1).isEqualTo(allegatoDTO2);
        allegatoDTO2.setId(2L);
        assertThat(allegatoDTO1).isNotEqualTo(allegatoDTO2);
        allegatoDTO1.setId(null);
        assertThat(allegatoDTO1).isNotEqualTo(allegatoDTO2);
    }
}
